package com.example.farming.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentFarmersLoginBinding
import com.google.firebase.auth.FirebaseAuth


class FarmersLoginFragment : Fragment() {

    private lateinit var binding: FragmentFarmersLoginBinding
    var firebaseAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFarmersLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()
        firebaseAuth = FirebaseAuth.getInstance()

        binding.textViewDontHaveAcc.setOnClickListener {
            findNavController().navigate(R.id.action_farmersLoginFragment_to_registerFragment2)
        }

        binding.buttonSignIn.setOnClickListener {
            val email: String = binding.enterLoginEmail.text.toString()
            val password: String = binding.enterLoginPassword.text.toString()

            if (email.isEmpty()){
                binding.enterLoginEmail.error = "Empty Email";
            }
            else if (password.isEmpty()){
                binding.enterLoginPassword.error = "Empty Password";
            }else{
                firebaseAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Successful Login...", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_farmersLoginFragment_to_deliveryReportMaterialFragment2)
                    } else {
                        Toast.makeText(requireContext(), "UnSuccessful To Login!!", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
        }
    }
}