package com.e.currencyconverter.API.currency_conversion_response

data class Query(
    val amount: Int,
    val from: String,
    val to: String
)