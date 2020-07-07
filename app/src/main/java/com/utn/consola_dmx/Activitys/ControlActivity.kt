package com.utn.consola_dmx.Activitys

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import com.utn.consola_dmx.Fragments.ManualViewModel
import com.utn.consola_dmx.R
import app.akexorcist.bluetotohspp.library.BluetoothSPP.BluetoothConnectionListener
import com.google.android.material.bottomnavigation.BottomNavigationView


class ControlActivity : AppCompatActivity() {

    private lateinit var viewModel_manual: ManualViewModel
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        viewModel_manual = ViewModelProvider(this).get(ManualViewModel::class.java)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.control_navhost) as NavHostFragment

        bottomNavView = findViewById(R.id.bottom_bar)

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)



    }


}