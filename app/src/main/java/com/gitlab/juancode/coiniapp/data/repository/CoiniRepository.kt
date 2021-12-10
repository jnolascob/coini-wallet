package com.gitlab.juancode.coiniapp.data.repository

import com.gitlab.juancode.coiniapp.data.datasource.RemoteDataSource
import com.gitlab.juancode.coiniapp.entity.Balance
import com.gitlab.juancode.coiniapp.entity.Response
import com.gitlab.juancode.coiniapp.entity.User
import com.gitlab.juancode.coiniapp.entity.UserId
import com.google.gson.JsonObject

class CoiniRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun verificationPhone(code: String, phoneNumber: String): Response {
        val jsonObject = JsonObject()
        jsonObject.addProperty("code", code)
        jsonObject.addProperty("phoneNumber", phoneNumber)
        return remoteDataSource.verificationPhone(jsonObject)
    }

    suspend fun createUser(phoneNumber: String, password: String): User {
        val jsonObject = JsonObject()
        jsonObject.addProperty("phone", phoneNumber)
        jsonObject.addProperty("password", password)
        return remoteDataSource.createUser(jsonObject)
    }

    suspend fun login(phoneNumber: String, password: String): UserId {
        val jsonObject = JsonObject()
        jsonObject.addProperty("phone", phoneNumber)
        jsonObject.addProperty("password", password)
        return remoteDataSource.login(jsonObject)
    }

    suspend fun getBalance(userId: String): Balance {
        return remoteDataSource.getBalance(userId)
    }
}