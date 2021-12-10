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
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class TransactionFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()

    private lateinit var binding: FragmentTransactionBinding
    private val viewModel: TransactionViewModel by viewModel()
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
        observers()
    }

    private fun observers() {
        viewModel.balanceLive.observe(viewLifecycleOwner, {
            binding.textBalance.text = "$ ${it.cusdBalance}"
        })
    }

}