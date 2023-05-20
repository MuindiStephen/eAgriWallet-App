
package com.example.farming.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farming.R
import com.example.farming.model.Supplies
import com.example.farming.ui.main.ItemBiddingDetailFragment


/**
 * A recycler view to display a list of all suppliers and more details
 */

class SuppliersAdapter(
    private val context: Context,
    private var itemList: ArrayList<Supplies>
) : RecyclerView.Adapter<SuppliersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_material_and_supplier, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        Glide.with(context)
            .load(item.supplyImage)
            .into(holder.mImage)
        holder.mName.text = item.supplyName
        holder.mPrice.text = "KSh ${item.supplyPrice}"
        holder.rating.text = item.rating
        holder.supplier.text = item.suppliedBy


        holder.mImage.setOnClickListener {

           val alertDialog = AlertDialog.Builder(context)
               .setTitle("Place Bid")
               .setMessage("Do you want to bid this?")
               .setCancelable(true)
               .setPositiveButton("Yes") { dialog: DialogInterface, which:Int ->
                   val intent = Intent(context, ItemBiddingDetailFragment::class.java)
                   context.startActivity(intent)
               }
            alertDialog.create()
            alertDialog.show()
            /**
            val intent = Intent(context, ItemBiddingDetailFragment::class.java)
            intent.putExtra("ITEM_IMAGE",item.supplyImage)
            intent.putExtra("ITEM_NAME",item.supplyName)
            intent.putExtra("ITEM_PRICE",item.supplyPrice)
            intent.putExtra("SUPPLIED_BY",item.suppliedBy)
            intent.putExtra("RATING",item.rating)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(intent)
            */
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mImage: ImageView = view.findViewById(R.id.materialImage)
        val mName: TextView = view.findViewById(R.id.materialName)
        val rating: TextView = view.findViewById(R.id.rating)
        val mPrice: TextView = view.findViewById(R.id.materialPrice)
        val supplier: TextView = view.findViewById(R.id.supplier)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: java.util.ArrayList<Supplies>) {
        itemList = filteredList
        notifyDataSetChanged()
    }
}