package com.example.farming.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.farming.R
import com.example.farming.databinding.FragmentInputDetailsBinding
import com.google.android.play.integrity.internal.t

class InputDetailsFragment : Fragment() {

    private lateinit var binding: FragmentInputDetailsBinding
    private val args: InputDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate he layout for this fragment
        binding = FragmentInputDetailsBinding.inflate(inflater, container, false)


        val suppliersItem = args.suppliersDTOItem

        binding.apply {
            Glide.with(requireContext())
                .load(suppliersItem.image)
                .into(inputImage)
            inputName.text = suppliersItem.materialSupply
            inputPrice.text = suppliersItem.price
        }
        setUpBinding()

        return binding.root

    }
    private fun setUpBinding() {
        binding.buttonCheckout.setOnClickListener {
            findNavController().navigate(R.id.action_inputDetailsFragment_to_mpesaPaymentFragment)
        }
    }
}
