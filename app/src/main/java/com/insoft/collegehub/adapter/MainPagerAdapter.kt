package com.insoft.collegehub.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.insoft.collegehub.Fragments.CollegeUpdatesFragment
import com.insoft.collegehub.Fragments.FeedFragment

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2  // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FeedFragment()   // First tab - Feed
            1 -> CollegeUpdatesFragment() // Second tab - Updates from college
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }
}