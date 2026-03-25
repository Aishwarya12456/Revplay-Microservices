package com.revplay.revplay_user_service.dto.response;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
    private String bio;

    public UserResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public static UserResponseBuilder builder() {
        return new UserResponseBuilder();
    }

    public static class UserResponseBuilder {
        private Long id;
        private String username;
        private String email;
        private String profilePicture;
        private String bio;

        public UserResponseBuilder id(Long id) { this.id = id; return this; }
        public UserResponseBuilder username(String username) { this.username = username; return this; }
        public UserResponseBuilder email(String email) { this.email = email; return this; }
        public UserResponseBuilder profilePicture(String profilePicture) { this.profilePicture = profilePicture; return this; }
        public UserResponseBuilder bio(String bio) { this.bio = bio; return this; }

        public UserResponse build() {
            UserResponse response = new UserResponse();
            response.setId(id);
            response.setUsername(username);
            response.setEmail(email);
            response.setProfilePicture(profilePicture);
            response.setBio(bio);
            return response;
        }
    }
}
