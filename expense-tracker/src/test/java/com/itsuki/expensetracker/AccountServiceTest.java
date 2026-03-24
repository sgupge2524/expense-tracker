/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/03/23 
* 説明:  
*-----------------------------------------------------------*/ 
package com.itsuki.expensetracker;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itsuki.expensetracker.model.Account;
import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.model.ExpenseType;
import com.itsuki.expensetracker.service.AccountService;

/**
 *@author 23238183 佐藤樹
 *
 */
@SpringBootTest
class AccountServiceTest {
    
    @Autowired
    private AccountService accountService; 

    @Transactional
    @Test
    void アカウントデータを保存できること() {
        // 1. 準備 (Arrange)
        Account account = new Account();
        account.setName("メイン口座");

        // 2. 実行 (Act)
        Account savedAccount = accountService.save(account);

        // 3. 検証 (Assert)
        assertThat(savedAccount.getId()).isNotNull();
        assertThat(savedAccount.getName()).isEqualTo("メイン口座");
    }
    
    @Transactional
    @Test
    void 全てのアカウントデータを取得できること() {
        // 1. 準備 (Arrange)
        // 2つのデータを保存したと仮定します
        saveSampleAccount("メイン口座");
        saveSampleAccount("サブ口座");

        // 2. 実行 (Act)
        List<Account> list = accountService.findAll();

        // 3. 検証 (Assert)
        assertThat(list).hasSize(2);
        assertThat(list).extracting(Account::getName)
        .containsExactlyInAnyOrder("メイン口座", "サブ口座");
    }
    
    @Transactional
    @Test
    void IDでアカウントデータを取得できること() {
        // 1. 準備 (Arrange)
        Account account = new Account();
        account.setName("メイン口座");
        Account savedAccount = accountService.save(account);

        // 2. 実行 (Act)
        Account foundAccount = accountService.findById(savedAccount.getId());

        // 3. 検証 (Assert)
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getName()).isEqualTo("メイン口座");
    }
    
    private void saveSampleAccount(String name) {
        Account account = new Account();
        account.setName(name);
        accountService.save(account);
    }

}
