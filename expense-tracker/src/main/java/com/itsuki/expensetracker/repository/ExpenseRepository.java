/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/02/13 
* 説明:  
*-----------------------------------------------------------*/ 
package com.itsuki.expensetracker.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.model.ExpenseType;

/**
 *@author 23238183 佐藤樹
 *
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.type = :type")
    Integer sumByType(ExpenseType type);
    
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
}

