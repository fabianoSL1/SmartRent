package com.ufrrj.smartrent.services;

import com.ufrrj.smartrent.models.User;
import com.ufrrj.smartrent.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findUserByUsername(username);
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return user.get();
    }

    public User saveUser(String username, String password) {
        var exist = userRepository.findByUsername(username);

        if (exist.isPresent()) {
            throw new Error("");
        }
        var encrypt = new BCryptPasswordEncoder().encode(password);

        var user = User.builder()
                .username(username)
                .password(encrypt)
                .build();

        return userRepository.save(user);
    }


}
