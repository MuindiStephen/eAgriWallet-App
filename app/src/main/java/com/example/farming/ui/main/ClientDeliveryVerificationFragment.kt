package com.example.farming.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.farming.R
import com.example.farming.databinding.FragmentClientDeliveryVerificationBinding

class ClientDeliveryVerificationFragment : Fragment() {

    private lateinit var binding:FragmentClientDeliveryVerificationBinding
    val secretCode = "ABC123"

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
        verifyDelivery(secretCode)
    }

    private fun verifyDelivery(code: String) {
        // Simulating the comparison
        val secretCodeFromSupplier = "ABC123" // Replace with the secret code received from the supplier
        if (code == secretCodeFromSupplier) {
            // Delivery is confirmed and completed
            println("Delivery confirmed: Thank you for verifying the delivery.")
            // Proceed with further actions, such as payment or additional processing
        } else {
            // Incorrect code entered
            println("Delivery verification failed: Incorrect secret code.")
            // Handle the failure scenario, such as displaying an error message to the customer
        }

    }
}