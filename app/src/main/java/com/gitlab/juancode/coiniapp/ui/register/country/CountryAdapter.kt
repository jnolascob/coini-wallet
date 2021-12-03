package com.gitlab.juancode.coiniapp.ui.register.country

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gitlab.juancode.coiniapp.databinding.ItemCountryBinding
import com.gitlab.juancode.coiniapp.entity.Country
import com.gitlab.juancode.coiniapp.entity.Flag
import com.gitlab.juancode.coiniapp.ui.common.loadImage
import com.gitlab.juancode.coiniapp.ui.common.loadUrl
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class CountryAdapter(private val selectCountryListener: (Flag) -> Unit): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    var countries: List<Flag> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
        holder.itemView.setOnClickListener {
            selectCountryListener(country)
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class ViewHolder(private val binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Flag) {
            with(binding) {

                imageFlag.loadUrl(country.flag)
                textCountryName.text = country.name
                textCountryCode.text = "+${country.callingCodes}"
            }
        }
    }
}