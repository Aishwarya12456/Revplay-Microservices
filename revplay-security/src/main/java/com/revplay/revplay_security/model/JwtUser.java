package com.revplay.revplay_security.model;

// import lombok.AllArgsConstructor;
// import lombok.Getter;

import java.util.List;

public class JwtUser {

    private Long userId;
    private Long artistId;
    private String email;
    private List<String> roles;

    public JwtUser(Long userId, Long artistId, String email, List<String> roles) {
        this.userId = userId;
        this.artistId = artistId;
        this.email = email;
        this.roles = roles;
    }

    public Long getUserId() { return userId; }
    public Long getArtistId() { return artistId; }
    public String getEmail() { return email; }
    public List<String> getRoles() { return roles; }
}
