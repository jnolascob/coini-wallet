package com.gitlab.juancode.coiniapp.ui.contacts

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
import com.gitlab.juancode.coiniapp.databinding.FragmentContactsBinding
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class ContactsFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()
    private lateinit var binding: FragmentContactsBinding
    private val viewModel: ContactsViewModel by viewModel()
    lateinit var navController: NavController
    private lateinit var letterAdapter: LetterAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@ContactsFragment

        letterAdapter = LetterAdapter{
            val action = ContactsFragmentDirections.actionSendFragmentToSendFragment(it)
            navController.navigate(action)
        }
        letterAdapter.letters = listOf("A","B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

        binding.recyclerContacts.adapter = letterAdapter

        binding.imgBack.setOnClickListener {
            navController.popBackStack()
        }
        observers()
    }

    private fun observers() {
        viewModel.balanceLive.observe(viewLifecycleOwner, {
            binding.textBalance.text = "$ ${it.cusdBalance}"
        })
    }

}