package com.gitlab.juancode.coiniapp.ui.transaction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.coiniapp.databinding.ItemTransactionBinding
import com.gitlab.juancode.coiniapp.entity.Transaction
import kotlin.properties.Delegates

class TransactionAdapter: RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    var transactions : List<Transaction> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    class ViewHolder(binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(transaction: Transaction) {

        }

    }
}