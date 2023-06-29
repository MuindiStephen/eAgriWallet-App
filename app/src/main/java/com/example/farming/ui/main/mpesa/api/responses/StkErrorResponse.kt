package com.example.farming.ui.main.mpesa.api.responses

import com.google.gson.annotations.SerializedName

data class StkErrorResponse(
    @SerializedName("requestId")
    val requestId: String,
    @SerializedName("errorCode")
    val errorCode:String,
    @SerializedName("errorMessage")
    val errorMessage:String

)