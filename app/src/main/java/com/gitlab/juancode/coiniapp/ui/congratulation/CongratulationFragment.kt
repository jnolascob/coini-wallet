package com.gitlab.juancode.coiniapp.ui.congratulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentCongratulationBinding
import com.gitlab.juancode.coiniapp.ui.common.getDateFormat

class CongratulationFragment : Fragment() {
    private lateinit var binding: FragmentCongratulationBinding
    lateinit var navController: NavController
    private val args: CongratulationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_congratulation, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding.lifecycleOwner = this@CongratulationFragment

        binding.textAmount.text = args.operation?.amount
        binding.textName.text = args.operation?.name
        binding.textDescription.text = args.operation?.description
        binding.textDate.text = getDateFormat()

        binding.btnHome.setOnClickListener {
            navController.popBackStack()
        }
    }
}