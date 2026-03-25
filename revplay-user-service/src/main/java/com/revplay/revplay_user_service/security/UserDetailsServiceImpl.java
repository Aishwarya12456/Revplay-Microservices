package com.revplay.revplay_user_service.security;

import com.revplay.revplay_user_service.Enum.RoleName;
import com.revplay.revplay_user_service.model.Artist;
import com.revplay.revplay_user_service.model.User;
import com.revplay.revplay_user_service.repository.ArtistRepository;
import com.revplay.revplay_user_service.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    public UserDetailsServiceImpl(UserRepository userRepository, ArtistRepository artistRepository) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        Long artistId = null;

        if (user.getRoles().stream()
                .anyMatch(r -> r.getName() == RoleName.ARTIST)) {

            artistId = artistRepository
                    .findByUserId(user.getId())
                    .map(Artist::getId)
                    .orElse(null);
            log.info("User {} has ARTIST role. artistId found: {}", email, artistId);
        } else {
            log.info("User {} does not have ARTIST role", email);
        }

        List<SimpleGrantedAuthority> authorities =
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                        .toList();

        return new CustomUserDetails(
                user.getId(),
                artistId,
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}

