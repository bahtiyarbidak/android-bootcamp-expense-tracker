package com.example.expensetracker

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.expensetracker.database.Expense
import com.example.expensetracker.database.ExpenseDatabase
import com.example.expensetracker.database.ExpenseDatabaseDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ExpenseDatabaseTest {

    private lateinit var expenseDao: ExpenseDatabaseDao
    private lateinit var db: ExpenseDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, ExpenseDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        expenseDao = db.expenseDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertExpense() {
        val expense = Expense()
        expenseDao.insert(expense)
        val received = expenseDao.getItemWithId(1)
        Assert.assertEquals(received?.currencyType, -1)
    }
}