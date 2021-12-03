package com.gitlab.juancode.coiniapp.data.service

import com.gitlab.juancode.coiniapp.entity.Flag
import retrofit2.http.GET

interface CoiniService {

    @GET("country.json")
    suspend fun getCountries(): List<Flag>
}