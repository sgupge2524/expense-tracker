package com.itsuki.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense save(Expense expense) {        
        return expenseRepository.save(expense);
    }
    
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }
    
    public List<Expense> findByMemoContaining(String keyword) {
        List<Expense> allExpense = expenseRepository.findAll();

        List<Expense> filteredExpenses = allExpense.stream()
            .filter(expense -> expense.getMemo().contains(keyword))
            .toList();

        return filteredExpenses;
    }
    
    
}