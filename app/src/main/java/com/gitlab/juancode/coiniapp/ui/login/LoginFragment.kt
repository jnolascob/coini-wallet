package com.gitlab.juancode.coiniapp.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.andrognito.pinlockview.PinLockListener
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentLoginBinding
import com.gitlab.juancode.coiniapp.preference.Preference
import com.gitlab.juancode.coiniapp.ui.common.Event
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope


class LoginFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    lateinit var navController: NavController
    val TAG = "LoginFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@LoginFragment

        Log.e(TAG, Preference.savedPhoneNumber)
        binding.pinLockView.setPinLockListener(mPinLockListener)
        binding.pinLockView.attachIndicatorDots(binding.indicatorDots)

        observer()
    }

    private val mPinLockListener: PinLockListener = object : PinLockListener {
        override fun onComplete(pin: String) {
            Log.d(TAG, "Pin complete: $pin")
            viewModel.login(pin)
        }

        override fun onEmpty() {
            Log.d(TAG, "Pin empty")
        }

        override fun onPinChange(pinLength: Int, intermediatePin: String) {
            Log.d(TAG, "Pin changed, new length $pinLength with intermediate pin $intermediatePin")
        }
    }

    private fun observer() {
        viewModel.loginLive.observe(viewLifecycleOwner, Event.EventObserver{
            Log.e("UserId", it.userId)
            val action = LoginFragmentDirections.actionLoginFragmentToBalanceFragment()
            navController.navigate(action, NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build())
        })
    }
}