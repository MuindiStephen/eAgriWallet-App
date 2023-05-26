package com.example.farming.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentLoginUserTypeBinding

class LoginUserTypeFragment : Fragment() {


    private lateinit var binding:FragmentLoginUserTypeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginUserTypeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLoginAsClient.setOnClickListener {
            findNavController().navigate(R.id.action_loginUserTypeFragment_to_loginFragment2)
        }

        binding.buttonLoginAsFarmerSupplier.setOnClickListener {
            findNavController().navigate(R.id.action_loginUserTypeFragment_to_farmersLoginFragment)
        }
    }
}