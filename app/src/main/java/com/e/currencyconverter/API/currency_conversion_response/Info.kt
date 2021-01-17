package com.e.currencyconverter.API.currency_conversion_response

import com.google.gson.annotations.Expose

data class Info(
    @Expose
    val rate: Double,
    @Expose
    val timestamp: Int
)