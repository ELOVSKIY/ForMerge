package com.example.myapplication.data.network

import com.example.myapplication.data.network.request.PhonePostRequest
import com.example.myapplication.data.network.responses.PhonePostResponse
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body

interface MyEventAPI {

    @POST("user")
    suspend fun postPhoneNumb(
            @Body body: PhonePostRequest
            ): Call<PhonePostResponse>

}