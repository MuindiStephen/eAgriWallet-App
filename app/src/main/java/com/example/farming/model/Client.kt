package com.example.farming.model

/**
 * Client request
 */
data class Client(
 val name: String,
 val email: String,
 val password: String,
 val pinLocation: String
)
