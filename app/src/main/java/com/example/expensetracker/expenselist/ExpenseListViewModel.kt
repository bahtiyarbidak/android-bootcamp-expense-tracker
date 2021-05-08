package com.example.expensetracker.expenselist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.database.ExpenseDatabaseDao
import com.example.expensetracker.network.CurrencyApi
import kotlinx.coroutines.launch

class ExpenseListViewModel(
    val database: ExpenseDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val expenses = database.getAll()

    private val _navigateToExpenseDetail = MutableLiveData<Long?>()

    fun onExpenseClicked(id: Long) {
        _navigateToExpenseDetail.value = id
    }

    val navigateToExpenseDetail
        get() = _navigateToExpenseDetail

    fun onExpenseDetailNavigated() {
        _navigateToExpenseDetail.value = null
    }


    // Get Data
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {

        viewModelScope.launch {
            try {
                val eurToTry = CurrencyApi.retrofitService.getEurTry()
                val eurToGbp = CurrencyApi.retrofitService.getEurGbp()
                val eurToUsd = CurrencyApi.retrofitService.getEurUsd()
                _response.value = "$eurToTry|$eurToGbp|$eurToUsd"

            } catch (e: Exception) {
                _response.value = "Failure!"
            }
        }
    }
}