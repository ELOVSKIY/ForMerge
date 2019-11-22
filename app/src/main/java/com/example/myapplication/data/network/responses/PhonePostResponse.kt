package com.example.myapplication.data.network.responses

import com.google.gson.annotations.SerializedName

data class PhonePostResponse(
        @SerializedName("data")
        val responseData: List<String>,
        val success: Boolean
)