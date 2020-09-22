package com.example.mychatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.mychatapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        // Inflate Main activity
        val binding = ActivityMainBinding.inflate(layoutInflater)

        //Set contentView to root
        setContentView(binding.root)

        //Get Nav Controller and ActionBar
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        //Setup Default ActionBar with Navigation UI
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)
    }

    //setting default behaviour on back arrow press
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
