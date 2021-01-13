package com.e.currencyconverter

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.e.currencyconverter.API.GeneralResponse
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response


class MainViewModel
@ViewModelInject
constructor(
    val repository: Repository
): ViewModel() {

    init {
        getAllSupportedCurrencySymbols()
    }
    val supportedCurrencyList: MutableLiveData<Resource<MutableList<String>>> = MutableLiveData()
    val convertedCurrencyValue: MutableLiveData<Resource<GeneralResponse>> =  MutableLiveData()


    fun getConvertedValue(fromCurrency: String, toCurrency: String, value:String){
        viewModelScope.launch {
            val resp = repository.getConvertedValue(fromCurrency, toCurrency, value)
            convertedCurrencyValue.postValue(handleConversionResponse(response = resp))

        }
    }

    fun getAllSupportedCurrencySymbols(){

        viewModelScope.launch {
           val response =  repository.getSupportedCurrencySymbols()
            //Log.d("Result", (response.body()?.string())?:"")
           supportedCurrencyList.postValue(handleSupportedSymbolsResponse(response))
        }
    }


    fun handleConversionResponse(response: Response<GeneralResponse>): Resource<GeneralResponse> {
        if(response.isSuccessful) {
            response.body()?.let { response->
                when (response) {
                    is GeneralResponse.ConvertedValueResponse -> {
                        return Resource.Success(response)
                    }


                    is GeneralResponse.ErrorResponse -> {
                    Log.e("TAG", "Error is ${response.error.info}")
                        return Resource.Error(response.error.info)
                    }
                }

            }
        }
        return Resource.Error("Request failed!")


    }

    fun handleSupportedSymbolsResponse(response: Response<ResponseBody>): Resource<MutableList<String>>{
       val  currencyList :MutableList<String> = mutableListOf()
        if(response.isSuccessful){
            response.body()?.let {responseBody->
                val jsonObject =  JSONObject(responseBody.string())
                jsonObject.let { currencyJson ->
                    val symbolsJson = currencyJson.optJSONObject("symbols")
                    val symbolsArray = symbolsJson?.names()
                    symbolsArray?.let {
                        for(i in 0 until it.length()){
                           // Log.d("val", it.getString(i))
                            currencyList.add(it.getString(i))
                        }
                    }

                }
            }
            //Log.d("currencySym", currencyList.toString())
            return Resource.Success(currencyList)
        }
        else
            return Resource.Error("Currencies not available!")

    }
}