package com.expenseease.split.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "split_expenses", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SplitExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
    @ManyToOne
    @JoinColumn(name = "co_spender")
    private User coSpender;
    private double amount;
    private String type;
}
