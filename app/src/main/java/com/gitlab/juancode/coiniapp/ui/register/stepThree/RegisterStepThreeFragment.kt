package com.gitlab.juancode.coiniapp.ui.register.stepThree

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
import com.gitlab.juancode.coiniapp.databinding.FragmentRegisterStepThreeBinding
import com.gitlab.juancode.coiniapp.entity.Country
import com.gitlab.juancode.coiniapp.ui.common.loadImage
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import com.gitlab.juancode.coiniapp.ui.register.stepOne.RegisterStepOneFragmentDirections

class RegisterStepThreeFragment : Fragment() {

    private lateinit var binding: FragmentRegisterStepThreeBinding
    private lateinit var viewModel: RegisterViewModel
    lateinit var navController: NavController
    var country = Country()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_step_three, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RegisterStepThreeFragment


    }

}