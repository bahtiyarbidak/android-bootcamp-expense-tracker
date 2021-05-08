package com.example.expensetracker.expenselist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.expensetracker.*
import com.example.expensetracker.util.*
import com.example.expensetracker.database.Expense

@BindingAdapter("explanationString")
fun TextView.setExplanationString(item: Expense?) {
    item?.let {
        text = item.explanation
    }
}

@BindingAdapter("amountString")
fun TextView.setAmountString(item: Expense?) {
    item?.let {
        val amount = convertCurrency(item)
        text = amount.toString()
    }
}

@BindingAdapter("typeImage")
fun ImageView.setTypeImage(item: Expense?) {
    item?.let {
        setImageResource(
            when (item.expenseType) {
                0 -> R.drawable.outline_home_64
                1 -> R.drawable.outline_receipt_long_64
                else -> R.drawable.outline_shopping_bag_64
            }
        )
    }
}