package com.example.farming.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentItemBiddingDetailBinding
import com.example.farming.model.Bid
import com.example.farming.model.Client
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ItemBiddingDetailFragment : Fragment() {

    private lateinit var binding: FragmentItemBiddingDetailBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    var userId: String? = null
    var userName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentItemBiddingDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //submitBid(supplier,bid)
        (activity as AppCompatActivity).supportActionBar?.hide()

        val database = FirebaseDatabase.getInstance().reference
        firebaseAuth = FirebaseAuth.getInstance()
        userId = firebaseAuth.currentUser?.uid

        fetchCurrentLoggedInUser()

        val materialPrice: EditText = view.findViewById(R.id.materialPrice)

        binding.buttonPlaceBid.setOnClickListener {
            // Login for bidding
            val priceBid: String = binding.enterBidPrice.text.toString()
            val materialSupplied: String = binding.enterMaterialToBid.text.toString()
            val clientName: String = binding.enterMaterialClient.text.toString()


            if (priceBid.isEmpty()) {
                binding.enterBidPrice.error = "Empty price bid"
            }
            else if (materialSupplied.isEmpty()) {
                binding.enterMaterialToBid.error = "Empty Material Supplied"
            }
            else if (clientName.isEmpty()) {
                binding.enterMaterialClient.error = "Empty Supplier"
            }
            else {
                 if (userName != null) {
                     val bid = Bid(clientName,priceBid,materialSupplied)
                     databaseReference.child("bids").push().setValue(bid)
                     Toast.makeText(context?.applicationContext, "Bidding success", Toast.LENGTH_SHORT)
                         .show()
                     findNavController().navigate(R.id.action_itemBiddingDetailFragment_to_successBidFragment)
                 }

            }
        }


    }

    private fun fetchCurrentLoggedInUser() {
        databaseReference.child("Clients").child(FirebaseAuth.getInstance().currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user: Client? = snapshot.getValue(Client::class.java)
                        userName = user?.name
                    } else {
                        Toast.makeText(
                            context?.applicationContext,
                            "Data Snapshot does not exist",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}