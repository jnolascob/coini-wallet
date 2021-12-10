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
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentSendBinding
import com.gitlab.juancode.coiniapp.entity.Operation
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SendFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()

    private lateinit var binding: FragmentSendBinding
    private val viewModel by viewModel<SendViewModel>()
    lateinit var navController: NavController
    private val args: SendFragmentArgs by navArgs()
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SendFragment

        binding.textNameContact.text = args.contact.name

        binding.imgBack.setOnClickListener {
            navController.popBackStack()
        }

        binding.etAmount.addTextChangedListener {
            if (it.toString().isEmpty()) {
                binding.txtSoles.setTextColor(resources.getColor(R.color.purple10, null))
                binding.buttonSend.background =
                    resources.getDrawable(R.drawable.button_purple_disable_shape, null)
            } else {
                binding.txtSoles.setTextColor(resources.getColor(R.color.purple, null))
                binding.buttonSend.background =
                    resources.getDrawable(R.drawable.button_purple_enable_shape, null)

            }
        }

        binding.buttonSend.setOnClickListener {
            val description = binding.etDescription.text.toString()
            val operation = Operation(
                amount = binding.etAmount.text.toString(),
                name = args.contact.name,
                description = description
            )
            val action =
                SendFragmentDirections.actionSendFragmentToCongratulationFragment(operation)
            navController.navigate(
                action,
                NavOptions.Builder().setEnterAnim(R.anim.nav_default_enter_anim).setExitAnim(R.anim.nav_default_exit_anim)
                    .setPopExitAnim(R.anim.nav_default_pop_exit_anim).setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                    .setPopUpTo(R.id.balanceFragment, false).build()
            )
        }

        observers()
    }

    private fun observers() {
        viewModel.balanceLive.observe(viewLifecycleOwner, {
            binding.textBalance.text = "$ ${it.cusdBalance}"
        })
    }
}