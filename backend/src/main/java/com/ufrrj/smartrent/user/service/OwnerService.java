package com.ufrrj.smartrent.user.service;

import com.ufrrj.smartrent.common.exception.NotFoundException;
import com.ufrrj.smartrent.user.model.Owner;
import com.ufrrj.smartrent.user.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public Owner findOwnerByUsername(String username) {
        var owner = ownerRepository.findOwnerByUserUsername(username);

        if (owner.isEmpty()) {
            throw new NotFoundException("Owner not found");
        }

        return owner.get();
    }
}
