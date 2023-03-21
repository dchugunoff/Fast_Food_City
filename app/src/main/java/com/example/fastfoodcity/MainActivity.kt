package com.example.fastfoodcity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.fastfoodcity.utilites.AUTH
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        // Set up the action bar for use with the NavController
        setupActionBarWithNavController(this, navController)
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
        AUTH = FirebaseAuth.getInstance()
    }

}