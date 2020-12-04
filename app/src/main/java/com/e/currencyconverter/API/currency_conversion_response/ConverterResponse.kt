package com.e.currencyconverter.API.currency_conversion_response

data class ConverterResponse(
    var date: String,
    var historical: String,
    var info: Info,
    var query: Query,
    val result: Double,
    val success: Boolean
)