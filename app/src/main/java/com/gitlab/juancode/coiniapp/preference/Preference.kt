package com.gitlab.juancode.coiniapp.preference

import android.content.Context
import android.content.SharedPreferences

object Preference {
    private const val preferenceName = "coiniPreference"

    private const val PHONE_NUMBER = "phoneNumber"
    private const val USER_ID = "userId"

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    fun instance(context: Context) {
        sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    var savedPhoneNumber: String
        get() = sharedPreferences.getString(PHONE_NUMBER, "")!!
        set(value) {
            editor.putString(PHONE_NUMBER, value).commit()
        }

    var userId: String
        get() = sharedPreferences.getString(USER_ID, "")!!
        set(value) {
            editor.putString(USER_ID, value).commit()
        }
}