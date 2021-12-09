package com.gitlab.juancode.coiniapp.data.service

import com.gitlab.juancode.coiniapp.data.datasource.RemoteDataSource
import com.gitlab.juancode.coiniapp.entity.Flag
import com.gitlab.juancode.coiniapp.entity.Response
import com.gitlab.juancode.coiniapp.entity.User
import com.gitlab.juancode.coiniapp.entity.UserId
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
}