package com.ufrrj.smartrent.common.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

    public static String getCurrentAuthUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
