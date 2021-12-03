package com.gitlab.juancode.coiniapp.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flag(
    val alpha2Code: String= "",
    val alpha3Code: String= "",
    val callingCodes: String= "",
    val flag: String= "",
    val name: String= ""
): Parcelable