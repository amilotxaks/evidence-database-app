package org.backend.evidencedatabasewebapp.services;

import lombok.RequiredArgsConstructor;
import org.backend.evidencedatabasewebapp.dtos.UserRegistrationForm;
import org.backend.evidencedatabasewebapp.entities.User;
import org.backend.evidencedatabasewebapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserRegistrationForm form) {
        User user = User.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        return userRepository.save(user);
    }
}
