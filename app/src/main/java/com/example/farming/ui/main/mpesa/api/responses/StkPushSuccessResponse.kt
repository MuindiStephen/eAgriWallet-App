package com.example.farming.ui.main.mpesa.api.responses

import com.google.gson.annotations.SerializedName


data class StkPushSuccessResponse (
    @SerializedName("CheckoutRequestID")
    val checkoutRequestID: String,
    @SerializedName("CustomerMessage")
    val customerMessage: String,
    @SerializedName("MerchantRequestID")
    val merchantRequestID: String,
    @SerializedName("ResponseCode")
    val responseCode: String,
    @SerializedName("ResponseDescription")
    val responseDescription: String,
)