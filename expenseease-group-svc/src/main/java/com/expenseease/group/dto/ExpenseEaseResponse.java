package com.expenseease.group.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.expenseease.group.enums.ExpenseEaseResult.SUCCESS;
import static com.expenseease.group.enums.ExpenseEaseResult.FAILURE;

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
