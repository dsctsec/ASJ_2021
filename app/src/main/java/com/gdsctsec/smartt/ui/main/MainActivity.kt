package com.gdsctsec.smartt.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.edit.EditScreenFragment
import com.gdsctsec.smartt.ui.main.adapter.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager2
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var fabgotoEditScreen:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)

        fabgotoEditScreen = findViewById(R.id.fab_editTTscreen)
        bottomNavigationView.menu.get(1).isEnabled = false;

//        val homeBackStackEntry=findNavController(R.id.nav_host_fragment).getBackStackEntry(R.id.homeScreenFragment).destination.id
//        val ttBackStackEntry=findNavController(R.id.nav_host_fragment).getBackStackEntry(R.id.homeScreenFragment).destination.id
//
//
//        if (!(ttBackStackEntry==R.id.TTSchedulingScreenFragment || homeBackStackEntry==R.id.homeScreenFragment)){
//            bottomNavigationView.visibility=View.GONE
//        }
//        else{
//            bottomNavigationView.visibility=View.VISIBLE
//        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

//        val navController=findNavController(R.id.nav_host_fragment)

        bottomNavigationView.setupWithNavController(navController)
        fabgotoEditScreen.setOnClickListener {


            if (bottomNavigationView.selectedItemId == R.id.homeScreenFragment) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeScreenFragment_to_editScreenFragment)
            } else {
                val bundle= bundleOf("Source" to R.id.TTSchedulingScreenFragment)
               findNavController(R.id.nav_host_fragment).navigate(R.id.action_TTSchedulingScreenFragment_to_editScreenFragment,bundle)
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showBottomNavigation()


    }

    fun hideBottomNavigation(){
        bottomNavigationView.visibility=View.GONE
        fabgotoEditScreen.visibility=View.GONE
    }



    fun showBottomNavigation(){
        bottomNavigationView.visibility=View.VISIBLE
        fabgotoEditScreen.visibility=View.VISIBLE
    }




}