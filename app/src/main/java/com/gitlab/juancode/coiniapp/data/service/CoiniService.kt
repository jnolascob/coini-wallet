package com.gitlab.juancode.coiniapp.data.service

import com.gitlab.juancode.coiniapp.entity.*
import com.google.gson.JsonObject
import retrofit2.http.*

interface CoiniService {

    @GET
    suspend fun getCountries(@Url url: String = "https://coini-assets.s3.us-east-2.amazonaws.com/country.json"): List<Flag>

    @POST("verifyphone")
    suspend fun verificationPhone(@Body jsonObject: JsonObject): Response

    @POST("user")
    suspend fun createUser(@Body jsonObject: JsonObject): User

    @POST("login")
    suspend fun login(@Body jsonObject: JsonObject): UserId

    @GET("balance")
    suspend fun getBalance(@Query("userId") userId: String): Balance
}