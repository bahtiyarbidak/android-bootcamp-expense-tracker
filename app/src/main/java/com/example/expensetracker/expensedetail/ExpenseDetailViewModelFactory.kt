package com.example.expensetracker.expensedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.database.ExpenseDatabaseDao

class ExpenseDetailViewModelFactory (
    private val expenseKey: Long,
    private val dataSource: ExpenseDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseDetailViewModel::class.java)) {
            return ExpenseDetailViewModel(expenseKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}