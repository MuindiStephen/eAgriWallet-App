package com.example.farming.data

import android.telecom.Call


interface ApiService {

     fun getAllSuppliers() : Call<SuppliersDTO>
}