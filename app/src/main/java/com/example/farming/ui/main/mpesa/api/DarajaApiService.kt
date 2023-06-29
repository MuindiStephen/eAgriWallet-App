package com.example.farming.ui.main.mpesa.api

import com.example.farming.ui.main.mpesa.api.requests.StkPushRequest
import com.example.farming.ui.main.mpesa.api.responses.AuthorizationResponse
import com.example.farming.ui.main.mpesa.api.responses.StkPushSuccessResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface DarajaApiService {

    @POST("mpesa/stkpush/v1/processrequest")
    fun sendPush(
        @Body
        stkPushRequest: StkPushRequest
    ) : Call<StkPushSuccessResponse>

    @GET("oauth/v1/generate?grant_type=client_credentials")
    fun getAccessToken() : Call<AuthorizationResponse>
}