package com.itsuki.expensetracker.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.model.ExpenseType;
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
    
    public List<Expense> findByType(ExpenseType keyword) {
        List<Expense> allExpense = expenseRepository.findAll();

        List<Expense> filteredExpenses = allExpense.stream()
            .filter(expense -> expense.getType() == keyword)
            .toList();

        return filteredExpenses;
    }
    
    public int getTotalExpense() {
        Integer total = expenseRepository.sumByType(ExpenseType.EXPENSE);
        return total != null ? total : 0;
    }
    
    public int getTotalIncome() {
        Integer total = expenseRepository.sumByType(ExpenseType.INCOME);
        return total != null ? total : 0;
    }
    
    // 指定された年月の収支データを取得するメソッド
    public List<Expense> findByMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return expenseRepository.findByDateBetween(startDate, endDate);
    }
    
    // 指定された年月の合計支出を取得するメソッド
    public int getTotalExpenseByMonth(int year, int month) {
        List<Expense> filteredExpenses = findByMonth(year, month);
        Integer total = filteredExpenses.stream()
                .filter(expense -> expense.getType() == ExpenseType.EXPENSE)
                .mapToInt(Expense::getAmount)
                .sum();
        return total != null ? total : 0;
    }
    
 // 指定された年月の合計収入を取得するメソッド
    public int getTotalIncomeByMonth(int year, int month) {
        List<Expense> filteredExpenses = findByMonth(year, month);
        Integer total = filteredExpenses.stream()
                .filter(expense -> expense.getType() == ExpenseType.INCOME)
                .mapToInt(Expense::getAmount)
                .sum();
        return total != null ? total : 0;
    }
    
    
}