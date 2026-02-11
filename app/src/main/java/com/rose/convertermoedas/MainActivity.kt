package com.rose.convertermoedas

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rose.convertermoedas.view.ConverterFragment
import com.rose.convertermoedas.view.HistoryFragment
import com.rose.convertermoedas.view.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var homeFragment: HomeFragment
    private lateinit var historyFragment: HistoryFragment
    private lateinit var converterFragment: ConverterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, -200)
            insets
        }

        homeFragment = HomeFragment()
        converterFragment = ConverterFragment()
        historyFragment = HistoryFragment()

        bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigate)
        loadFragment(HomeFragment())

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> loadFragment(homeFragment)
                R.id.menu_converter -> loadFragment(converterFragment)
                R.id.menu_history -> loadFragment(historyFragment)
            }
            return@setOnItemSelectedListener true
        }


    }

    private fun loadFragment(fragment: Fragment) {
        if (fragment != null) {
            val transition = supportFragmentManager.beginTransaction()
            transition.replace(R.id.container_main, fragment)
            transition.commit()
        }
    }
}