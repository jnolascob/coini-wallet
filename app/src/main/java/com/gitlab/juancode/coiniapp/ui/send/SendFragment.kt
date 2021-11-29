package com.gitlab.juancode.coiniapp.ui.send

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentSendBinding

class SendFragment : Fragment() {
    private lateinit var binding: FragmentSendBinding
    private lateinit var viewModel: SendViewModel
    lateinit var navController: NavController
    private lateinit var letterAdapter: LetterAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_send, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        viewModel = ViewModelProvider(this).get(SendViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SendFragment

        letterAdapter = LetterAdapter()
        letterAdapter.letters = listOf("A","B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

        binding.recyclerContacts.adapter = letterAdapter
    }

}