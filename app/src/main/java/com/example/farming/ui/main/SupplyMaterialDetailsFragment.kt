package com.example.farming.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.farming.R
import com.example.farming.databinding.FragmentSupplyMaterialDetailsBinding

class SupplyMaterialDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSupplyMaterialDetailsBinding

    private val args:SupplyMaterialDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSupplyMaterialDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val suppliersItem = args.suppliersitem

        binding.apply {
            Glide.with(requireContext())
                .load(suppliersItem.image)
                .into(materialImage)
            materialSupplyText.text = suppliersItem.materialSupply
            priceText.text = suppliersItem.price
            supplierText.text = suppliersItem.supplier
            ratingText.text = suppliersItem.rating
        }

        setUpBinding()
    }

    private fun setUpBinding() {
        binding.buttonCheckout.setOnClickListener {
            findNavController().navigate(R.id.action_supplyMaterialDetailsFragment_to_itemBiddingDetailFragment2)
        }
    }

}