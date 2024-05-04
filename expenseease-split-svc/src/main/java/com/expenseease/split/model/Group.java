package com.expenseease.split.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long createdBy;
    private Timestamp createdOn;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "group_user",
            schema = "public",
            joinColumns = @JoinColumn(name = "group_id", table = "groups"),
            inverseJoinColumns = @JoinColumn(name = "user_id", table = "users"))
    @ToString.Exclude
    private Set<User> users = new HashSet<>();
    @OneToMany(mappedBy = "group")
    private Set<Expense> expenses = new HashSet<>();
}
