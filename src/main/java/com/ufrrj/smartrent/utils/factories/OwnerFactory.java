package com.ufrrj.smartrent.utils.factories;

import com.ufrrj.smartrent.models.Owner;
import com.ufrrj.smartrent.models.User;

public class OwnerFactory {

    public static Owner createOwner(User user) {
        return Owner.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

}
