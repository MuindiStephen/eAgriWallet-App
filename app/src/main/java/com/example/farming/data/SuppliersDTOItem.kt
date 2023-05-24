package com.example.farming.data


import com.google.gson.annotations.SerializedName

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
)