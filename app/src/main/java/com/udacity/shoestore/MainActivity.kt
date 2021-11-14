package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.core.utils.SHARED_PREFERENCE_KEEP_LOGGED
import com.udacity.shoestore.core.utils.saveSharedPreferenceBoolean
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
        setSupportActionBar(binding.toolbar)
    }

    private fun setupListeners() {
        binding.toolbar.setOnMenuItemClickListener { itemClicked ->
            when (itemClicked.itemId) {
                R.id.logoutMenu -> {
                    navController.navigate(R.id.actionGlobalLoginFragment)
                    saveSharedPreferenceBoolean(
                        SHARED_PREFERENCE_KEEP_LOGGED,
                        false
                    )
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
                    setBackHomeButton(false)
                }
                R.id.shoeDetailFragment -> {
                    setBackHomeButton(true)
                }
                else -> {
                    binding.toolbar.isVisible = false
                }
            }
        }
    }

    private fun setBackHomeButton(isEnabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(isEnabled)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)
        menu?.findItem(R.id.logoutMenu)?.isVisible =
            navController.currentDestination?.id != R.id.shoeDetailFragment
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return true
    }
}
