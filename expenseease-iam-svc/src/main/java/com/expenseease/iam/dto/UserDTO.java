package com.expenseease.iam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
}
