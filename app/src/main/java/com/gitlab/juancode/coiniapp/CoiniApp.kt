package com.gitlab.juancode.coiniapp

import android.app.Application

class CoiniApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}