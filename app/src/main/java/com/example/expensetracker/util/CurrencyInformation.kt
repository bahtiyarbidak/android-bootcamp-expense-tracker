package com.example.expensetracker.util

import android.content.Context
import com.example.expensetracker.R

const val KEY_CURRENT_CURRENCY = "key_current_currency"
const val KEY_EUR_TO_TRY = "key_eur_to_try"
const val KEY_EUR_TO_GBP = "key_eur_to_gbp"
const val KEY_EUR_TO_USD = "key_eur_to_usd"

var currentCurrency = 0
var eurToTry = 0F
var eurToGbp = 0F
var eurToUsd = 0F
var tryToGbp = 0F
var tryToUsd = 0F
var gbpToUsd = 0F

fun setCurrentCurrency(mContext: Context, currentCurrency: Int) {
    val editor = mContext.getSharedPreferences(KEY_CURRENT_CURRENCY, Context.MODE_PRIVATE).edit()
    editor.putInt(KEY_CURRENT_CURRENCY, currentCurrency)
    editor.apply()
}

fun getCurrentCurrency(mContext: Context): Int {
    val pref = mContext.getSharedPreferences(KEY_CURRENT_CURRENCY, Context.MODE_PRIVATE)
    return pref.getInt(KEY_CURRENT_CURRENCY, R.id.button_try)
}

    // EUR_TO_TRY

fun setEurToTry(mContext: Context, value: Float) {
    val editor = mContext.getSharedPreferences(KEY_EUR_TO_TRY, Context.MODE_PRIVATE).edit()
    editor.putFloat(KEY_EUR_TO_TRY, value)
    editor.apply()
}

fun getEurToTry(mContext: Context): Float {
    val pref = mContext.getSharedPreferences(KEY_EUR_TO_TRY, Context.MODE_PRIVATE)
    return pref.getFloat(KEY_EUR_TO_TRY, 0F)
}

    // EUR_TO_GBP

fun setEurToGbp(mContext: Context, value: Float) {
    val editor = mContext.getSharedPreferences(KEY_EUR_TO_GBP, Context.MODE_PRIVATE).edit()
    editor.putFloat(KEY_EUR_TO_GBP, value)
    editor.apply()
}

fun getEurToGbp(mContext: Context): Float {
    val pref = mContext.getSharedPreferences(KEY_EUR_TO_GBP, Context.MODE_PRIVATE)
    return pref.getFloat(KEY_EUR_TO_GBP, 0F)
}

    // EUR_TO_USD

fun setEurToUsd(mContext: Context, value: Float) {
    val editor = mContext.getSharedPreferences(KEY_EUR_TO_USD, Context.MODE_PRIVATE).edit()
    editor.putFloat(KEY_EUR_TO_USD, value)
    editor.apply()
}

fun getEurToUsd(mContext: Context): Float {
    val pref = mContext.getSharedPreferences(KEY_EUR_TO_USD, Context.MODE_PRIVATE)
    return pref.getFloat(KEY_EUR_TO_USD, 0F)
}