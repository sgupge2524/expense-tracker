/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/03/19 
* 説明:  
*-----------------------------------------------------------*/ 
package com.itsuki.expensetracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itsuki.expensetracker.model.Account;
import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.repository.AccountRepository;
import com.itsuki.expensetracker.service.AccountService;
import com.itsuki.expensetracker.service.ExpenseService;



/**
 *@author 23238183 佐藤樹
 *
 */
@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private AccountService accountService;
    
    // 画面表示
    @GetMapping
    public String showExpneseList(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            Model model) {
        
        int targetYear = (year != null) ? year : LocalDate.now().getYear();
        int targetMonth = (month != null) ? month : LocalDate.now().getMonthValue();

        List<Expense> expenses = expenseService.findByMonth(targetYear, targetMonth);
        List<Account> accounts = accountService.findAll();
        
        model.addAttribute("expenses", expenses);
        model.addAttribute("accounts", accounts);
        model.addAttribute("totalExpense", expenseService.getTotalExpense());
        model.addAttribute("totalIncome", expenseService.getTotalIncome());
        model.addAttribute("totalExpenseByMonth", expenseService.getTotalExpenseByMonth(targetYear, targetMonth));
        model.addAttribute("totalIncomeByMonth", expenseService.getTotalIncomeByMonth(targetYear, targetMonth));
        model.addAttribute("selectedYear", targetYear);
        model.addAttribute("selectedMonth", targetMonth);

        return "expenses"; // Thymeleafのテンプレート名
    }
    
    @PostMapping
    public String createExpense(@ModelAttribute Expense expense, @RequestParam Long accountId) {
        
        expense.setAccount(accountService.findById(accountId));
        expenseService.save(expense);
        
        return "redirect:/expenses";
    }

}
