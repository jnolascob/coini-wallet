package com.gitlab.juancode.coiniapp.ui.register.stepTwo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentRegisterStepTwoBinding
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepTwoFragment : Fragment() {
    private lateinit var binding: FragmentRegisterStepTwoBinding
    private val viewModel by sharedViewModel<RegisterViewModel>()
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_step_two, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RegisterStepTwoFragment

        binding.firstPinView.addTextChangedListener {
            if (it.toString().length == 4) {
                binding.layoutNextButton.background = resources.getDrawable(R.drawable.button_purple_enable_shape, null)
            } else {
                binding.layoutNextButton.background = resources.getDrawable(R.drawable.button_purple_disable_shape, null)
            }
        }

        binding.layoutNextButton.setOnClickListener {
            val action = RegisterStepTwoFragmentDirections.actionRegisterStepTwoFragmentToRegisterStepThreeFragment()
            navController.navigate(action)
        }
    }
}