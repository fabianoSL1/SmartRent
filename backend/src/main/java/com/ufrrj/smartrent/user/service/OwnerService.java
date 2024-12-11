package com.ufrrj.smartrent.user.service;

import com.ufrrj.smartrent.common.exception.NotFoundException;
import com.ufrrj.smartrent.common.security.AuthUtils;
import com.ufrrj.smartrent.user.model.Owner;
import com.ufrrj.smartrent.user.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public Owner getCurrentOwner() {
        var username = AuthUtils.getCurrentAuthUsername();
        var owner = ownerRepository.findOwnerByUserUsername(username);

        if (owner.isEmpty()) {
            throw new NotFoundException("Proprietario não encontrado.");
        }

        return owner.get();
    }
}
