package com.revplay.revplay_user_service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Long userId;
    private Long artistId;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails() {}

    public CustomUserDetails(Long userId, Long artistId, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.artistId = artistId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public Long getUserId() { return userId; }
    public Long getArtistId() { return artistId; }
    public String getEmail() { return email; }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
