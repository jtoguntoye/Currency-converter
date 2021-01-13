package com.e.currencyconverter.API

import com.e.currencyconverter.API.currency_conversion_response.Info
import com.e.currencyconverter.API.currency_conversion_response.Query
import com.e.currencyconverter.API.error_response.Error


sealed class GeneralResponse{

    data class ConvertedValueResponse(
        var date: String,
        var historical: String,
        var info: Info,
        var query: Query,
        val result: Double,
        val success: Boolean
    ): GeneralResponse()


    data class ErrorResponse(
        val error: Error,
        val success: Boolean
    ): GeneralResponse()
}