package com.gitlab.juancode.coiniapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeNavHostActivity : AppCompatActivity() {
    lateinit var imgNotify: ImageView
    lateinit var imgUser: ImageView
    lateinit var layoutHeader: LinearLayout
    private val registerViewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_nav_host)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        imgNotify = findViewById(R.id.imgNotify)
        imgUser = findViewById(R.id.imgUser)
        layoutHeader = findViewById(R.id.layoutHeader)
        controller = navHostFragment.navController
    }

    private lateinit var controller: NavController

    private val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
        if (destination.label == "fragment_camera") {
            layoutHeader.visibility = View.GONE
        } else if (destination.label != "fragment_login") {
            Log.e("TAG", "${destination.label}")
            layoutHeader.visibility = View.VISIBLE
            imgUser.visibility = View.VISIBLE
            imgNotify.visibility = View.GONE
        } else {
            layoutHeader.visibility = View.VISIBLE
            imgUser.visibility = View.GONE
            imgNotify.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        controller.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        controller.removeOnDestinationChangedListener(listener)
        super.onPause()
    }
}