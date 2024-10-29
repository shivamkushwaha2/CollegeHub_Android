package com.insoft.collegehub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.insoft.collegehub.R

class bannerAdapter(private val context: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private val images = arrayOf<Int>(
        R.drawable.ald_img1,
        R.drawable.ald_img2,
        R.drawable.ald_img3
    )


    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater
        val view: View = layoutInflater!!.inflate(R.layout.banner_item, null)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(images[position])
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }
}