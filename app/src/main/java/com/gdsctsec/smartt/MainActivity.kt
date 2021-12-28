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
import com.gdsctsec.smartt.Adapters.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(){

        lateinit var viewPager:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavigationView:BottomNavigationView=findViewById(R.id.bottom_navigation_view);


        bottomNavigationView.menu.get(1).isEnabled=false;

        //setting up the viewPager for bottom nav
        viewPager=findViewById(R.id.viewpager)
        val adapter=ViewPagerAdapter(this)
        viewPager.adapter=adapter






    bottomNavigationView.setOnItemSelectedListener {
        when(it.itemId){
                R.id.home_button-> {
                    viewPager.currentItem=0
                    true
                }
            R.id.calender_button->{
                viewPager.currentItem=1
                true
            }

            else->false

        }
    }

        supportActionBar?.hide()

    }






}