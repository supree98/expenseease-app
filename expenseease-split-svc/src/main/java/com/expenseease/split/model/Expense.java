package com.expenseease.split.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "expenses", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    private Timestamp createdOn;
    private String description;
    private double amount;
    @OneToMany(mappedBy = "expense")
    private Set<SplitExpense> splitExpenses;
}
