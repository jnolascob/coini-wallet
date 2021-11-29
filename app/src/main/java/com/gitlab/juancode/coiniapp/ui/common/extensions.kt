package com.gitlab.juancode.coiniapp.ui.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gitlab.juancode.coiniapp.entity.Country
import java.text.SimpleDateFormat
import java.util.*

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

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

fun getDateFormat(): String {
    val date = getCurrentDateTime()
    return date.toString("yyyy/MM/dd - HH:mm:ssa")
}