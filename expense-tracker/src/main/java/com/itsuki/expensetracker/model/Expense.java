/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/02/13 
* 説明:  
*-----------------------------------------------------------*/ 
package com.itsuki.expensetracker.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 *@author 23238183 佐藤樹
 *
 */
@Getter
@Setter
@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;
    
    private Integer amount;
    private ExpenseType type;
    private LocalDate date;
    private String memo;
    private Long userId;
    private Long categoryId;
    
}
