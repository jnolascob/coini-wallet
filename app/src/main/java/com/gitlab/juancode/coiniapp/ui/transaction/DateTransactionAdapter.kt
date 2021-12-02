package com.gitlab.juancode.coiniapp.ui.transaction

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.coiniapp.databinding.ItemDateTransactionBinding
import com.gitlab.juancode.coiniapp.entity.Transaction
import com.gitlab.juancode.coiniapp.ui.common.convertDateToSimpleDate
import com.gitlab.juancode.coiniapp.ui.common.getDayName
import com.gitlab.juancode.coiniapp.ui.common.getMonthName
import com.gitlab.juancode.coiniapp.ui.common.toStringDate
import java.util.*
import kotlin.properties.Delegates

class DateTransactionAdapter: RecyclerView.Adapter<DateTransactionAdapter.ViewHolder>() {
    var transactions : List<Transaction> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDateTransactionBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    class ViewHolder(private val binding: ItemDateTransactionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            val dateCalendar = convertDateToSimpleDate(transaction.date)
            val calendarTmp = Calendar.getInstance()

            val dateNow = calendarTmp.time.toStringDate()
            Log.e("------>1 ","$dateNow")
            calendarTmp.set(Calendar.DATE, 0)
            val dateYesterday = calendarTmp.time.toStringDate()
            Log.e("------>2 ","$dateYesterday")

            if (dateCalendar.get(Calendar.YEAR) == calendarTmp.get(Calendar.YEAR) &&
                dateCalendar.get(Calendar.MONTH) == calendarTmp.get(Calendar.MONTH) &&
                dateCalendar.get(Calendar.DAY_OF_MONTH) == calendarTmp.get(Calendar.DAY_OF_MONTH-1) ) {
                Log.e("------>"," ${dateCalendar.get(Calendar.DAY_OF_MONTH)} ${dateCalendar.get(Calendar.MONTH)} ${dateCalendar.get(Calendar.YEAR)}")
            }
            binding.textNameTransaction.text = getDayName(dateCalendar.get(Calendar.DAY_OF_WEEK))
            binding.textDateTransaction.text = "${dateCalendar.get(Calendar.DAY_OF_MONTH)} ${getMonthName(dateCalendar.get(Calendar.MONTH))} ${dateCalendar.get(Calendar.YEAR)}"
        }
    }
}