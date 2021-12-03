package com.gitlab.juancode.coiniapp.ui.transaction

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentTransactionBinding
import com.gitlab.juancode.coiniapp.ui.common.*

class TransactionFragment : Fragment() {
    private lateinit var binding: FragmentTransactionBinding
    private lateinit var viewModel: TransactionViewModel
    lateinit var navController: NavController
    lateinit var dateTransactionAdapter: DateTransactionAdapter

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            val action = TransactionFragmentDirections.actionTransactionFragmentToCameraFragment()
            navController.navigate(action)
        }
    }
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

        dateTransactionAdapter.transactions = getTransactions().filter {
            convertDateToSimpleDate(it.date).time.toStringDate() == getDateNowToString() ||
            convertDateToSimpleDate(it.date).time.toStringDate() == getDateYesterdayToString()
        }

        binding.textViewAll.setOnClickListener {
            binding.textViewAll.visibility = View.GONE
            dateTransactionAdapter.transactions = getTransactions()
        }

        binding.buttonCameraQR.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        binding.buttonSend.setOnClickListener {
            val action = TransactionFragmentDirections.actionTransactionFragmentToContactFragment()
            navController.navigate(action)
        }
    }
}