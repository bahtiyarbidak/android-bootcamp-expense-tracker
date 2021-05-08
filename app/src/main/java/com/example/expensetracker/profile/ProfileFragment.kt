package com.example.expensetracker.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.*
import com.example.expensetracker.databinding.FragmentProfileBinding
import com.example.expensetracker.util.*
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var textName: EditText
    private lateinit var radioGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textName = binding.textName
        radioGroup = binding.radioGroupProfile

        fillThePage()

        binding.updateUserButton.setOnClickListener {

            val userName = textName.text.toString()

            if (userName.isEmpty() || binding.radioGroupProfile.checkedRadioButtonId == -1) {
                Snackbar.make(view, R.string.empty_field_warning, Snackbar.LENGTH_LONG)
                    .setAction(R.string.ok) {
                        // Responds to click on the action
                    }
                    .show()
            } else {
                saveUser(userName)
                this.findNavController()
                    .navigate(ProfileFragmentDirections.actionProfileFragmentToExpenseListFragment())
            }
        }
    }

    private fun fillThePage() {
        val userName = context?.let { getUserName(it) }
        if (userName != null) {
            textName.setText(userName)

            val gender: Int = when (context?.let { getUserGender(it) }) {
                0 -> R.id.radio_male
                1 -> R.id.radio_female
                else -> R.id.radio_unknown
            }
            radioGroup.check(gender)
        }
    }

    private fun saveUser(userName: String) {
        context?.let { it1 -> saveUserName(it1, userName) }

        val gender: Int = when (binding.radioGroupProfile.checkedRadioButtonId) {
            R.id.radio_male -> 0
            R.id.radio_female -> 1
            else -> -1
        }
        context?.let { saveUserGender(it, gender) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}