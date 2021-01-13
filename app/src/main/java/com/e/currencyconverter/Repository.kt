package com.e.currencyconverter

import android.content.Context
import android.provider.Settings.Global.getString
import com.e.currencyconverter.API.FixerApiEndPoint
import com.e.currencyconverter.API.GeneralResponse
import com.e.currencyconverter.Constants.Companion.API_KEY
import com.google.gson.JsonObject
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class Repository
@Inject
constructor(
    val fixerApiEndPoint: FixerApiEndPoint
){

    suspend fun getConvertedValue(
                                  fromCurrency:String,
                                  toCurrency: String,
                                  amount: String
    ): Response<GeneralResponse>{
    return fixerApiEndPoint.convertCurrency(fromCurrency, toCurrency, amount)
    }

    suspend fun getSupportedCurrencySymbols(): Response<ResponseBody> {
         return fixerApiEndPoint.getAllSupportedCurrency()
    }
}

