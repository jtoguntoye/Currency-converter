package com.e.currencyconverter

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.e.currencyconverter.API.ConvertionResponse
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
    val convertedCurrencyValue: MutableLiveData<Resource<ConvertionResponse>> =  MutableLiveData()


    fun getConvertedValue(fromCurrency: String, toCurrency: String, value:String){
        viewModelScope.launch {
            val resp = repository.getConvertedValue(fromCurrency, toCurrency, value)
            Log.d("result", resp.toString())
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


    fun handleConversionResponse(response: Response<ConvertionResponse>): Resource<ConvertionResponse> {
        if(response.isSuccessful) {
            response.body()?.let { response->
                if(response.success == true)
                return Resource.Success(response)
                 }
        }
        return Resource.Error("Conversion Failed")
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
            return Resource.Success(currencyList)
        }
        else
            return Resource.Error("Currencies not available!")

    }
}