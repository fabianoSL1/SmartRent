package com.ufrrj.smartrent.dtos.auth;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RegisterResponse {

    private UUID id;

    private String username;

}
