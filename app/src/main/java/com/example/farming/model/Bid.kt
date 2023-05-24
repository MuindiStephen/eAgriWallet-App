package com.example.farming.model

class Bid {
    var price:String? = null
    var materialSupplied:String? = null
    var clientName:String? = null

    constructor() {}
    constructor(price:String, materialSupplied:String, clientName: String?) {
        this.price = price
        this.materialSupplied = materialSupplied
        this.clientName = clientName
    }
}