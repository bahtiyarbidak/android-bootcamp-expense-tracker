package com.example.expensetracker.loadingscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.databinding.FragmentOnboardingOneBinding

class FragmentOnboardingOne : Fragment() {

    private var _binding: FragmentOnboardingOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOnboardingOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOne.setOnClickListener {
            this.findNavController().navigate(FragmentOnboardingOneDirections.actionFragmentOnboardingOneToFragmentOnboardingTwo())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}