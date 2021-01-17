package com.e.currencyconverter.API.error_response

import com.google.gson.annotations.Expose

data class Error(
    val code: Int,
    val type: String,
    val info: String
)