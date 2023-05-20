package com.example.farming.adapter

import android.R
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farming.model.Bid


class BidsAdapter : RecyclerView.Adapter<BidsAdapter.BidViewHolder>() {


     class BidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var price: TextView
        lateinit var material: TextView
        lateinit var client: TextView

        init {
            /**  TODO
            price = itemView.findViewById(R.id.textViewPostTitle)
            material = itemView.findViewById(R.id.textViewPostDescription)
            client = itemView.findViewById(R.id.textViewPostOwner)
            */
        }

        @SuppressLint("SetTextI18n")
        fun bind(bid: Bid) {
            price.text = bid.price
            material.text = bid.materialSupplied
            client.text = "Client: " + bid.clientName
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BidViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}