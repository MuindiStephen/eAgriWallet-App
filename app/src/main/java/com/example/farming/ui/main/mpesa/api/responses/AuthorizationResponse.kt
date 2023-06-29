package com.example.farming.ui.main.mpesa.api.responses

import com.google.gson.annotations.SerializedName

data class AuthorizationResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: String
)