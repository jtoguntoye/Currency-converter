package com.e.currencyconverter.API

import com.e.currencyconverter.API.currency_conversion_response.Info
import com.e.currencyconverter.API.currency_conversion_response.Query
import com.e.currencyconverter.API.error_response.Error
import com.google.gson.annotations.Expose


     data class ConvertionResponse(
         val date: String? = null,
         val historical: String? = "",
         val info: Info? = null,
         val query: Query? = null,
         val result: Double? = 0.0,
         val success: Boolean? = false,
        val error: Error? = null

     )