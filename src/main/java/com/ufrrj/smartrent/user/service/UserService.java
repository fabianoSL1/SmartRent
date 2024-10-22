package com.ufrrj.smartrent.user.service;

import com.ufrrj.smartrent.common.exception.NotFoundException;
import com.ufrrj.smartrent.user.model.Owner;
import com.ufrrj.smartrent.user.model.Renter;
import com.ufrrj.smartrent.user.model.User;
import com.ufrrj.smartrent.user.repository.OwnerRepository;
import com.ufrrj.smartrent.user.repository.RenterRepository;
import com.ufrrj.smartrent.user.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    private final OwnerRepository ownerRepository;

    private final RenterRepository renterRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findUserByUsername(username);
    }

    public User findUserByUsername(String username) throws RuntimeException {
        var user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new NotFoundException("user not found");
        }

        return user.get();
    }

    @Transactional()
    public User saveUser(String username, String password) throws Error {
        var exist = userRepository.findByUsername(username);

        if (exist.isPresent()) {
            throw new NotFoundException("Username " + username + " already exists");
        }

        var encrypt = new BCryptPasswordEncoder().encode(password);

        var user = User.builder()
                .username(username)
                .password(encrypt)
                .build();

        userRepository.save(user);

        saveOwner(user);

        saveRenter(user);

        return userRepository.save(user);
    }

    private void saveRenter(User user) {
        var renter = Renter.builder()
            .user(user)
            .build();

        renterRepository.save(renter);
    }

    private void saveOwner(User user) {
        var owner = Owner.builder()
                .user(user)
                .build();

        ownerRepository.save(owner);
    }

}
