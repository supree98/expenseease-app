package com.expenseease.split.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddExpenseDTO {
    private Long groupId;
    private String description;
    private double amount;
    private boolean isEqualShare;
    private Set<AddSplitExpenseDTO> splitExpenses;
}
