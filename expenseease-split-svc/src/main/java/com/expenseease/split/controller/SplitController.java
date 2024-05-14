package com.expenseease.split.controller;

import com.expenseease.split.dto.AddExpenseDTO;
import com.expenseease.split.dto.ExpenseEaseResponse;
import com.expenseease.split.model.User;
import com.expenseease.split.service.SplitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class SplitController {

    private final SplitService splitService;

    public SplitController(SplitService splitService) {
        this.splitService = splitService;
    }

    @PostMapping("/expenses")
    public ResponseEntity<ExpenseEaseResponse> createGroup(@RequestHeader("Authorization") String token, @RequestBody AddExpenseDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();
        splitService.addExpense(loggedInUser.getId(), request);
        return ResponseEntity.ok().body(ExpenseEaseResponse.success("Expense Added Successfully"));
    }
}
