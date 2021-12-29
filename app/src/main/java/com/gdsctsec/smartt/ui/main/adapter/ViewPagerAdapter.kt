package com.gdsctsec.smartt.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gdsctsec.smartt.ui.main.HomeScreenFragment
import com.gdsctsec.smartt.ui.main.MainActivity
import com.gdsctsec.smartt.ui.main.TTSchedulingScreenFragment

class ViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeScreenFragment()
            1 -> TTSchedulingScreenFragment()
            else -> HomeScreenFragment()
        }
    }

}