package com.insoft.collegehub.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import com.google.android.material.card.MaterialCardView
import com.insoft.collegehub.R
import com.insoft.collegehub.databinding.FragmentStudentsBinding

class StudentsFragment : Fragment() {

    lateinit var binding: FragmentStudentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_students, container, false)

        binding.card1.setOnClickListener {
            openCustomTab(requireContext(), "https://docs.google.com/forms/d/e/1FAIpQLScOCGFRiXZzxcxhNd80QmQ_hO6QouN6HS3IufbHQ35Z2nclAQ/viewform")
        }
        binding.card2.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/achievements")
        }
        binding.card3.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/disciplinary-action-anti-ragging-cell")
        }
        binding.card4.setOnClickListener {
            openCustomTab(requireContext(), "http://coe.allduniv.ac.in/result/List_Result-2023-24.html")
        }
        binding.card5.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/download-forms")
        }

        binding.card6.setOnClickListener {
            invisibleAll()

            binding.Tv1.visibility = View.VISIBLE
            binding.Tv2.visibility = View.VISIBLE
            binding.Tv3.visibility = View.VISIBLE

            binding.card6.strokeColor = resources.getColor(R.color.active)
            binding.img6.setImageResource(R.drawable.down_arrow)

        }

        binding.card7.setOnClickListener {
            invisibleAll()

            binding.Tv4.visibility = View.VISIBLE
            binding.Tv5.visibility = View.VISIBLE
            binding.Tv6.visibility = View.VISIBLE
            binding.Tv7.visibility = View.VISIBLE
            binding.Tv8.visibility = View.VISIBLE
            binding.Tv9.visibility = View.VISIBLE
            binding.Tv10.visibility = View.VISIBLE
            binding.Tv11.visibility = View.VISIBLE
            binding.Tv12.visibility = View.VISIBLE
            binding.Tv13.visibility = View.VISIBLE
            binding.Tv14.visibility = View.VISIBLE

            binding.card7.strokeColor = resources.getColor(R.color.active)
            binding.img7.setImageResource(R.drawable.down_arrow)

        }
        binding.card8.setOnClickListener {
            invisibleAll()

            binding.Tv15.visibility = View.VISIBLE
            binding.Tv16.visibility = View.VISIBLE
            binding.Tv17.visibility = View.VISIBLE

            binding.card8.strokeColor = resources.getColor(R.color.active)
            binding.img8.setImageResource(R.drawable.down_arrow)

        }
        binding.card9.setOnClickListener {
            invisibleAll()

            binding.Tv18.visibility = View.VISIBLE
            binding.Tv19.visibility = View.VISIBLE
            binding.Tv20.visibility = View.VISIBLE
            binding.Tv21.visibility = View.VISIBLE
            binding.Tv22.visibility = View.VISIBLE

            binding.card9.strokeColor = resources.getColor(R.color.active)
            binding.img9.setImageResource(R.drawable.down_arrow)

        }

        binding.Tv1.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/p/205/Admission%202023")
        }

        binding.Tv2.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/p/641/pg-admission-2024")
        }

        binding.Tv3.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/p/263/phd-admission-cret-2022")
        }

        binding.Tv4.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/vice-chancellor-s-desk")
        }

        binding.Tv5.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/dean-of-arts-desk")
        }
        binding.Tv6.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/dean-of-commerce-desk")
        }
        binding.Tv7.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/dean-of-law-desk")
        }
        binding.Tv8.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/dean-of-science-desk")
        }
        binding.Tv9.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/dsw-s-desk")
        }
        binding.Tv10.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/proctor-s-desk")
        }
        binding.Tv11.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/controller-of-examination-desk")
        }
        binding.Tv12.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/registrar-s-desk")
        }
        binding.Tv13.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/finance-officer-desk")
        }
        binding.Tv14.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/desk/hindi-rajbhasha-cell")
        }
        binding.Tv15.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/endowment-scholarship")
        }
        binding.Tv16.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/scholarships-under-government-schemes")
        }
        binding.Tv17.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/meghnad-saha-award-for-research-scholar")
        }
        binding.Tv18.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/vision-and-mission")
        }
        binding.Tv19.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/notice-circular")
        }
        binding.Tv20.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/contact")
        }
        binding.Tv21.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/photo-gallery-student")
        }
        binding.Tv22.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/placement-reports")
        }


        return binding.root
    }
    fun invisibleAll() {
        binding.Tv1.visibility = View.GONE
        binding.Tv2.visibility = View.GONE
        binding.Tv3.visibility = View.GONE
        binding.Tv4.visibility = View.GONE
        binding.Tv5.visibility = View.GONE
        binding.Tv6.visibility = View.GONE
        binding.Tv7.visibility = View.GONE
        binding.Tv8.visibility = View.GONE
        binding.Tv9.visibility = View.GONE
        binding.Tv10.visibility = View.GONE
        binding.Tv11.visibility = View.GONE
        binding.Tv12.visibility = View.GONE
        binding.Tv13.visibility = View.GONE
        binding.Tv14.visibility = View.GONE
        binding.Tv15.visibility = View.GONE
        binding.Tv16.visibility = View.GONE
        binding.Tv17.visibility = View.GONE
        binding.Tv18.visibility = View.GONE
        binding.Tv19.visibility = View.GONE
        binding.Tv20.visibility = View.GONE
        binding.Tv21.visibility = View.GONE
        binding.Tv22.visibility = View.GONE

        binding.card6.strokeColor = resources.getColor(android.R.color.transparent)
        binding.img6.setImageResource(R.drawable.baseline_arrow_forward_ios_24)

        binding.card7.strokeColor = resources.getColor(android.R.color.transparent)
        binding.img7.setImageResource(R.drawable.baseline_arrow_forward_ios_24)

        binding.card8.strokeColor = resources.getColor(android.R.color.transparent)
        binding.img8.setImageResource(R.drawable.baseline_arrow_forward_ios_24)

        binding.card9.strokeColor = resources.getColor(android.R.color.transparent)
        binding.img9.setImageResource(R.drawable.baseline_arrow_forward_ios_24)
    }
    private fun openCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    }

}