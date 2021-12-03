package com.gitlab.juancode.coiniapp.data.repository

import com.gitlab.juancode.coiniapp.data.datasource.RemoteDataSource
import com.gitlab.juancode.coiniapp.entity.Flag

class FlagRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getFlags(): List<Flag> {
        return remoteDataSource.getFlags()
    }
}