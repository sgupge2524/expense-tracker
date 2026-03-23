/*-------------------------------------------- 
* 佐賀大学理工学部数理・情報部門 
*  ソフトウェア協同開発 第x回 xx演習 
* 作成者: 23238183 佐藤樹 
* 作成日: 2026/03/23 
* 説明:  
*-----------------------------------------------------------*/ 
package com.itsuki.expensetracker.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 *@author 23238183 佐藤樹
 *
 */
public class Budget {
    Long id;
    Account account;
    @Enumerated(EnumType.STRING)
    Category category;
    Integer amount;
}
