package com.ufrrj.smartrent.dtos.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {

    private long id;

    private String username;

}
