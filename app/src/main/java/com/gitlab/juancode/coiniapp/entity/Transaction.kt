package com.gitlab.juancode.coiniapp.entity

data class Transaction(
    val amount: Int,
    val date: String,
    val from: From,
    val to: From,
    val type: String
)

data class From (
    val userId: String,
    val address: String
)
