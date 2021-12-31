package com.gdsctsec.smartt.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.gdsctsec.smartt.ui.edit.EditScreenActivity
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.adapter.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val fab_gotoeditscreen : FloatingActionButton =  findViewById(R.id.fab_editTTscreen)
        bottomNavigationView.menu.get(1).isEnabled = false;
        //setting up the viewPager for bottom nav
        viewPager = findViewById(R.id.view_pager)
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_button -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.calendar_button -> {
                    viewPager.currentItem = 1
                    true
                }

                else -> false

            }
        }
        fab_gotoeditscreen.setOnClickListener{
            startActivity(Intent(applicationContext, EditScreenActivity::class.java))
        }

        supportActionBar?.hide()

    }


}