package com.expenseease.split.dto;

import com.expenseease.split.enums.SplitType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddSplitExpenseDTO {
    private Long coSpender;
    private double amount;
    private SplitType type;
}
