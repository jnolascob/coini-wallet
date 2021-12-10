package com.gitlab.juancode.coiniapp.data.service

import com.gitlab.juancode.coiniapp.data.datasource.RemoteDataSource
import com.gitlab.juancode.coiniapp.entity.*
import com.google.gson.JsonObject

class ServiceDataSource: RemoteDataSource {
    override suspend fun getFlags(): List<Flag> {
        return CoiniRetrofit.service.getCountries()
    }

    override suspend fun verificationPhone(jsonObject: JsonObject): Response {

        return CoiniRetrofit.service.verificationPhone(jsonObject)
    }

    override suspend fun createUser(jsonObject: JsonObject): User {
        return CoiniRetrofit.service.createUser(jsonObject)
    }

    override suspend fun login(jsonObject: JsonObject): UserId {
        return CoiniRetrofit.service.login(jsonObject)
    }

    override suspend fun getBalance(userId: String): Balance {
        return CoiniRetrofit.service.getBalance(userId)
    }
}