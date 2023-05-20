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
import com.example.farming.databinding.FragmentRegisterClientBinding
import com.example.farming.model.Client
import com.example.farming.model.FarmerSupplier
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterClientFragment : Fragment() {

    private lateinit var binding: FragmentRegisterClientBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterClientBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        databaseReference =  FirebaseDatabase.getInstance().getReference("Clients")
        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener {
            val fullName: String = binding.enterName.text.toString()
            val email: String = binding.enterEmail.text.toString()
            val password: String = binding.enterPasswd.text.toString()
            val location: String = binding.enterPinLocation.text.toString()

            if (fullName.isEmpty()) {
                binding.enterName.error = "Empty Name"
            }
            else if (email.isEmpty()) {
                binding.enterEmail.error = ("Empty Email")
            }
            else if (password.isEmpty() || password.length<8) {
                binding.enterPasswd.error = ("Password too weak or password empty")
            }
            else if (location.isEmpty()) {
                binding.enterPinLocation.error = ("Empty Location")
            }
            else{

                binding.registerProgressbar.visibility = View.VISIBLE

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId:String = firebaseAuth.currentUser!!.uid
                        val farmerSupplier = Client(fullName,email, password, location)

                        databaseReference.child(userId).setValue(farmerSupplier)

                        Toast.makeText(requireContext(),"Account created successfully!", Toast.LENGTH_SHORT).show()

                        firebaseAuth.signOut()

                        binding.registerProgressbar.visibility = View.INVISIBLE

                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        binding.registerProgressbar.visibility = View.INVISIBLE
                        Toast.makeText(requireContext(),task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }

        binding.textViewHaveAcc.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }
}