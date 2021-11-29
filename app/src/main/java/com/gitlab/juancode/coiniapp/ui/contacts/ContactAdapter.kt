package com.gitlab.juancode.coiniapp.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.coiniapp.databinding.ItemContactBinding
import com.gitlab.juancode.coiniapp.entity.Contact
import kotlin.properties.Delegates

class ContactAdapter(private val listener: (contact: Contact) -> Unit) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    var contacts: List<Contact> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactValue = contacts[position]
        holder.bind(contactValue)
        holder.itemView.setOnClickListener {
            listener(contactValue)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }



    class ViewHolder(private val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(value: Contact) {
            binding.textName.text = value.name
            binding.textNumber.text = value.number
        }
    }
}