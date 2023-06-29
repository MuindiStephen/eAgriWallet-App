package com.example.farming.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.example.farming.R
import com.example.farming.databinding.FragmentSuccessBidBinding

class SuccessBidFragment : Fragment() {
    private lateinit var binding: FragmentSuccessBidBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSuccessBidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        setUpBinding()
        setUpAnimation()
    }

    private fun setUpAnimation() {
        binding.apply {
            lottieAnimationView.setAnimation(R.raw.ic_success)
            lottieAnimationView.repeatCount = LottieDrawable.INFINITE
            lottieAnimationView.playAnimation()
        }
    }

    private fun setUpBinding() {
        binding.buttonGoBackDashboard.setOnClickListener {
            findNavController().navigate(R.id.action_successBidFragment2_to_dashboardFragment2)
        }
    }
}