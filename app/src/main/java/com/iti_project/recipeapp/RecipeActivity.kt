package com.iti_project.recipeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeActivity : AppCompatActivity() {
    private lateinit var navController: NavController

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
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)
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

                val fragmentManger = supportFragmentManager
                val transaction = fragmentManger.beginTransaction()
                transaction.add(R.id.nav_host_fragment, AboutUsFragment(), "tag")
                transaction.addToBackStack("backstack")
                transaction.commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}