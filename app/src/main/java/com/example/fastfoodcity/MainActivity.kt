package com.example.fastfoodcity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fastfoodcity.utilites.AUTH
import com.example.fastfoodcity.utilites.initFirebase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(/*context=*/this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
//
//        setupActionBarWithNavController(this, navController)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener {_, destination, _ ->
            when (destination.id) {
                R.id.enterPhoneNumber -> navView.visibility = View.GONE
                R.id.enterCode -> navView.visibility = View.GONE
                else -> navView.visibility = View.VISIBLE
            }
        }
        initFunc()
        initAutorisation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun initAutorisation() {
        if (AUTH.currentUser != null) {
            navController.navigate(R.id.menuFragment)
        } else {
            navController.navigate(R.id.enterPhoneNumber)
        }
    }
    private fun initFunc() {
        initFirebase()
    }

}