package com.luv2code.springbootlibrary.requestmodels;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
