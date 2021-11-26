package com.gitlab.juancode.coiniapp.ui.send

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.coiniapp.databinding.ItemContactBinding
import com.gitlab.juancode.coiniapp.databinding.ItemCountryBinding
import com.gitlab.juancode.coiniapp.databinding.ItemLettersBinding
import com.gitlab.juancode.coiniapp.entity.Country
import com.gitlab.juancode.coiniapp.ui.register.country.CountryAdapter
import kotlin.properties.Delegates

class LetterAdapter : RecyclerView.Adapter<LetterAdapter.ViewHolder>() {

    var letters: List<String > by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLettersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val letterValue = letters[position]
        holder.bind(letterValue)


    }

    override fun getItemCount(): Int {
        return letters.size
    }



    class ViewHolder(private val binding: ItemLettersBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(letterValue: String) {
            binding.textLetter.text = letterValue
            val list = (1..4).map { Pair("Alex Torres$it", "994 223 22$it") }.toList()
            val contactAdapter = ContactAdapter()
            contactAdapter.contacts = list
            binding.recyclerNamesAndNumbers.adapter = contactAdapter
        }
    }
}