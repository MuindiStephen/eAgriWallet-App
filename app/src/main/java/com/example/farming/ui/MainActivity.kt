package com.example.farming.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.farming.R
import com.example.farming.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()


        binding.bottomNavigation.apply {
            setupWithNavController(navController)
        }


//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
//        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when(destination.id) {
                R.id.dashboardFragment2 -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                R.id.mpesaPaymentFragment -> {
                     binding.bottomNavigation.visibility = View.VISIBLE
                }
                R.id.locateAgrovetsFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                R.id.profileFragment2 -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavigation.visibility = View.INVISIBLE
                }
            }

        }

    }
}
