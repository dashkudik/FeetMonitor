package com.example.feetmonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val navController
        by lazy { Navigation.findNavController(this, R.id.fragment_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(nav_view) {
            setupWithNavController(navController)
            setOnNavigationItemSelectedListener { item ->
                with(navController) {
                    when (item.itemId) {
                        R.id.item_home -> navigate(R.id.fragment_home)
                        R.id.item_bluetooth_connection -> navigate(R.id.fragment_connection)
                        R.id.item_settings -> navigate(R.id.fragment_settings)
                        R.id.item_stats -> navigate(R.id.fragment_stats)
                    }
                }
                true
            }
            selectedItemId = R.id.item_home
        }
    }
}