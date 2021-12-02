package com.gitlab.juancode.coiniapp.ui.common

import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.gitlab.juancode.coiniapp.entity.Country
import com.gitlab.juancode.coiniapp.entity.From
import com.gitlab.juancode.coiniapp.entity.Transaction
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
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


fun getCountries(): List<Country> {
    return (1..10).map {
        Country(url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Flag_of_Antigua_and_Barbuda.svg/23px-Flag_of_Antigua_and_Barbuda.svg.png",
            name = "Arabia $it",
            code = "+$it")
    }.toList()

}

fun getTransactions(): List<Transaction> {
    return (1..20).map {
        Transaction(
            amount = 200+it,
            type = "in",
            from = From(
                userId = "1",
                address = "address"
            ),
            to = From(
                userId = "${it + 1}",
                address = "address$it"
            ),
            date = "2021-11-${it+10}T22:07:38+00:00"
        )
    }
}

fun convertDateToSimpleDate(date: String): Calendar {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    cal.time = sdf.parse(date)
    return cal
}

fun getDayName(day: Int): String {
    return when(day) {
        1 -> "Domingo"
        2 -> "Lunes"
        3 -> "Martes"
        4 -> "Miercoles"
        5 -> "Jueves"
        6 -> "Viernes"
        7 -> "Sábado"
        else -> ""
    }
}

fun getMonthName(month: Int): String {
    val monthName = arrayOf(
        "Ene.", "Feb.","Mar.", "Abr.", "May.", "Jun.", "Jul.",
        "Ago.", "Set.", "Oct.", "Nov.", "Dic."
    )
    return monthName[month]
}

fun Date.toStringDate(format: String = "yyyy/MM/dd", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

val FragmentManager.currentNavigationFragment: Fragment?
    get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()