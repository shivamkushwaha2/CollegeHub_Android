package com.insoft.collegehub.Intro

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.insoft.collegehub.Login.LoginActivity
import com.insoft.collegehub.MainActivity
import com.insoft.collegehub.R
import com.insoft.collegehub.Utils.Constants
import com.insoft.collegehub.adapter.IntroPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class IntroActivity : AppCompatActivity() {

    private lateinit var skipButton: TextView
    private lateinit var nextButton: MaterialButton

    lateinit var viewPager: ViewPager2
    lateinit var dot1: DotsIndicator
    lateinit var viewAdapter: IntroPagerAdapter
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager = findViewById(R.id.viewPager)
        nextButton = findViewById(R.id.nextButton)

        val fragments = listOf(
            IntroFragment1(),
            IntroFragment2(),
            IntroFragment3()
        )

        sharedPreferences = this.getSharedPreferences(Constants.LOCAL_STORAGE, MODE_PRIVATE)

        val adapter = IntroPagerAdapter(this, fragments)
        viewPager.adapter = adapter

        dot1=findViewById(R.id.dot1);

        dot1.setViewPager2(viewPager)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Check if the current page is the last page
                if (position == fragments.size - 1) {
                    nextButton.text = "Let’s get started"
                } else {
                    nextButton.text = "Next"
                }
            }
        })

        nextButton.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem < fragments.size - 1) {
                viewPager.currentItem = currentItem + 1
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            if (nextButton.text == "Let’s get started"){
                val editor = sharedPreferences.edit()
                editor.putString(Constants.LETSGETSTARTEDCLICKED, "true")
                editor.apply()
            }
        }

//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                if (position == fragments.size - 1) {
//                    nextButton.text = "Finish"
//                } else {
//                    nextButton.text = "Next"
//                }
//            }
//        })
    }
}
