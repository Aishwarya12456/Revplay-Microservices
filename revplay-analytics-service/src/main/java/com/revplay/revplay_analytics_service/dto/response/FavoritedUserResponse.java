package com.revplay.revplay_analytics_service.dto.response;

public class FavoritedUserResponse {

    private Long userId;
    private String username;
    private String displayName;
    private String profilePicture;

    public FavoritedUserResponse() {}

    public FavoritedUserResponse(Long userId, String username, String displayName, String profilePicture) {
        this.userId = userId;
        this.username = username;
        this.displayName = displayName;
        this.profilePicture = profilePicture;
    }

    public static FavoritedUserResponseBuilder builder() {
        return new FavoritedUserResponseBuilder();
    }

    public static class FavoritedUserResponseBuilder {
        private Long userId;
        private String username;
        private String displayName;
        private String profilePicture;

        public FavoritedUserResponseBuilder userId(Long userId) { this.userId = userId; return this; }
        public FavoritedUserResponseBuilder username(String username) { this.username = username; return this; }
        public FavoritedUserResponseBuilder displayName(String displayName) { this.displayName = displayName; return this; }
        public FavoritedUserResponseBuilder profilePicture(String profilePicture) { this.profilePicture = profilePicture; return this; }

        public FavoritedUserResponse build() {
            return new FavoritedUserResponse(userId, username, displayName, profilePicture);
        }
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
}
