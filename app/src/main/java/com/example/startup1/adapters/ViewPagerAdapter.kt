package com.example.startup1.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.startup1.fragments.RegularUsersFragment
import com.example.startup1.fragments.OfficesFragment
import com.example.startup1.fragments.ReportsFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RegularUsersFragment.newInstance()
            1 -> OfficesFragment.newInstance()
            2 -> ReportsFragment.newInstance()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
} 