package com.gitlab.juancode.coiniapp.data.datasource

import com.gitlab.juancode.coiniapp.entity.*
import com.google.gson.JsonObject

interface RemoteDataSource {
    suspend fun getFlags(): List<Flag>
    suspend fun verificationPhone(jsonObject: JsonObject): Response
    suspend fun createUser(jsonObject: JsonObject): User
    suspend fun login(jsonObject: JsonObject): UserId
    suspend fun getBalance(userId: String): Balance
}