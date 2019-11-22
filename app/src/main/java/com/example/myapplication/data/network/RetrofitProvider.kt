package com.example.myapplication.data.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private val BASE_URL = "http://23.105.226.153/"

    private val instance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MyEventAPI::class.java)

    operator fun invoke(): MyEventAPI{
        return instance
    }

}