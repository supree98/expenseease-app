package com.expenseease.iam.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String emailId;
    private String password;
}
