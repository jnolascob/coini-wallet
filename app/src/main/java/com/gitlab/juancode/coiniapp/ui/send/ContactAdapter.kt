package com.gitlab.juancode.coiniapp.ui.send

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.coiniapp.databinding.ItemContactBinding
import com.gitlab.juancode.coiniapp.databinding.ItemCountryBinding
import com.gitlab.juancode.coiniapp.entity.Country
import com.gitlab.juancode.coiniapp.ui.register.country.CountryAdapter
import kotlin.properties.Delegates

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    var contacts: List<Pair<String, String>> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactValue = contacts[position]
        holder.bind(contactValue)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }



    class ViewHolder(private val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(value: Pair<String, String>) {
            binding.textName.text = value.first
            binding.textNumber.text = value.second
        }
    }
}