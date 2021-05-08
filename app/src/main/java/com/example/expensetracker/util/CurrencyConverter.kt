package com.example.expensetracker.util

import com.example.expensetracker.R
import com.example.expensetracker.database.Expense

fun convertCurrency(item: Expense): Int {
    val amount: Int
    when (currentCurrency) {
        R.id.button_try -> {
            amount = when (item.currencyType) {
                R.id.button_gbp -> (item.amountOfExp.toFloat() / tryToGbp).toInt()
                R.id.button_eur -> (item.amountOfExp.toFloat() * eurToTry).toInt()
                R.id.button_usd -> (item.amountOfExp.toFloat() / tryToUsd).toInt()
                else -> item.amountOfExp
            }
        }
        R.id.button_gbp -> {
            amount = when (item.currencyType) {
                R.id.button_try -> (item.amountOfExp.toFloat() * tryToGbp).toInt()
                R.id.button_eur -> (item.amountOfExp.toFloat() * eurToGbp).toInt()
                R.id.button_usd -> (item.amountOfExp.toFloat() / gbpToUsd).toInt()
                else -> item.amountOfExp
            }
        }
        R.id.button_eur -> {
            amount = when (item.currencyType) {
                R.id.button_try -> (item.amountOfExp.toFloat() / eurToTry).toInt()
                R.id.button_gbp -> (item.amountOfExp.toFloat() / eurToGbp).toInt()
                R.id.button_usd -> (item.amountOfExp.toFloat() / eurToUsd).toInt()
                else -> item.amountOfExp
            }
        }
        else -> {
            amount = when (item.currencyType) {
                R.id.button_try -> (item.amountOfExp.toFloat() * tryToUsd).toInt()
                R.id.button_gbp -> (item.amountOfExp.toFloat() * gbpToUsd).toInt()
                R.id.button_eur -> (item.amountOfExp.toFloat() * eurToUsd).toInt()
                else -> item.amountOfExp
            }
        }
    }
    return amount
}