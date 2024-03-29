package com.example.farming.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.adapter.AllBidsAdapter
import com.example.farming.databinding.FragmentListMateialBidsBinding
import com.example.farming.model.Bid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ListMateialBidsFragment : Fragment() {

    private lateinit var binding:FragmentListMateialBidsBinding
    var bidList: ArrayList<Bid> = ArrayList()
    var bidAdapter: AllBidsAdapter? = null
    var databaseReference: DatabaseReference? = null
    var firebaseAuth: FirebaseAuth? = null

     // private var buttonApprove: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListMateialBidsBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        // Initializing the Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("bids")

        getAllBids()

        setUpBinding()


        // Approve and Delivery of the Bid Material
        /**
       buttonApprove = view.findViewById(R.id.buttonApprove)

        buttonApprove!!.setOnClickListener {
            findNavController().navigate(R.id.action_listMateialBidsFragment_to_deliveryReportMaterialFragment)
        }
       */

    }

    private fun setUpBinding() {
        binding.goBackHome.setOnClickListener {
            findNavController().navigate(R.id.action_listMateialBidsFragment2_to_dashboardFragment2)
        }

        binding.proceedToPay.setOnClickListener {
            findNavController().navigate(R.id.action_listMateialBidsFragment2_to_clientPaymentMaterialFragment2)
        }
    }


    private fun getAllBids() {
       // bidList.clear()

        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val bid: Bid? = i.getValue(Bid::class.java)
                        bidList.add(bid!!)
                    }
                    bidAdapter = AllBidsAdapter()
                    bidAdapter!!.submitList(bidList)
                    binding.bidsRecyclerView.adapter = bidAdapter
                    Toast.makeText(
                        requireContext(),
                        "Alert! Available bids from your clients",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    Toast.makeText(
                        requireContext(),
                        "No bids yet",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })


    }
}