package com.e.currencyconverter

import com.e.currencyconverter.API.FixerApiEndPoint
import com.e.currencyconverter.API.ConvertionResponse
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
    ): Response<ConvertionResponse>{
    return fixerApiEndPoint.convertCurrency(fromCurrency = fromCurrency, toCurrency = toCurrency, amount =  amount)
    }

    suspend fun getSupportedCurrencySymbols(): Response<ResponseBody> {
         return fixerApiEndPoint.getAllSupportedCurrency()
    }
}

