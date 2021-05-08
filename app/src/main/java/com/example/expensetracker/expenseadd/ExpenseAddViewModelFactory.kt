package com.example.expensetracker.expenseadd

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.database.ExpenseDatabaseDao
import com.example.expensetracker.expenselist.ExpenseListViewModel

class ExpenseAddViewModelFactory(private val dataSource: ExpenseDatabaseDao,
                                 private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseAddViewModel::class.java)) {
            return ExpenseAddViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}