package com.revplay.revplay_user_service.dto.response;

public class RegisterResponse {
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
    private String bio;

    public RegisterResponse() {}

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

    public static RegisterResponseBuilder builder() {
        return new RegisterResponseBuilder();
    }

    public static class RegisterResponseBuilder {
        private Long id;
        private String username;
        private String email;
        private String profilePicture;
        private String bio;

        public RegisterResponseBuilder id(Long id) { this.id = id; return this; }
        public RegisterResponseBuilder username(String username) { this.username = username; return this; }
        public RegisterResponseBuilder email(String email) { this.email = email; return this; }
        public RegisterResponseBuilder profilePicture(String profilePicture) { this.profilePicture = profilePicture; return this; }
        public RegisterResponseBuilder bio(String bio) { this.bio = bio; return this; }

        public RegisterResponse build() {
            RegisterResponse response = new RegisterResponse();
            response.setId(id);
            response.setUsername(username);
            response.setEmail(email);
            response.setProfilePicture(profilePicture);
            response.setBio(bio);
            return response;
        }
    }
}
