package com.example.expensetracker.loadingscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.util.FIRST_TIME
import com.example.expensetracker.util.isFirstLogin

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (FIRST_TIME) {
                this.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFragmentOnboardingOne())
            }
            else {
                this.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToExpenseListFragment())
            }
        }, 1000)
    }
}