package com.gitlab.juancode.coiniapp.ui.register.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentRegisterCountryBinding
import com.gitlab.juancode.coiniapp.ui.common.getCountries
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel

class RegisterCountryFragment : Fragment() {

    lateinit var binding: FragmentRegisterCountryBinding
    private lateinit var viewModel: RegisterViewModel
    lateinit var navController: NavController
    private val args: RegisterCountryFragmentArgs by navArgs()

    lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_country, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RegisterCountryFragment

        countryAdapter = CountryAdapter {
            navController.previousBackStackEntry?.savedStateHandle?.set("CountryReturn", it)
            navController.navigateUp()
        }

        binding.recyclerCountry.adapter = countryAdapter



        viewModel.flagsLive.observe(viewLifecycleOwner, {
            countryAdapter.countries = it
        })
    }
}