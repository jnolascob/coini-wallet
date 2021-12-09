package com.gitlab.juancode.coiniapp

import android.app.Application
import com.gitlab.juancode.coiniapp.preference.Preference

class CoiniApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
        Preference.instance(context = applicationContext)

    }
}