package com.gitlab.juancode.coiniapp.ui.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gitlab.juancode.coiniapp.entity.Country

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}

fun getCountries(): List<Country> {
    return (1..10).map {
        Country(url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Flag_of_Antigua_and_Barbuda.svg/23px-Flag_of_Antigua_and_Barbuda.svg.png",
            name = "Arabia $it",
            code = "+$it")
    }.toList()

}