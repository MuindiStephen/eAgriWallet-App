package com.example.farming.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentMainAuthBinding


class MainAuthFragment : Fragment() {

    private lateinit var binding: FragmentMainAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainAuthBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_mainAuthFragment2_to_loginFragment2)
        }
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_mainAuthFragment2_to_registerUserTypeFragment2)
        }
    }

}