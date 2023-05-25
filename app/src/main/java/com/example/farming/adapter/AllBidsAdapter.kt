package com.example.farming.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.farming.databinding.ItemBidsListBinding
import com.example.farming.model.Bid


/**
 * Adapter is Working noew
 */
class AllBidsAdapter: ListAdapter<Bid, AllBidsAdapter.MyViewHolder>(MyDiffUtil) {

    object MyDiffUtil: DiffUtil.ItemCallback<Bid>() {
        override fun areItemsTheSame(oldItem: Bid, newItem: Bid): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Bid, newItem: Bid): Boolean {
            return oldItem.clientName == newItem.clientName
        }

    }

    inner class MyViewHolder(private val binding: ItemBidsListBinding)
        : RecyclerView.ViewHolder(binding.root) //Returns outermost View in the associated layout.
    {
        @SuppressLint("SetTextI18n")
        fun bind(bid: Bid) {
            binding.bidClient.text = bid.clientName
            binding.bidPrice.text = bid.price
            binding.bidMaterial.text = bid.materialSupplied
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemBidsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bid = getItem(position)
        holder.bind(bid)

        /**
        holder.itemView.setOnClickListener {

        val alertDialog = AlertDialog.Builder(it.context)
        .setTitle("Place Bid")
        .setMessage("Do you want to bid this?")
        .setCancelable(true)
        .setNegativeButton("No") {dialog: DialogInterface, which:Int ->
        dialog.dismiss()
        }
        .setPositiveButton("Yes") { dialog: DialogInterface, which:Int ->
        Navigation.findNavController(view = View(it.context))
        .navigate(R.id.action_dashboardFragment2_to_itemBiddingDetailFragment2)
        }

        alertDialog.create()
        alertDialog.show()
        }
         */

    }
}