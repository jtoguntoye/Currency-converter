package com.e.currencyconverter.API.currency_conversion_response

import com.google.gson.annotations.Expose

data class Query(
    @Expose
    val amount: Int,
    @Expose
    val from: String,
    @Expose
    val to: String
)