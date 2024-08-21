// RecipeActivity.kt
package com.iti_project.recipeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val toolbar: Toolbar = findViewById(R.id.appBar)
        setSupportActionBar(toolbar)

        // Find the NavHostFragment and NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.catogriesFragment2, R.id.favoriteFragment, R.id.searchFragment)
        )

        // Setup the Toolbar with NavController
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Setup the BottomNavigationView with NavController
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        // Add a destination change listener to hide/show the bottom navigation bar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.recipeDetailFragment || destination.id ==R.id.homeFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
            if (destination.id == R.id.favoriteFragment || destination.id == R.id.recipeDetailFragment) {
                {
                    userViewModel.getFavorites(
                        getSharedPreferences(
                            "UserPrefs",
                            Context.MODE_PRIVATE
                        ).getInt("userId", -1)
                    )
                }


            }
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}
