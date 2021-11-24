package com.gitlab.juancode.coiniapp.ui.register.stepFour

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
import com.gitlab.juancode.coiniapp.databinding.FragmentRegisterStepFourBinding
import com.gitlab.juancode.coiniapp.entity.Country
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import com.gitlab.juancode.coiniapp.ui.register.stepOne.RegisterStepOneFragmentDirections

class RegisterStepFourFragment : Fragment() {

    private lateinit var binding: FragmentRegisterStepFourBinding
    private lateinit var viewModel: RegisterViewModel
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register_step_four,
            container,
            false
        )
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@RegisterStepFourFragment

        binding.firstPinView.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                binding.firstPinView.setLineColor(resources.getColor(R.color.black, null))
            } else {
                binding.firstPinView.setLineColor(resources.getColor(R.color.gray10, null))
            }

            if (it.toString().length == 6) {
                binding.buttonEnter.background =
                    resources.getDrawable(R.drawable.button_purple_enable_shape, null)

            } else {
                binding.buttonEnter.background = resources.getDrawable(R.drawable.button_purple_disable_shape, null)
            }
        }
    }
}