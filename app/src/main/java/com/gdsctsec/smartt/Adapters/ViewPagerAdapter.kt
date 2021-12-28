package com.gdsctsec.smartt.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gdsctsec.smartt.HomeScreen
import com.gdsctsec.smartt.MainActivity
import com.gdsctsec.smartt.TTSchedulingScreen

class ViewPagerAdapter(activity:MainActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->HomeScreen()
            else->TTSchedulingScreen()
        }
    }

}