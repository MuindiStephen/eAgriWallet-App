package com.example.farming.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farming.data.SuppliersDTOItem
import com.example.farming.databinding.ItemMaterialAndSupplierBinding

class FarmersAdapter: ListAdapter<SuppliersDTOItem, FarmersAdapter.MyViewHolder>(MyDiffUtil) {

    object MyDiffUtil: DiffUtil.ItemCallback<SuppliersDTOItem>() {
        override fun areItemsTheSame(oldItem: SuppliersDTOItem, newItem: SuppliersDTOItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SuppliersDTOItem, newItem: SuppliersDTOItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class MyViewHolder(private val binding: ItemMaterialAndSupplierBinding)
        : RecyclerView.ViewHolder(binding.root) //Returns outermost View in the associated layout.
    {
        @SuppressLint("SetTextI18n")
        fun bind(supply: SuppliersDTOItem) {
            binding.materialPrice.text = supply.price
            binding.materialName.text = supply.materialSupply
            binding.rating.text = "Rating:${supply.rating}"
            binding.supplier.text = supply.supplier
            Glide.with(binding.materialImage).load(supply.image).into(binding.materialImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemMaterialAndSupplierBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val supply = getItem(position)
        holder.bind(supply)

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