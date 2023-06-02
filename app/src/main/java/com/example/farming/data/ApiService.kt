package com.example.farming.data

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
     @GET("inputs")
     fun getAllSuppliers() : Call<ArrayList<SuppliersDTOItem>>
}