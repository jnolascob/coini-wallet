package com.gitlab.juancode.coiniapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class HomeNavHostActivity : AppCompatActivity() {
    lateinit var imgNotify: ImageView
    lateinit var imgUser: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_nav_host)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        imgNotify = findViewById(R.id.imgNotify)
        imgUser = findViewById(R.id.imgUser)
        controller = navHostFragment.navController
    }

    private lateinit var controller: NavController

    private val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
        if (destination.label != "fragment_login") {
            imgUser.visibility = View.VISIBLE
            imgNotify.visibility = View.GONE
        } else {
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