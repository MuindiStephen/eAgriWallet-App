package com.example.farming.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Parcelize allows to pass data faster.
 *
 */

@Parcelize
data class SuppliersDTOItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("materialSupply")
    val materialSupply: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("Supplier")
    val supplier: String
) : Parcelable