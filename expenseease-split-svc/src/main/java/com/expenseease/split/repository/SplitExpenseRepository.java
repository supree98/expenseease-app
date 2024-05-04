package com.expenseease.split.repository;

import com.expenseease.split.model.SplitExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SplitExpenseRepository extends JpaRepository<SplitExpense, Long> {
}
