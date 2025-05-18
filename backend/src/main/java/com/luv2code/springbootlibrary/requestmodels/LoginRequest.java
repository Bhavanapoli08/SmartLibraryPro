package com.luv2code.springbootlibrary.requestmodels;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
