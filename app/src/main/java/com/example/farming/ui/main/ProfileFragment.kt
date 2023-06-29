package com.example.farming.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding

    var firebaseAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        // Current Time
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        val greeting = when (currentHour) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            else -> "Good Evening"
        }


         binding.textView26.text = greeting

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth!!.currentUser

        // If user is not null, retrieve their profile
        if (currentUser != null) {
            binding.textView21.text = currentUser.email.toString()
        }
        else {
            Log.e(TAG, "An error,occurred while retrieving your profile")
        }
        binding.textView23.setOnClickListener {
            firebaseAuth!!.signOut()
            findNavController().navigate(R.id.action_profileFragment2_to_mainAuthFragment2)
        }

        binding.textViewGoHome.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment2_to_dashboardFragment2)
        }

    }
    companion object {
        const val TAG = "ProfileFragment"
    }
}