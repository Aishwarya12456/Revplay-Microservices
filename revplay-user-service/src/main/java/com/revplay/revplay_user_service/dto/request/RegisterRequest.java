package com.revplay.revplay_user_service.dto.request;

public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String gender;
    private String bio;

    public RegisterRequest() {}

    public RegisterRequest(String username, String email, String password, String gender, String bio) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.bio = bio;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
