package com.gitlab.juancode.coiniapp.ui.register.stepOne

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentRegisterStepOneBinding
import com.gitlab.juancode.coiniapp.entity.Flag
import com.gitlab.juancode.coiniapp.ui.common.loadUrl
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterStepOneFragment : Fragment() {

    private lateinit var binding: FragmentRegisterStepOneBinding
    private val viewModel by sharedViewModel<RegisterViewModel>()

    lateinit var navController: NavController
    var flag = Flag()
    var phoneNumber = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_step_one, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RegisterStepOneFragment

        binding.countryLayout.setOnClickListener {
            val action = RegisterStepOneFragmentDirections.actionStepOneFragmentToRegisterCountryFragment(flag)
            navController.navigate(action)
        }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Flag>("CountryReturn")?.observe(viewLifecycleOwner) {result ->
            flag = result
            binding.textCountryCode.text = "+${flag.callingCodes}"
            binding.imageCountry.loadUrl(flag.flag)
            binding.textCountryCode.setTextColor(resources.getColor(R.color.black, null))

            validateNextButton(phoneNumber, flag.name)

        }

        binding.layoutNextButton.setOnClickListener {
            val action = RegisterStepOneFragmentDirections.actionStepOneFragmentToRegisterStepTwoFragment()
            navController.navigate(action)
        }

        binding.etNumber.addTextChangedListener {
            phoneNumber = it.toString()
            validateNextButton(phoneNumber, flag.name)
        }
    }

    private fun validateNextButton(value: String, flagName: String) {
        if (value.isNotEmpty() && flagName.isNotEmpty()) {
            binding.layoutNextButton.isClickable = true
            binding.layoutNextButton.background = resources.getDrawable(R.drawable.button_purple_enable_shape, null)
        } else {
            binding.layoutNextButton.isClickable = false
            binding.layoutNextButton.background = resources.getDrawable(R.drawable.button_purple_disable_shape, null)
        }
    }

}