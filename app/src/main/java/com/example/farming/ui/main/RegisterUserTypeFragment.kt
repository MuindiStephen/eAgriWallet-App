package com.example.farming.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentRegisterUserTypeBinding

class RegisterUserTypeFragment : Fragment() {

    private lateinit var binding: FragmentRegisterUserTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterUserTypeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        binding.buttonRegisterAsClient.setOnClickListener {
            findNavController().navigate(R.id.action_registerUserTypeFragment_to_registerClientFragment)
        }
        binding.buttonRegisterAsFarmerSupplier.setOnClickListener {
            findNavController().navigate(R.id.action_registerUserTypeFragment2_to_registerFragment2)
        }
    }
}