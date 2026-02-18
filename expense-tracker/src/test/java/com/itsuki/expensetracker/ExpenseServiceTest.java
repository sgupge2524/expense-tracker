package com.itsuki.expensetracker;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;

import com.itsuki.expensetracker.model.Expense;
import com.itsuki.expensetracker.model.ExpenseType;
import com.itsuki.expensetracker.service.ExpenseService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ExpenseServiceTest {

    @Autowired
    private ExpenseService expenseService; // まだ存在しないのでコンパイルエラーになります

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
    }
    
    @Test
    void 全ての収支データを取得できること() {
        // 1. 準備 (Arrange)
        // 2つのデータを保存したと仮定します
        saveSampleExpense(1000, ExpenseType.EXPENSE);
        saveSampleExpense(5000, ExpenseType.INCOME);

        // 2. 実行 (Act)
        List<Expense> list = expenseService.findAll();

        // 3. 検証 (Assert)
        assertThat(list).hasSize(2);
    }
    
    private void saveSampleExpense(Integer amount, ExpenseType type) {
        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setType(type);
        expense.setDate(LocalDate.now());
        // ここでリポジトリを使って保存する処理が必要になります
        Expense savedExpense = expenseService.save(expense);
    }
}

