package com.example.expensetracker.expenselist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.*
import com.example.expensetracker.util.*
import com.example.expensetracker.database.Expense
import com.example.expensetracker.database.ExpenseDatabase
import com.example.expensetracker.databinding.FragmentExpenseListBinding

class ExpenseListFragment : Fragment() {

    private lateinit var binding: FragmentExpenseListBinding
    private lateinit var expenseListViewModel: ExpenseListViewModel

    private lateinit var expenseList: List<Expense>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentCurrency = getCurrentCurrency(requireContext())
        eurToTry = getEurToTry(requireContext())
        eurToGbp = getEurToGbp(requireContext())
        eurToUsd = getEurToUsd(requireContext())
        tryToGbp = eurToGbp / eurToTry
        tryToUsd = eurToUsd / eurToTry
        gbpToUsd = eurToUsd / eurToGbp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_expense_list, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ExpenseDatabase.getInstance(application).expenseDatabaseDao

        val viewModelFactory = ExpenseListViewModelFactory(dataSource, application)

        expenseListViewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(ExpenseListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.expenseListViewModel = expenseListViewModel

        expenseListViewModel.response.observe(viewLifecycleOwner, {
            it?.let {
                val size = it.length
                if (size > 40) {
                    setCurrencyValues(it)
                    binding.expenseList.adapter?.notifyDataSetChanged()
                }

            }
        })

        val adapter = ExpenseAdapter(ExpenseListener { expenseId ->
            expenseListViewModel.onExpenseClicked(expenseId)
        })

        binding.expenseList.adapter = adapter

        expenseListViewModel.navigateToExpenseDetail.observe(viewLifecycleOwner, { expense ->
            expense?.let {
                this.findNavController().navigate(
                    ExpenseListFragmentDirections.actionExpenseListFragmentToExpenseDetailFragment(expense))
                expenseListViewModel.onExpenseDetailNavigated()
            }
        })

        expenseListViewModel.expenses.observe(viewLifecycleOwner, {
            it?.let {
                expenseList = it
                setSumText()
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInformation()
        setToggleButtonState()

        binding.cardView.setOnClickListener {
            this.findNavController().navigate(
                ExpenseListFragmentDirections.actionExpenseListFragmentToProfileFragment())
        }

        binding.toggleButtonGroup.addOnButtonCheckedListener { _, checkedId, _ ->
            context?.let { setCurrentCurrency(it, checkedId) }
            currentCurrency = checkedId
            binding.expenseList.adapter?.notifyDataSetChanged()
            setSumText()
        }

        binding.buttonOpenAddScreen.setOnClickListener {
            this.findNavController().navigate(ExpenseListFragmentDirections.actionExpenseListFragmentToExpenseAddFragment())
        }
    }

    private fun setCurrencyValues(values: String) {
        var index: Int = values.indexOf("try", 0)
        var value: Float = values.subSequence(index + 5, index + 10).toString().toFloat()
        context?.let { setEurToTry(it, value) }

        index = values.indexOf("gbp", 0)
        value = values.subSequence(index + 5, index + 10).toString().toFloat()
        context?.let { setEurToGbp(it, value) }

        index = values.indexOf("usd", 0)
        value = values.subSequence(index + 5, index + 10).toString().toFloat()
        context?.let { setEurToUsd(it, value) }
    }


    private fun setUserInformation() {
        var userName = context?.let { getUserName(it) }
        if (userName != null) {
            val gender: String = when (context?.let { getUserGender(it) }) {
                0 -> getString(R.string.card_male)
                1 -> getString(R.string.card_female)
                else -> " "
            }
            userName = "$userName $gender"
            binding.textViewPerson.text = userName
        }
        else {
            binding.textViewPerson.text = getString(R.string.hello_user)
        }
    }

    private fun setToggleButtonState() {

        var currentState = context?.let { getCurrentCurrency(it) }

        if (currentState != null && currentState != -1) {
            binding.toggleButtonGroup.check(currentState)
        }
        else {
            currentState = R.id.button_try
            context?.let { setCurrentCurrency(it, currentState) }
            binding.toggleButtonGroup.check(currentState)
        }
    }

    private fun setSumText() {
        var sum = 0
        val size = expenseList.size
        for (i in 0 until size) {
            sum += convertCurrency(expenseList[i])
        }
        binding.textViewExpense.text = when(currentCurrency) {
            R.id.button_try -> "Harcamanız\n   $sum ₺"
            R.id.button_gbp -> "Harcamanız\n   $sum £"
            R.id.button_eur -> "Harcamanız\n   $sum €"
            else -> "Harcamanız\n   $sum \$"
        }
    }
}