package com.gitlab.juancode.coiniapp.ui.common

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.entity.Country
import com.gitlab.juancode.coiniapp.entity.From
import com.gitlab.juancode.coiniapp.entity.Transaction
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}
fun ImageView.loadUrl(url: String) {

    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .error(R.drawable.ic_pe)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
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
    val listDecember = (1..2).map {
        Transaction(
            amount = 200+it,
            type = if (it%2 == 0) "in" else "out",
            from = From(
                userId = "1",
                address = "address"
            ),
            to = From(
                userId = "${it + 1}",
                address = "address$it"
            ),
            date = "2021-12-${it+0}T22:07:38+00:00"
        )
    }.reversed()

    val listNovember = (1..20).map {
        Transaction(
            amount = 200+it,
            type = if (it%2 == 0) "in" else "out",
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
    }.reversed()
    return listDecember + listNovember
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

fun getDateNowToString(): String {
    val calendarNow = Calendar.getInstance()

    return calendarNow.time.toStringDate()
}

fun getDateYesterdayToString(): String {
    val calendarYesterday = Calendar.getInstance()
    calendarYesterday.add(Calendar.DATE, -1)

    return calendarYesterday.time.toStringDate()
}