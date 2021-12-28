package com.gdsctsec.smartt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(){

        lateinit var viewPager:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView:BottomNavigationView=findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.background=null;
        bottomNavigationView.menu.get(1).isEnabled=false;

        //setting up the viewPager for bottom nav
        viewPager=findViewById(R.id.viewpager)






    bottomNavigationView.setOnItemSelectedListener {
        when(it.itemId){

        }
    }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuItem: Unit =menuInflater.inflate(R.menu.bottom_nav_menu,menu)
        return true;
    }




}