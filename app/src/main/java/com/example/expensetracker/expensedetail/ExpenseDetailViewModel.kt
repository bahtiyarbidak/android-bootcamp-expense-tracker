package com.example.expensetracker.expensedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.database.Expense
import com.example.expensetracker.database.ExpenseDatabaseDao
import kotlinx.coroutines.launch

class ExpenseDetailViewModel(
    private val expenseKey: Long = 0L,
    val database: ExpenseDatabaseDao
) : ViewModel() {

    // get
    private val expense: LiveData<Expense> = database.getItemWithId(expenseKey)
    fun getExpense() = expense

    // delete
    fun deleteExpense() {
        viewModelScope.launch {
            delete()
        }
    }

    private suspend fun delete() {
        database.delete(expenseKey)
    }
}