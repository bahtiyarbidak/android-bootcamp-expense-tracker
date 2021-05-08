package com.example.expensetracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_detail_table")
data class Expense(
        @PrimaryKey(autoGenerate = true)
        var expenseId: Long = 0L,

        @ColumnInfo(name = "type_of_expense")
        var expenseType: Int = -1,

        @ColumnInfo(name = "explanation_of_expense")
        var explanation: String = " ",

        @ColumnInfo(name = "amount_of_expense")
        var amountOfExp: Int = 0,

        @ColumnInfo(name = "currency_type")
        var currencyType: Int = -1
)