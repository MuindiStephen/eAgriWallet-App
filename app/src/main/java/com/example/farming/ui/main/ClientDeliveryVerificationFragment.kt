package com.example.farming.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.farming.R
import com.example.farming.databinding.FragmentClientDeliveryVerificationBinding

class ClientDeliveryVerificationFragment : Fragment() {

    private lateinit var binding:FragmentClientDeliveryVerificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentClientDeliveryVerificationBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
    }

    private fun setUpBinding() {
        binding.buttonConfirm.setOnClickListener {
            verifyDelivery()
        }
    }


    private fun verifyDelivery() {

        // Simulating the comparison
        var secretCode = binding.enterSecretCodeToConfirm.text.toString()
        secretCode = "ABC123"

        if (secretCode.isEmpty()) {
            Toast.makeText(requireActivity(),"Empty Secret code!!!", Toast.LENGTH_SHORT)
                .show()
        } else if (secretCode != "ABC123") {
            // Incorrect secret code entered
            Toast.makeText(requireActivity(),"Delivery verification failed: Incorrect secret code.", Toast.LENGTH_SHORT)
                .show()
        } else {
            // Delivery is confirmed and completed
            Toast.makeText(requireActivity(),"Delivery confirmed: Thank you for verifying the delivery.!", Toast.LENGTH_SHORT)
                .show()
        }


    }
}