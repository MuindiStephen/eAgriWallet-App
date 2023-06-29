package com.example.farming.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        val userLoggedIn = FirebaseAuth.getInstance().currentUser

        Handler(Looper.getMainLooper()).postDelayed ({

           // findNavController().navigate(R.id.action_splashFragment2_to_mainAuthFragment2)

            /**
             * Firebase
             * @userLoggedIn Checks if the user is logged in or not
             */
             if (userLoggedIn != null) {
                 // When the user is already logged in
                 findNavController().navigate(R.id.action_splashFragment2_to_dashboardFragment2)
             }
            else {
                 findNavController().navigate(R.id.action_splashFragment2_to_mainAuthFragment2)
             }

        },3000)
    }
}