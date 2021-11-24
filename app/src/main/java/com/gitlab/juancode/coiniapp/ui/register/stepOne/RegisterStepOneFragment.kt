package com.gitlab.juancode.coiniapp.ui.register.stepOne

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
import com.gitlab.juancode.coiniapp.databinding.FragmentRegisterStepOneBinding
import com.gitlab.juancode.coiniapp.entity.Country
import com.gitlab.juancode.coiniapp.ui.common.loadImage
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel

class RegisterStepOneFragment : Fragment() {
    private lateinit var binding: FragmentRegisterStepOneBinding
    private lateinit var viewModel: RegisterViewModel
    lateinit var navController: NavController
    var country = Country()
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
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RegisterStepOneFragment

        binding.countryLayout.setOnClickListener {
            val action = RegisterStepOneFragmentDirections.actionStepOneFragmentToRegisterCountryFragment(country)
            navController.navigate(action)
        }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Country>("CountryReturn")?.observe(viewLifecycleOwner) {result ->
            country = result
            binding.textCountryCode.text = country.code
            binding.imageCountry.loadImage(country.url)

            if (country.name.isNotEmpty()) {
                binding.layoutNextButton.isEnabled = true
                binding.layoutNextButton.background = resources.getDrawable(R.drawable.button_purple_enable_shape, null)
            }
        }

        binding.layoutNextButton.setOnClickListener {
            val action = RegisterStepOneFragmentDirections.actionStepOneFragmentToRegisterStepTwoFragment()
            navController.navigate(action)
        }


    }


}