package com.example.vinilos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.vinilos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private  lateinit var navController2:NavController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController2=navHostFragment.navController
        Log.d("Entro", "onCreate: "+navHostFragment.navController.graph)

        setupActionBarWithNavController(navController2, AppBarConfiguration(navController2.graph))
      supportActionBar!!.title="Vinilos"
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController2.navigateUp() || super.onSupportNavigateUp()
    }}