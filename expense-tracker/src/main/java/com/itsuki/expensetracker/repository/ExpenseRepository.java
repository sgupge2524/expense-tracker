/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/02/13 
* 説明:  
*-----------------------------------------------------------*/ 
package com.itsuki.expensetracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.itsuki.expensetracker.model.Expense;

/**
 *@author 23238183 佐藤樹
 *
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}