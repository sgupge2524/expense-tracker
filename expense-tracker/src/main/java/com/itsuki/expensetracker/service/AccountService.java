/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/03/23 
* 説明:  
*-----------------------------------------------------------*/ 
package com.itsuki.expensetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itsuki.expensetracker.model.Account;
import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

/**
 *@author 23238183 佐藤樹
 *
 */
@Service
@RequiredArgsConstructor
public class AccountService {
    
    private final AccountRepository accountRepository;
    
    public Account save(Account account) {
        return accountRepository.save(account);
    }
    
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + id));
    }

}
