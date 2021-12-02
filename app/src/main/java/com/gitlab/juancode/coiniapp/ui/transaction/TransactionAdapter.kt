package com.gitlab.juancode.coiniapp.ui.transaction

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.ItemTransactionBinding
import com.gitlab.juancode.coiniapp.entity.Transaction
import com.gitlab.juancode.coiniapp.ui.common.convertDateToSimpleDate
import com.gitlab.juancode.coiniapp.ui.common.toStringDate
import kotlin.properties.Delegates

class TransactionAdapter(): RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    companion object {
        const val IN = "in"
        const val OUT = "out"
    }
    var transactions : List<Transaction> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    class ViewHolder(val binding: ItemTransactionBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root){

        fun bind(transaction: Transaction) {
            Log.e("hola", "ads")
            binding.textNameTransaction.text = transaction.to.userId
            binding.textDateTransaction.text = convertDateToSimpleDate(transaction.date).time.toStringDate()
            if (transaction.type == IN) {
                binding.textAmountTransaction.text = "+ ${transaction.amount}"
                binding.textAmountTransaction.setTextColor(context.resources.getColor(R.color.green, null))
            } else {
                binding.textAmountTransaction.text =  "- ${transaction.amount}"
                binding.textAmountTransaction.setTextColor(context.resources.getColor(R.color.red, null))

            }
        }

    }
}
