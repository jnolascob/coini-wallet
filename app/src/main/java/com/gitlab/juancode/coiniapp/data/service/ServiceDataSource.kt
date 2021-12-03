package com.gitlab.juancode.coiniapp.data.service

import com.gitlab.juancode.coiniapp.data.datasource.RemoteDataSource
import com.gitlab.juancode.coiniapp.entity.Flag

class ServiceDataSource: RemoteDataSource {
    override suspend fun getFlags(): List<Flag> {
        return CoiniRetrofit.service.getCountries()
    }
}