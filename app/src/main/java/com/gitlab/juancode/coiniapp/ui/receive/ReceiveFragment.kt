package com.gitlab.juancode.coiniapp.ui.receive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gitlab.juancode.coiniapp.R
import com.gitlab.juancode.coiniapp.databinding.FragmentReceiveBinding

class ReceiveFragment : Fragment() {
    private lateinit var binding: FragmentReceiveBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receive, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        binding.lifecycleOwner = this@ReceiveFragment

        binding.imgBack.setOnClickListener {
            navController.popBackStack()
        }

        binding.imgCopy.setOnClickListener {
            Toast.makeText(context, "Enlace copiado", Toast.LENGTH_SHORT).show()
        }
    }
}