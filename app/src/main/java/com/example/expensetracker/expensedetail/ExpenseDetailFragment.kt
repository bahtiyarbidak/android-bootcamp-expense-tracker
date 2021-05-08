package com.example.expensetracker.expensedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.database.ExpenseDatabase
import com.example.expensetracker.databinding.FragmentExpenseDetailBinding
import com.example.expensetracker.util.currentCurrency

class ExpenseDetailFragment : Fragment() {

    private lateinit var binding: FragmentExpenseDetailBinding
    private lateinit var expenseDetailViewModel: ExpenseDetailViewModel
    private lateinit var arguments: ExpenseDetailFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_expense_detail, container, false)

        val application = requireNotNull(this.activity).application
        arguments = ExpenseDetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = ExpenseDatabase.getInstance(application).expenseDatabaseDao
        val viewModelFactory = ExpenseDetailViewModelFactory(arguments.expenseKey, dataSource)

        expenseDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ExpenseDetailViewModel::class.java)

        binding.expenseDetailViewModel = expenseDetailViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deleteButton.setOnClickListener {
            expenseDetailViewModel.deleteExpense()
            this.findNavController().navigate(ExpenseDetailFragmentDirections.actionExpenseDetailFragmentToExpenseListFragment())
        }

        binding.detailTextViewCurrency.text = when(currentCurrency) {
            R.id.button_try -> "₺"
            R.id.button_gbp -> "£"
            R.id.button_eur -> "€"
            else -> "\$"
        }
    }
}