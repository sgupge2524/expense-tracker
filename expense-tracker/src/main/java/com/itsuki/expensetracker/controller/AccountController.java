/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/03/23 
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

import com.itsuki.expensetracker.model.Account;
import com.itsuki.expensetracker.service.AccountService;

/**
 *@author 23238183 佐藤樹
 *
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    
    
    @GetMapping
    public String showAccountList(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        return "accounts"; // Thymeleafのテンプレート名
    }
    
    @PostMapping
    public String addAccount(@ModelAttribute Account account) {
        accountService.save(account);
        return "redirect:/accounts"; // 追加後にアカウント一覧にリダイレクト
    }
    
    

}
