package com.revplay.revplay_security.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.revplay.revplay_security.model.JwtUser;
import com.revplay.revplay_security.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
// import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            if (jwtUtil.validateJwtToken(token)) {

                Claims claims = jwtUtil.extractAllClaims(token);

                Object userIdClaim = claims.get("userId");
                Long userId = null;
                if (userIdClaim instanceof Integer) {
                    userId = ((Integer) userIdClaim).longValue();
                } else if (userIdClaim instanceof Long) {
                    userId = (Long) userIdClaim;
                } else if (userIdClaim instanceof String) {
                    try {
                        userId = Long.parseLong((String) userIdClaim);
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: userId in JWT is not a valid number: " + userIdClaim);
                    }
                }

                if (userId == null) {
                    System.out.println("Warning: userId not found in JWT claims");
                    filterChain.doFilter(request, response);
                    return;
                }

                Object artistIdClaim = claims.get("artistId");
                Long artistId = null;
                if (artistIdClaim instanceof Integer) {
                    artistId = ((Integer) artistIdClaim).longValue();
                } else if (artistIdClaim instanceof Long) {
                    artistId = (Long) artistIdClaim;
                } else if (artistIdClaim instanceof String) {
                    try {
                        artistId = Long.parseLong((String) artistIdClaim);
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: artistId in JWT is not a valid number: " + artistIdClaim);
                    }
                }

                String email = claims.getSubject();

                List<String> roles = claims.get("roles", List.class);
                System.out.println("Extracted roles from JWT: " + roles);

                if (roles == null) {
                    System.out.println("Warning: roles not found in JWT claims");
                    filterChain.doFilter(request, response);
                    return;
                }

                JwtUser jwtUser = new JwtUser(userId, artistId, email, roles);

                List<SimpleGrantedAuthority> authorities =
                        roles.stream()
                                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                                .map(SimpleGrantedAuthority::new)
                                .toList();
                System.out.println("Setting authorities in SecurityContext: " + authorities);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                jwtUser,
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
