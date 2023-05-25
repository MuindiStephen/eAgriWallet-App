package com.example.farming.model


/**
 * A data class to hold data to be stored in the firebase and retrieval
 */
class Bid {
    var price:String? = null
    var materialSupplied:String? = null
    var clientName:String? = null

    constructor() {}
    constructor(price:String?, materialSupplied:String?, clientName: String?) {
        this.price = price
        this.materialSupplied = materialSupplied
        this.clientName = clientName
    }
}