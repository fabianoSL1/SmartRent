package com.ufrrj.smartrent.user.service;

import com.ufrrj.smartrent.common.exception.NotFoundException;
import com.ufrrj.smartrent.common.security.AuthUtils;
import com.ufrrj.smartrent.user.model.Renter;
import com.ufrrj.smartrent.user.repository.RenterRepository;
import com.ufrrj.smartrent.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RenterService {

    private final RenterRepository renterRepository;

    private final UserRepository userRepository;

    public Renter getCurrentRenter() {
        var username = AuthUtils.getCurrentAuthUsername();

        var exist = renterRepository.findOwnerByUserUsername(username);
        return exist.orElseGet(() -> createByUsername(username));
    }

    private Renter createByUsername(String username) {
        var user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var renter = Renter.builder()
                .user(user.get())
                .build();

        return renterRepository.save(renter);
    }

}
