package com.ms.app.ui.activity.main

import android.graphics.Color
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ms.app.R
import com.ms.app.ui.base.BaseAppCompatActivity
import com.ms.app.ui.base.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseAppCompatActivity() {

    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _BottomNavigationView.itemIconTintList = null

        navController = Navigation.findNavController(this, R.id.fragment)
        navController!!.setGraph(R.navigation.nav_main)
        navController!!.navigate(R.id.homeFragment)

        _BottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController!!.navigate(R.id.homeFragment)
                }

                R.id.navigation_my -> {
                    navController!!.navigate(R.id.myFragment)
                }
            }
            true
        })
    }

    override fun layout(): Int {
        return R.layout.activity_main
    }

    override fun setStatusBar() {
        StatusBarUtil.setTransparent(this)
        StatusBarUtil.setLightMode(this)
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"),0)
    }
}