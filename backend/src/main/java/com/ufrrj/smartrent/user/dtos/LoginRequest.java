package com.ufrrj.smartrent.user.dtos;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;

    private String password;
}
