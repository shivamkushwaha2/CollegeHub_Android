package com.insoft.collegehub.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.card.MaterialCardView
import com.insoft.collegehub.R
import com.insoft.collegehub.adapter.bannerAdapter
import com.insoft.collegehub.bottomSheets.CollegesBottomSheet
import com.insoft.collegehub.bottomSheets.centresBottomsheet
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class ExploreFragment : Fragment() {

    lateinit var viewPager: ViewPager
    lateinit var dot1: DotsIndicator
    lateinit var viewAdapter: bannerAdapter
    lateinit var department:CardView
    lateinit var centres:CardView
    lateinit var courses: CardView
    lateinit var colleges:CardView
    lateinit var photoGallery:CardView
    lateinit var videoGallery:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        viewPager=view.findViewById(R.id.view_pager);
        dot1=view.findViewById(R.id.dot1);
        department = view.findViewById(R.id.cv1)
        centres = view.findViewById(R.id.cv2)
        courses = view.findViewById(R.id.cv3)
        colleges = view.findViewById(R.id.cv4)
        photoGallery = view.findViewById(R.id.galleryCard1)
        videoGallery = view.findViewById(R.id.galleryCard2)

        viewAdapter= bannerAdapter(requireContext());
        viewPager.adapter = viewAdapter
        dot1.setViewPager(viewPager);

        photoGallery.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/photo-gallery")
        }
        videoGallery.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/video-gallery")
        }
        department.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/p/244/department")
        }
        courses.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/academics/courses")
        }
        centres.setOnClickListener {
            val bottomSheetFragment = centresBottomsheet()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
        colleges.setOnClickListener {
            val bottomSheetFragment = CollegesBottomSheet()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }

        val studentCard1 = view.findViewById<CardView>(R.id.Studentcard1)
        studentCard1.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/admit-cards")
        }
        val studentCard2 = view.findViewById<CardView>(R.id.Studentcard2)
        studentCard2.setOnClickListener {
            openCustomTab(requireContext(), "http://coe.allduniv.ac.in/result/")
        }
        val studentCard3 = view.findViewById<CardView>(R.id.Studentcard3)
        studentCard3.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/pay-fees")
        }
        val studentCard4 = view.findViewById<CardView>(R.id.Studentcard4)
        studentCard4.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment_to_studentsFragment)
        }
        val viewall = view.findViewById<TextView>(R.id.ViewAllTv2)
        viewall.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment_to_studentsFragment)
        }

        val facilitiescard1 = view.findViewById<CardView>(R.id.facilitiescard1)
        facilitiescard1.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/computer-centre")
        }
        val facilitiescard2 = view.findViewById<CardView>(R.id.facilitiescard2)
        facilitiescard2.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/p/627/early-childhood-care-centre")
        }
        val facilitiescard3 = view.findViewById<CardView>(R.id.facilitiescard3)
        facilitiescard3.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/p/258/incubation-centre")
        }
        val facilitiescard4 = view.findViewById<CardView>(R.id.facilitiescard4)
        facilitiescard4.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment_to_facilitiesFragment)
        }
        val viewall2 = view.findViewById<TextView>(R.id.ViewAllTv3)
        viewall2.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment_to_facilitiesFragment)
        }


        val card1 = view.findViewById<MaterialCardView>(R.id.card1)
        card1.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/academics/academic-calendar")
        }
        val card2 = view.findViewById<MaterialCardView>(R.id.card2)
        card2.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/pdf/holidays.pdf")
        }
        val card3 = view.findViewById<MaterialCardView>(R.id.card3)
        card3.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/student/download-forms")
        }
        val card4 = view.findViewById<MaterialCardView>(R.id.card4)
        card4.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/communication-directory")
        }
        val card5 = view.findViewById<MaterialCardView>(R.id.card5)
        card5.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/p/16/history")
        }
        val card6 = view.findViewById<MaterialCardView>(R.id.card6)
        card6.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/student-feedback")
        }
        val card7 = view.findViewById<MaterialCardView>(R.id.card7)
        card7.setOnClickListener {
            openCustomTab(requireContext(), "http://allduniv.ac.in/pdf/ccash.pdf")
        }
        val card8 = view.findViewById<MaterialCardView>(R.id.card8)
        card8.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/about-uoa/annual-report")
        }
        val card9 = view.findViewById<MaterialCardView>(R.id.card9)
        card9.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/contact_us")
        }

        val twitter = view.findViewById<ImageView>(R.id.twitter)
        twitter.setOnClickListener {
            openCustomTab(requireContext(), "https://x.com/UoA_Official")
        }
        val insta = view.findViewById<ImageView>(R.id.instagram)
        insta.setOnClickListener {
            openCustomTab(requireContext(), "https://www.instagram.com/uoa_official/")
        }
        val linkedin = view.findViewById<ImageView>(R.id.linkedin)
        linkedin.setOnClickListener {
            openCustomTab(requireContext(), "https://www.linkedin.com/school/university-of-allahabad")
        }
        val youtube = view.findViewById<ImageView>(R.id.youtube)
        youtube.setOnClickListener {
            openCustomTab(requireContext(), "https://www.youtube.com/channel/UCv6arA2_Drtm_HkikLMr1Yg/videos")
        }

        return view
    }

    private fun openCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    }

}