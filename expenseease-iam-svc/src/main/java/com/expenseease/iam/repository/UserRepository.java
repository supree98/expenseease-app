package com.expenseease.iam.repository;

import com.expenseease.iam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
