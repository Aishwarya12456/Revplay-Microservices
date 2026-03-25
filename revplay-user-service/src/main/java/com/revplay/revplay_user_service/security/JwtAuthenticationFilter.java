package com.revplay.revplay_user_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String method = request.getMethod();
        String path = request.getServletPath();

        logger.info("Processing request: " + method + " " + path);

        if (method.equalsIgnoreCase("OPTIONS")) {
            logger.info("Skipping OPTIONS request");
            filterChain.doFilter(request, response);
            return;
        }

        if (path.equals("/revplay/auth/login") || path.startsWith("/revplay/auth/register")) {
            logger.info("Skipping authentication for public path: " + path);
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
                // Robustly extract IDs to ensure context is fully available if used elsewhere
                Claims claims = jwtUtils.extractAllClaims(jwt);
                Object userIdClaim = claims.get("userId");
                Long userId = null;
                if (userIdClaim instanceof Integer) userId = ((Integer) userIdClaim).longValue();
                else if (userIdClaim instanceof Long) userId = (Long) userIdClaim;
                else if (userIdClaim instanceof String) {
                    try { userId = Long.parseLong((String) userIdClaim); } catch (Exception e) {}
                }
                
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("Successfully authenticated user: " + username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.warn("JWT token missing or invalid for protected path: " + path);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }


    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
