package com.example.expensetracker.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CurrencyApiService {
    @GET("latest/currencies/eur/try.min.json")
    suspend fun getEurTry(): String
    @GET("latest/currencies/eur/gbp.min.json")
    suspend fun getEurGbp(): String
    @GET("latest/currencies/eur/usd.min.json")
    suspend fun getEurUsd(): String
}

object CurrencyApi {
    val retrofitService : CurrencyApiService by lazy {
        retrofit.create(CurrencyApiService::class.java) }
}