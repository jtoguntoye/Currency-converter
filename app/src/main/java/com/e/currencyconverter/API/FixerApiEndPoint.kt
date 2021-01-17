package com.e.currencyconverter.API

import com.e.currencyconverter.Constants.Companion.API_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FixerApiEndPoint {

    @GET("convert")
    suspend fun convertCurrency(
        @Query("access_key") apiKey: String = API_KEY,
        @Query("from") fromCurrency: String = "USD",
        @Query("to") toCurrency: String = "GBP",
        @Query("amount") amount:String = "1"
    ): Response<ConvertionResponse>



    @GET("symbols")
    suspend fun getAllSupportedCurrency(
        @Query("access_key") apiKey: String = API_KEY
    ): Response<ResponseBody>

}