package com.example.farming.model

/**
 * Farmer Request
 */
            data class FarmerSupplier(
                    val name: String,
                    val email: String,
                    val password: String,
                    val supplies: String,
                    val suppliesMaterial:String
                 )