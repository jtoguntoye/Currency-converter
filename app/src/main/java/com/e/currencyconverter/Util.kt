package com.e.currencyconverter

class Constants {

    companion object{
        const val BASE_URL = "http://data.fixer.io/api/"
        const val  API_KEY = "YOUR-API-KEY-HERE"
    }
}

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
