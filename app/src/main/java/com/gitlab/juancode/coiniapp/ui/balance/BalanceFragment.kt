package com.gitlab.juancode.coiniapp.ui.balance

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
import com.gitlab.juancode.coiniapp.databinding.FragmentBalanceBinding
import com.gitlab.juancode.coiniapp.ui.login.LoginViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class BalanceFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()

    private lateinit var binding: FragmentBalanceBinding
    lateinit var navController: NavController
    private val viewModel by viewModel<BalanceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_balance, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@BalanceFragment

        binding.buttonSend.setOnClickListener {
            val action = BalanceFragmentDirections.actionBalanceFragmentToSendFragment()
            navController.navigate(action)
        }

        binding.buttonScan.setOnClickListener {
            val action = BalanceFragmentDirections.actionBalanceFragmentToReceiveFragment()
            navController.navigate(action)
        }

        binding.buttonLastTransaction.setOnClickListener {
            val action = BalanceFragmentDirections.actionBalanceFragmentToTransactionFragment()
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