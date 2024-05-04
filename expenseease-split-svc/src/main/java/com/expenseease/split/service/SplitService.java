package com.expenseease.split.service;

import com.expenseease.split.dto.AddExpenseDTO;
import com.expenseease.split.dto.AddSplitExpenseDTO;
import com.expenseease.split.model.Expense;
import com.expenseease.split.model.Group;
import com.expenseease.split.model.SplitExpense;
import com.expenseease.split.model.User;
import com.expenseease.split.repository.ExpenseRepository;
import com.expenseease.split.repository.GroupRepository;
import com.expenseease.split.repository.SplitExpenseRepository;
import com.expenseease.split.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SplitService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final SplitExpenseRepository splitExpenseRepository;

    public SplitService(UserRepository userRepository, GroupRepository groupRepository, ExpenseRepository expenseRepository, SplitExpenseRepository splitExpenseRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.splitExpenseRepository = splitExpenseRepository;
    }

    public void addExpense(Long loggedInUserId, AddExpenseDTO request) {
        Expense expense = new Expense();

        User user = userRepository.findById(loggedInUserId).get();
        Group group = groupRepository.findById(request.getGroupId()).get();
        expense.setGroup(group);
        expense.setCreatedBy(user);
        expense.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expenseRepository.save(expense);

        double share = request.getAmount() / request.getSplitExpenses().size();

        for (AddSplitExpenseDTO addSplitExpenseDTO : request.getSplitExpenses()) {
            User coSpender = userRepository.findById(addSplitExpenseDTO.getCoSpender()).get();
            SplitExpense splitExpense = new SplitExpense();
            splitExpense.setCoSpender(coSpender);
            splitExpense.setAmount(share);
            splitExpense.setType(addSplitExpenseDTO.getType().toString());
            splitExpense.setExpense(expense);
            splitExpenseRepository.save(splitExpense);
        }
    }
}
