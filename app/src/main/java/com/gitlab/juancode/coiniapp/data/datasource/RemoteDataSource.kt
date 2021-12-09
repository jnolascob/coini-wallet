package com.gitlab.juancode.coiniapp.data.datasource

import com.gitlab.juancode.coiniapp.entity.Flag
import com.gitlab.juancode.coiniapp.entity.Response
import com.gitlab.juancode.coiniapp.entity.User
import com.gitlab.juancode.coiniapp.entity.UserId
import com.google.gson.JsonObject

interface RemoteDataSource {
    suspend fun getFlags(): List<Flag>
    suspend fun verificationPhone(jsonObject: JsonObject): Response
    suspend fun createUser(jsonObject: JsonObject): User
    suspend fun login(jsonObject: JsonObject): UserId
}