package com.example.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExpenseDatabaseDao {
    @Insert
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Query("SELECT * FROM expense_detail_table")
    fun getAll(): LiveData<List<Expense>>

    @Query("SELECT * FROM expense_detail_table WHERE expenseId = :key")
    fun getItemWithId(key: Long): LiveData<Expense>

    @Query("DELETE FROM expense_detail_table WHERE expenseId = :key")
    suspend fun delete(key: Long)
}