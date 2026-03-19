/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/03/19 
* 説明:  
*-----------------------------------------------------------*/ 
package com.itsuki.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itsuki.expensetracker.model.Expense;
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
    
    // 画面表示
    @GetMapping
    public String showExpneseList(Model model) {
        List<Expense> expenses = expenseService.findAll();
        model.addAttribute("expenses", expenses);
        model.addAttribute("totalExpense", expenseService.getTotalExpense());
        model.addAttribute("totalIncome", expenseService.getTotalIncome());
        return "expenses"; // Thymeleafのテンプレート名
    }
    
    @PostMapping
    public String createExpense(@ModelAttribute Expense expense) {
        // ① 保存処理
        expenseService.save(expense);
        // ② 一覧画面にリダイレクト
        return "redirect:/expenses";
    }

}
