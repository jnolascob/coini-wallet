package com.gitlab.juancode.coiniapp.data.datasource

import com.gitlab.juancode.coiniapp.entity.Flag

interface RemoteDataSource {
    suspend fun getFlags(): List<Flag>
}