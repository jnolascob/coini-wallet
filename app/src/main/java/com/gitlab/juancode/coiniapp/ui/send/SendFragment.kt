package com.gitlab.juancode.coiniapp.ui.send

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
import androidx.navigation.fragment.navArgs
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentSendBinding

class SendFragment : Fragment() {
    private lateinit var binding: FragmentSendBinding
    private lateinit var viewModel: SendViewModel
    lateinit var navController: NavController
    val args: SendFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_send, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        viewModel = ViewModelProvider(this).get(SendViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SendFragment

        binding.textNameContact.text = args.contact.name

        binding.imgBack.setOnClickListener {
            navController.popBackStack()
        }

        binding.etAmount.addTextChangedListener {
            if (it.toString().isEmpty()) {
                binding.txtSoles.setTextColor(resources.getColor(R.color.purple10, null))
                binding.buttonSend.background = resources.getDrawable(R.drawable.button_purple_disable_shape, null)
            } else {
                binding.txtSoles.setTextColor(resources.getColor(R.color.purple, null))
                binding.buttonSend.background = resources.getDrawable(R.drawable.button_purple_enable_shape, null)

            }
        }
    }
}