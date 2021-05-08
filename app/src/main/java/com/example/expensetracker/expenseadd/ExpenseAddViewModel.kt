package com.example.expensetracker.expenseadd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.database.Expense
import com.example.expensetracker.database.ExpenseDatabaseDao
import kotlinx.coroutines.launch

class ExpenseAddViewModel(
    val database: ExpenseDatabaseDao
) : ViewModel() {

    // insert
    fun addNewExpense(expense: Expense) {
        viewModelScope.launch {
            insert(expense)
        }
    }

    private suspend fun insert(expense: Expense) {
        database.insert(expense)
    }

}