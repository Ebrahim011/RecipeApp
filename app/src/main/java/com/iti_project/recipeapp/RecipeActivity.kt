package com.iti_project.recipeapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iti_project.recipeapp.RoomFolder.UserViewModel

class RecipeActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toggleDarkMode: ImageButton
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check theme state from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserTheme", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_recipe)

        val toolbar: Toolbar = findViewById(R.id.appBar)
        setSupportActionBar(toolbar)

        toggleDarkMode = findViewById(R.id.toggleDarkMode)
        updateToggleIcon()

        toggleDarkMode.setOnClickListener {
            val editor = sharedPreferences.edit()
            if (isDarkModeOn()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("isDarkMode", false)
                Log.i("Recipe", "Dark mode is off")
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("isDarkMode", true)
                Log.i("Recipe", "Dark mode is on")
            }
            editor.apply()
            updateToggleIcon()
        }

        // Find the NavHostFragment and NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.catogriesFragment2, R.id.favoriteFragment, R.id.searchFragment, R.id.profileFragment)
        )

        // Setup the Toolbar with NavController
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Setup the BottomNavigationView with NavController
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        // Add a destination change listener to hide/show the bottom navigation bar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.catogriesFragment2) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
            userViewModel.getFavorites(getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).getInt("userId", -1))
            if (destination.id == R.id.recipeDetailFragment || destination.id == R.id.homeFragment || destination.id == R.id.aboutUsFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    private fun isDarkModeOn(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    private fun updateToggleIcon() {
        if (isDarkModeOn()) {
            toggleDarkMode.setImageResource(R.drawable.ic_moon)
        } else {
            toggleDarkMode.setImageResource(R.drawable.ic_sun)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sign_out -> {
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear().apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.action_about_the_creator -> {
                navController.navigate(R.id.aboutUsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}