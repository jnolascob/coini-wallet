package com.gitlab.juancode.coiniapp.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val url: String = "",
    val name: String = "",
    val code: String = ""
): Parcelable