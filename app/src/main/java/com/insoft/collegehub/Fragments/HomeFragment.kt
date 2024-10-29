package com.insoft.collegehub.Fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.insoft.collegehub.R
import com.insoft.collegehub.adapter.MainPagerAdapter


class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize ViewPager and TabLayout
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)

        // Setup the ViewPager with adapter
        val adapter = MainPagerAdapter(this)
        viewPager.adapter = adapter
        tabLayout.tabRippleColor = ColorStateList.valueOf(Color.TRANSPARENT)
        // Bind ViewPager with TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "FEED"
                1 -> "UPDATES"
                else -> null
            }
        }.attach()

        val bottomIcon1: LinearLayout = view.findViewById(R.id.icon1)
        val bottomIcon1Img: ImageView = view.findViewById(R.id.icon1Img)
        val bottomIcon1Text: TextView = view.findViewById(R.id.icon1Text)

        val bottomIcon2: LinearLayout = view.findViewById(R.id.icon2)
        val bottomIcon2Img: ImageView = view.findViewById(R.id.icon2Img)
        val bottomIcon2Text: TextView = view.findViewById(R.id.icon2Text)

        val bottomIcon3: LinearLayout = view.findViewById(R.id.icon3)
        val bottomIcon3Img: ImageView = view.findViewById(R.id.icon3Img)
        val bottomIcon3Text: TextView = view.findViewById(R.id.icon3Text)

        val bottomIcon4: LinearLayout = view.findViewById(R.id.icon4)
        val bottomIcon4Img: ImageView = view.findViewById(R.id.icon4Img)
        val bottomIcon4Text: TextView = view.findViewById(R.id.icon4Text)

        val bottomIcon5: LinearLayout = view.findViewById(R.id.icon5)
        val bottomIcon5Img: ImageView = view.findViewById(R.id.icon5Img)
        val bottomIcon5Text: TextView = view.findViewById(R.id.icon5Text)

        bottomIcon4.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)

            bottomIcon1Img.setImageResource(R.drawable.bottom_bar_icon1)
            bottomIcon1Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon2Img.setImageResource(R.drawable.bottombar_icon2)
            bottomIcon2Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon3Img.setImageResource(R.drawable.bottombar_icon3)
            bottomIcon3Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon4Img.setImageResource(R.drawable.bottombar_icon4_selected)
            bottomIcon4Text.setTextColor(resources.getColor(R.color.active))

            bottomIcon5Img.setImageResource(R.drawable.bottombar_icon5)
            bottomIcon5Text.setTextColor(resources.getColor(R.color.lightBlue))

        }

        bottomIcon2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatFragment)

            bottomIcon1Img.setImageResource(R.drawable.bottom_bar_icon1)
            bottomIcon1Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon2Img.setImageResource(R.drawable.bottombar_icon2_selected)
            bottomIcon2Text.setTextColor(resources.getColor(R.color.active))

            bottomIcon4Img.setImageResource(R.drawable.bottombar_icon4)
            bottomIcon4Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon3Img.setImageResource(R.drawable.bottombar_icon3)
            bottomIcon3Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon5Img.setImageResource(R.drawable.bottombar_icon5)
            bottomIcon5Text.setTextColor(resources.getColor(R.color.lightBlue))
        }
        bottomIcon3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_exploreFragment)

            bottomIcon1Img.setImageResource(R.drawable.bottom_bar_icon1)
            bottomIcon1Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon2Img.setImageResource(R.drawable.bottombar_icon2)
            bottomIcon2Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon4Img.setImageResource(R.drawable.bottombar_icon4)
            bottomIcon4Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon3Img.setImageResource(R.drawable.bottombar_icon3_selected)
            bottomIcon3Text.setTextColor(resources.getColor(R.color.active))

            bottomIcon5Img.setImageResource(R.drawable.bottombar_icon5)
            bottomIcon5Text.setTextColor(resources.getColor(R.color.lightBlue))
        }

        bottomIcon1.setOnClickListener {
            bottomIcon1Img.setImageResource(R.drawable.bottombar_icon1_selected)
            bottomIcon1Text.setTextColor(resources.getColor(R.color.active))

            bottomIcon4Img.setImageResource(R.drawable.bottombar_icon4)
            bottomIcon4Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon2Img.setImageResource(R.drawable.bottombar_icon2)
            bottomIcon2Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon3Img.setImageResource(R.drawable.bottombar_icon3)
            bottomIcon3Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon5Img.setImageResource(R.drawable.bottombar_icon5)
            bottomIcon5Text.setTextColor(resources.getColor(R.color.lightBlue))
//            val fragmentManager = supportFragmentManager
//            if (fragmentManager.backStackEntryCount > 0) {
//                onBackPressed()
//            }
        }

        bottomIcon5.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notesFragment)

            bottomIcon1Img.setImageResource(R.drawable.bottom_bar_icon1)
            bottomIcon1Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon2Img.setImageResource(R.drawable.bottombar_icon2)
            bottomIcon2Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon3Img.setImageResource(R.drawable.bottombar_icon3)
            bottomIcon3Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon4Img.setImageResource(R.drawable.bottombar_icon4)
            bottomIcon4Text.setTextColor(resources.getColor(R.color.lightBlue))

            bottomIcon5Img.setImageResource(R.drawable.notes_icon)

            bottomIcon5Text.setTextColor(resources.getColor(R.color.active))

        }
    }
}