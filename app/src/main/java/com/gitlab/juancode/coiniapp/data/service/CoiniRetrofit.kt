package com.gitlab.juancode.coiniapp.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoiniRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.doapps.pe/coini/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: CoiniService = retrofit.create(CoiniService::class.java)
}