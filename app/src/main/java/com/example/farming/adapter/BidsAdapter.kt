package com.example.farming.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farming.R
import com.example.farming.model.Bid
import com.google.android.play.core.integrity.p


class BidsAdapter : RecyclerView.Adapter<BidsAdapter.BidViewHolder>() {

    var bidList: List<Bid>? = null

    fun PostsAdapter(bidList: List<Bid>?) {
        this.bidList = bidList
    }

     class BidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var price: TextView
        var material: TextView
        var client: TextView

        init {

            price = itemView.findViewById(R.id.bidPrice)
            material = itemView.findViewById(R.id.bidMaterial)
            client = itemView.findViewById(R.id.bidClient)

        }

        @SuppressLint("SetTextI18n")
        fun bind(bid: Bid) {
            price.text = bid.price
            material.text = bid.materialSupplied
            client.text = "Bid by Client: " + bid.clientName
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidViewHolder {
        return BidViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_bids_list,parent,false
        ))
    }

    override fun onBindViewHolder(holder: BidViewHolder, position: Int) {
        val bid = bidList?.get(position)
        holder.bind(bid!!)
    }

    override fun getItemCount(): Int {
        return bidList?.size ?: 0
    }

}