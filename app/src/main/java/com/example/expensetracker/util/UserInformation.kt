package com.example.expensetracker.util

import android.content.Context

var FIRST_TIME = true
const val KEY_IS_FIRST = "key_is_login"
const val KEY_USER_NAME = "key_user_name"
const val KEY_USER_GENDER = "key_user_gender"

fun saveFirstLogin(mContext: Context, isFirst: Boolean) {
    val editor = mContext.getSharedPreferences(KEY_IS_FIRST, Context.MODE_PRIVATE).edit()
    editor.putBoolean(KEY_IS_FIRST, isFirst)
    editor.apply()
}

fun isFirstLogin(mContext: Context): Boolean {
    val pref = mContext.getSharedPreferences(KEY_IS_FIRST, Context.MODE_PRIVATE)
    return pref.getBoolean(KEY_IS_FIRST, true)
}


    // User Name

fun saveUserName(mContext: Context, userName: String) {
    val editor = mContext.getSharedPreferences(KEY_USER_NAME, Context.MODE_PRIVATE).edit()
    editor.putString(KEY_USER_NAME, userName)
    editor.apply()
}

fun getUserName(mContext: Context): String? {
    val pref = mContext.getSharedPreferences(KEY_USER_NAME, Context.MODE_PRIVATE)
    return pref.getString(KEY_USER_NAME, null)
}


    // Gender

fun saveUserGender(mContext: Context, userGender: Int) {
    val editor = mContext.getSharedPreferences(KEY_USER_GENDER, Context.MODE_PRIVATE).edit()
    editor.putInt(KEY_USER_GENDER, userGender)
    editor.apply()
}

fun getUserGender(mContext: Context): Int {
    val pref = mContext.getSharedPreferences(KEY_USER_GENDER, Context.MODE_PRIVATE)
    return pref.getInt(KEY_USER_GENDER, -1)
}