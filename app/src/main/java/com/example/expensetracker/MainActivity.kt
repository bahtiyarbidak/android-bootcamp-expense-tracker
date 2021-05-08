package com.example.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expensetracker.network.isOnline
import com.example.expensetracker.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!isOnline()) {
            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_LONG).show()
        }

        val isFirst = isFirstLogin(this)
        if (isFirst || getEurToTry(this) == 0F || getEurToGbp(this) == 0F || getEurToUsd(this) == 0F) {
            saveFirstLogin(this, false)
            FIRST_TIME = true
            // If the internet is off when the application is opened for the first time, the following values are set by default.
            setEurToTry(this, 10F)
            setEurToGbp(this, 0.86F)
            setEurToUsd(this, 1.21F)
        }
        else {
            FIRST_TIME = false
        }
    }
}