package com.gitlab.juancode.coiniapp.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Operation(
    val name: String,
    val amount: String,
    val description : String,

): Parcelable
