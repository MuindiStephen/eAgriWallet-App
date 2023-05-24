package com.example.farming.data

import com.example.farming.utils.MOBILE_KILIMO_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(MOBILE_KILIMO_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api by lazy { retrofit.create(ApiService::class.java) }
}