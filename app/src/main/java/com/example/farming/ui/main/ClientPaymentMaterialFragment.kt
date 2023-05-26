package com.example.farming.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentClientPaymentMaterialBinding


class ClientPaymentMaterialFragment : Fragment() {

    private lateinit var binding: FragmentClientPaymentMaterialBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClientPaymentMaterialBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.payMpesa.setOnClickListener {
            findNavController().navigate(R.id.action_clientPaymentMaterialFragment2_to_mpesaPaymentFragment)
        }
        binding.payByPaypal.setOnClickListener {
            Toast.makeText(requireContext(),"Feature Coming soon...",Toast.LENGTH_LONG).show()
        }
    }
}