package com.expenseease.iam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.expenseease.iam.enums.ExpenseEaseResult.FAILURE;
import static com.expenseease.iam.enums.ExpenseEaseResult.SUCCESS;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpenseEaseResponse {
    private String result;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static ExpenseEaseResponse success(String message) {
        return new ExpenseEaseResponse(SUCCESS.name(), message, null);
    }

    public static ExpenseEaseResponse success(String message, Object data) {
        return new ExpenseEaseResponse(SUCCESS.name(), message, data);
    }

    public static ExpenseEaseResponse failure(String message) {
        return new ExpenseEaseResponse(FAILURE.name(), message, null);
    }

    public static ExpenseEaseResponse failure(String message, Object data) {
        return new ExpenseEaseResponse(FAILURE.name(), message, data);
    }
}
