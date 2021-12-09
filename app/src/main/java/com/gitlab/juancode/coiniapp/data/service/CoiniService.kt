package com.gitlab.juancode.coiniapp.data.service

import com.gitlab.juancode.coiniapp.entity.Flag
import com.gitlab.juancode.coiniapp.entity.Response
import com.gitlab.juancode.coiniapp.entity.User
import com.gitlab.juancode.coiniapp.entity.UserId
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoiniService {

    @GET("country.json")
    suspend fun getCountries(): List<Flag>

    @POST("verifyphone")
    suspend fun verificationPhone(@Body jsonObject: JsonObject): Response

    @POST("user")
    suspend fun createUser(@Body jsonObject: JsonObject): User

    @POST("login")
    suspend fun login(@Body jsonObject: JsonObject): UserId
}