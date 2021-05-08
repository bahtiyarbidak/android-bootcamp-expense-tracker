package com.example.expensetracker.expenseadd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.database.Expense
import com.example.expensetracker.database.ExpenseDatabase
import com.example.expensetracker.databinding.FragmentExpenseAddBinding
import com.google.android.material.snackbar.Snackbar

class ExpenseAddFragment : Fragment() {

    private lateinit var binding: FragmentExpenseAddBinding
    private lateinit var expenseAddViewModel: ExpenseAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_expense_add, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ExpenseDatabase.getInstance(application).expenseDatabaseDao
        val viewModelFactory = ExpenseAddViewModelFactory(dataSource, application)

        expenseAddViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ExpenseAddViewModel::class.java)

        binding.expenseAddViewModel = expenseAddViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val textExplanation = binding.textExplanation
        val textAmount = binding.textExpense

        var explanation: String
        var amountOfExp: String

        binding.addExpenseButton.setOnClickListener {

            explanation = textExplanation.text.toString()
            amountOfExp = textAmount.text.toString()

            if (explanation.isEmpty() || amountOfExp.isEmpty() ||
                binding.radioGroup1.checkedRadioButtonId == -1 || binding.radioGroup2.checkedRadioButtonId == -1) {
                Snackbar.make(view, R.string.empty_field_warning, Snackbar.LENGTH_LONG)
                    .setAction(R.string.ok) {}
                    .show()
            } else {
                insertNewValues(explanation, amountOfExp)
                this.findNavController()
                    .navigate(ExpenseAddFragmentDirections.actionExpenseAddFragmentToExpenseListFragment())
            }
        }
    }

    private fun insertNewValues(
        explanation: String,
        amountOfExp: String,
    ) {
        val expenseType: Int = when (binding.radioGroup1.checkedRadioButtonId) {
            R.id.radio_rent -> 0
            R.id.radio_bill -> 1
            else -> 2
        }

        val currencyType: Int = when (binding.radioGroup2.checkedRadioButtonId) {
            R.id.radio_try -> R.id.button_try
            R.id.radio_gbp -> R.id.button_gbp
            R.id.radio_eur -> R.id.button_eur
            else -> R.id.button_usd
        }

        val expense =
            Expense(0, expenseType, explanation, Integer.parseInt(amountOfExp), currencyType)
        expenseAddViewModel.addNewExpense(expense)
    }
}