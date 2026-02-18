package com.itsuki.expensetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense save(Expense expense) {
        // ここに保存処理を書きます
        expenseRepository.save(expense);
        return null; // まずはコンパイルを通すために最小限の戻り値を返します
    }
    
    public List<Expense> findAll() {
        // ここに全件取得処理を書きます
        return null; // まずはコンパイルを通すために最小限の戻り値を返します
    }
}