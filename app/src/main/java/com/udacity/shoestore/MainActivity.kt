package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        supportFragmentManager.findFragmentById(R.id.navHostFragment)?.findNavController()
            ?.let {
                navController = it
            }

        setContentView(binding.root)
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        binding.toolbar.inflateMenu(R.menu.main_toolbar)
    }

    private fun setupListeners() {
        binding.toolbar.setOnMenuItemClickListener { itemClicked ->
            when (itemClicked.itemId) {
                R.id.logoutMenu -> {
                    navController.navigate(R.id.actionGlobalLoginFragment)
                    true
                }
                R.id.loginFragment -> {
                    navController.navigate(R.id.actionGlobalLoginFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.shoeListFragment -> {
                    binding.toolbar.isVisible = true
                }
                else -> {
                    binding.toolbar.isVisible = false
                }
            }
        }
    }
}
