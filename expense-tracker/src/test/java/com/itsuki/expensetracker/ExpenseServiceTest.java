package com.itsuki.expensetracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    
    @Transactional
    @Test
    void 合計支出を取得できること() {
        saveSampleExpense(1000, ExpenseType.EXPENSE, "ランチ代");
        saveSampleExpense(5000, ExpenseType.EXPENSE, "交通費");
        saveSampleExpense(10000, ExpenseType.EXPENSE, "備品代");
        saveSampleExpense(10000, ExpenseType.INCOME, "給与");

        int totalExpense = expenseService.getTotalExpense();

        assertEquals(16000, totalExpense);
    }
    
    @Transactional
    @Test
    void 合計収入を取得できること() {
        saveSampleExpense(1000, ExpenseType.EXPENSE, "ランチ代");
        saveSampleExpense(20000, ExpenseType.INCOME, "給与");
        saveSampleExpense(5000, ExpenseType.INCOME, "副収入");

        int totalIncome = expenseService.getTotalIncome();

        assertEquals(25000, totalIncome);
    }
    
    @Transactional
    @Test
    void 月別収支を取得できること() {
        saveSampleExpense(1000, ExpenseType.EXPENSE, "ランチ代", LocalDate.of(2026, 3, 10));
        saveSampleExpense(20000, ExpenseType.INCOME, "給与", LocalDate.of(2026, 3, 15));
        saveSampleExpense(5000, ExpenseType.INCOME, "副収入", LocalDate.of(2026, 3, 20));
        saveSampleExpense(8000, ExpenseType.INCOME, "別月収入", LocalDate.of(2026, 4, 1));

        List<Expense> list = expenseService.findByMonth(2026, 3);

        assertThat(list).hasSize(3);
    }
    
    private void saveSampleExpense(Integer amount, ExpenseType type, String memo) {
        saveSampleExpense(amount, type, memo, LocalDate.now());
    }

    private void saveSampleExpense(Integer amount, ExpenseType type, String memo, LocalDate date) {
        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setType(type);
        expense.setDate(date);
        expense.setMemo(memo);
        expenseService.save(expense);
    }
    
    
}

