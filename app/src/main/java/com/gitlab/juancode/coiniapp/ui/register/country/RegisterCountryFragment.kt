package com.gitlab.juancode.coiniapp.ui.register.country

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentRegisterCountryBinding
import com.gitlab.juancode.coiniapp.entity.Flag
import com.gitlab.juancode.coiniapp.ui.common.loadUrl
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterCountryFragment : Fragment() {

    lateinit var binding: FragmentRegisterCountryBinding
    private val viewModel by sharedViewModel<RegisterViewModel>()
    lateinit var navController: NavController
    private val args: RegisterCountryFragmentArgs by navArgs()

    private lateinit var countryAdapter: CountryAdapter
    private var countries = mutableListOf<Flag>()
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RegisterCountryFragment

        countryAdapter = CountryAdapter {
            navController.previousBackStackEntry?.savedStateHandle?.set("CountryReturn", it)
            navController.navigateUp()
        }

        binding.recyclerCountry.adapter = countryAdapter

        binding.ivCurrentFlag.loadUrl(args.flag.flag)
        binding.tvNameFlag.text = args.flag.name
        binding.tvCountryCode.text = "+${args.flag.callingCodes}"

        if (args.flag.name.isEmpty()) {
            binding.layoutCurrentLocation.visibility = View.GONE
        } else {
            binding.layoutCurrentLocation.visibility = View.VISIBLE
        }

        binding.etSearchCountry.addTextChangedListener { edit->
            val filter = countries.filter { it.name.lowercase().contains(edit.toString().lowercase()) }
            countryAdapter.countries = filter
        }

        viewModel.flagsLive.observe(viewLifecycleOwner, {
            countries.clear()
            countries.addAll(it)
            countryAdapter.countries = countries
        })
    }
}