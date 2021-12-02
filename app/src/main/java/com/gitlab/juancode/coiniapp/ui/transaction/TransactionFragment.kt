package com.gitlab.juancode.coiniapp.ui.transaction

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
import com.gitlab.juancode.coiniapp.databinding.FragmentTransactionBinding
import com.gitlab.juancode.coiniapp.ui.common.getTransactions

class TransactionFragment : Fragment() {
    private lateinit var binding: FragmentTransactionBinding
    private lateinit var viewModel: TransactionViewModel
    lateinit var navController: NavController
    lateinit var dateTransactionAdapter: DateTransactionAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@TransactionFragment

        dateTransactionAdapter = DateTransactionAdapter()
        binding.recyclerTransactions.adapter = dateTransactionAdapter

        dateTransactionAdapter.transactions = getTransactions()


    }
}