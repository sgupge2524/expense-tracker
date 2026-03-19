package com.itsuki.expensetracker;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;

import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.model.ExpenseType;
import com.itsuki.expensetracker.service.ExpenseService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ExpenseServiceTest {

    @Autowired
    private ExpenseService expenseService; 

    @Transactional
    @Test
    void 収支データを保存できること() {
        // 1. 準備 (Arrange)
        Expense expense = new Expense();
        expense.setAmount(1000);
        expense.setType(ExpenseType.EXPENSE);
        expense.setDate(LocalDate.now());

        // 2. 実行 (Act)
        Expense savedExpense = expenseService.save(expense);

        // 3. 検証 (Assert)
        assertThat(savedExpense.getId()).isNotNull();
        assertThat(savedExpense.getAmount()).isEqualTo(1000);
        assertThat(savedExpense.getType()).isEqualTo(ExpenseType.EXPENSE);
        assertThat(savedExpense.getDate()).isEqualTo(LocalDate.now());
    }
    
    @Transactional
    @Test
    void 全ての収支データを取得できること() {
        // 1. 準備 (Arrange)
        // 2つのデータを保存したと仮定します
        saveSampleExpense(1000, ExpenseType.EXPENSE, "ランチ代");
        saveSampleExpense(5000, ExpenseType.INCOME, "給料");

        // 2. 実行 (Act)
        List<Expense> list = expenseService.findAll();

        // 3. 検証 (Assert)
        assertThat(list).hasSize(2);
        assertThat(list).extracting(Expense::getAmount)
        .containsExactlyInAnyOrder(1000, 5000);
    }
    
    @Transactional
    @Test 
    void メモに検索文字列を含む収支データを取得できること() {
        // 1. 準備 (Arrange)
        saveSampleExpense(1000, ExpenseType.EXPENSE, "ランチ代");
        saveSampleExpense(5000, ExpenseType.EXPENSE, "交通費");

        // 2. 実行 (Act)
        List<Expense> list = expenseService.findByMemoContaining("ランチ");

        // 3. 検証 (Assert)
        assertThat(list).hasSize(1);
        assertThat(list.get(0).getMemo()).contains("ランチ");
    }
    
    private void saveSampleExpense(Integer amount, ExpenseType type, String memo) {
        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setType(type);
        expense.setDate(LocalDate.now());
        expense.setMemo(memo);
        expenseService.save(expense);
    }
}

