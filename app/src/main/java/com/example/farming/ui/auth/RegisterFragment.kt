package com.example.farming.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentRegisterBinding
import com.example.farming.model.FarmerSupplier
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Farmer / Suppliers SignUp
 */
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        databaseReference =  FirebaseDatabase.getInstance().getReference("FarmerSuppliers")
        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener {
            val fullName: String = binding.enterFullName.text.toString()
            val email: String = binding.enterEmail.text.toString()
            val password: String = binding.enterPassword.text.toString()
            val supplies: String = binding.enterSupply.text.toString()
            val suppliesPrice:String = binding.enterMaterialSupplyPrice.text.toString()

            if (fullName.isEmpty()) {
                binding.enterFullName.error = "Empty Username"
            }
            else if (email.isEmpty()) {
                binding.enterEmail.error = ("Empty Email")
            }
            else if (password.isEmpty() || password.length<8) {
                binding.enterPassword.error = ("Password too weak or password empty")
            }
           else if (supplies.isEmpty()) {
                binding.enterSupply.error = ("Empty Username")
            }
            else if (suppliesPrice.isEmpty()) {
                binding.enterMaterialSupplyPrice.error = ("Empty Material Price")
            }
            else{

                binding.registerProgressbar.visibility = View.VISIBLE

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId:String = firebaseAuth.currentUser!!.uid
                        val farmerSupplier = FarmerSupplier(fullName,email, password, supplies,suppliesPrice)

                        databaseReference.child(userId).setValue(farmerSupplier)

                        Toast.makeText(requireContext(),"Account created successfully!",Toast.LENGTH_SHORT).show()

                        firebaseAuth.signOut()

                        binding.registerProgressbar.visibility = View.INVISIBLE

                        findNavController().navigate(R.id.action_registerFragment2_to_farmersLoginFragment)
                    } else {
                           binding.registerProgressbar.visibility = View.INVISIBLE
                           Toast.makeText(requireContext(),task.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.textViewHaveAcc.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment2_to_farmersLoginFragment)
        }

    }
}