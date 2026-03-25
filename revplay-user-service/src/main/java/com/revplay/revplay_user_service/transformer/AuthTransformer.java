package com.revplay.revplay_user_service.transformer;

import com.revplay.revplay_user_service.Enum.Gender;
import com.revplay.revplay_user_service.dto.request.RegisterRequest;
import com.revplay.revplay_user_service.dto.response.RegisterResponse;
import com.revplay.revplay_user_service.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class AuthTransformer {

    private final PasswordEncoder passwordEncoder;

    public AuthTransformer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User registerRequestToUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setGender(Gender.valueOf(registerRequest.getGender()));
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setBio(registerRequest.getBio());
        user.setEnabled(true);
        user.setRoles(new HashSet<>());
        return user;
    }

    public RegisterResponse userToRegisterResponse(User user) {
        return RegisterResponse
                .builder()
                .username(user.getUsername())
                .id(user.getId())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .bio(user.getBio())
                .build();
    }

}
