package com.smartlibrarypro.springbootlibrary.requestmodels;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
