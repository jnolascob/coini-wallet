package com.gitlab.juancode.coiniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.gitlab.juancode.coiniapp.ui.common.currentNavigationFragment
import com.gitlab.juancode.coiniapp.ui.login.LoginFragment

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

    private lateinit var controller: NavController // don't forget to initialize

    private val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
        Log.e("current2", "onDestinationChanged: "+destination.label);
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