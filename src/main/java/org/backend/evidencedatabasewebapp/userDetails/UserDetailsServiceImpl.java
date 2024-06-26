package org.backend.evidencedatabasewebapp.userDetails;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.backend.evidencedatabasewebapp.entities.User;
import org.backend.evidencedatabasewebapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return UserDetailsImpl.build(user);
    }
}
