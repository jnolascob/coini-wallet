package com.gitlab.juancode.coiniapp.ui.transaction

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.coiniapp.databinding.ItemDateTransactionBinding
import com.gitlab.juancode.coiniapp.entity.Transaction
import com.gitlab.juancode.coiniapp.ui.common.*
import com.gitlab.juancode.coiniapp.ui.contacts.ContactAdapter
import java.util.*
import kotlin.properties.Delegates

class DateTransactionAdapter() : RecyclerView.Adapter<DateTransactionAdapter.ViewHolder>() {
    var transactions: List<Transaction> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDateTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        val transactionsByDay = transactions.filter { convertDateToSimpleDate(it.date).time.toStringDate() ==  convertDateToSimpleDate(transaction.date).time.toStringDate() }
        holder.bind(transaction, transactionsByDay)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    class ViewHolder(private val binding: ItemDateTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(transaction: Transaction, listTransactionByDay: List<Transaction>) {

            val transactionAdapter = TransactionAdapter()
            transactionAdapter.transactions = listTransactionByDay

            binding.recyclerTransactions.adapter = transactionAdapter

            val dateCalendar = convertDateToSimpleDate(transaction.date)

            when {
                (dateCalendar.time.toStringDate() == getDateNowToString()) -> {
                    binding.textNameTransaction.text = "Hoy"
                    binding.textDateTransaction.text =
                        "${dateCalendar.get(Calendar.DAY_OF_MONTH)} ${
                            getMonthName(
                                dateCalendar.get(Calendar.MONTH)
                            )
                        } ${dateCalendar.get(Calendar.YEAR)}"
                }

                (dateCalendar.time.toStringDate() == getDateYesterdayToString()) -> {
                    binding.textNameTransaction.text = "Ayer"
                    binding.textDateTransaction.text =
                        "${dateCalendar.get(Calendar.DAY_OF_MONTH)} ${
                            getMonthName(
                                dateCalendar.get(Calendar.MONTH)
                            )
                        } ${dateCalendar.get(Calendar.YEAR)}"
                }
                else -> {
                    binding.textNameTransaction.text =
                        getDayName(dateCalendar.get(Calendar.DAY_OF_WEEK))
                    binding.textDateTransaction.text =
                        "${dateCalendar.get(Calendar.DAY_OF_MONTH)} ${
                            getMonthName(
                                dateCalendar.get(Calendar.MONTH)
                            )
                        } ${dateCalendar.get(Calendar.YEAR)}"

                }

            }



        }
    }
}