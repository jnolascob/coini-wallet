package com.gitlab.juancode.coiniapp.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val number: String,
    val name: String
): Parcelable
