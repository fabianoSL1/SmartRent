package com.ufrrj.smartrent.user.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {

    private long id;

    private String username;

}
