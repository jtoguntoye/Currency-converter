package com.e.currencyconverter.API

import retrofit2.http.GET

interface FixerApiEndPoint {


    @GET("/convert")
    fun convertCurrency()

    fun getAllSupportedCurrency()


}