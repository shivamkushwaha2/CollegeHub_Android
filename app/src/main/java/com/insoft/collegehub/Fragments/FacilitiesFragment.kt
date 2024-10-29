package com.insoft.collegehub.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import com.google.android.material.card.MaterialCardView
import com.insoft.collegehub.R
import com.insoft.collegehub.databinding.FragmentFacilitiesBinding

class FacilitiesFragment : Fragment() {

    lateinit var binding:FragmentFacilitiesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_facilities, container, false)


        binding.Tv1.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/about1")
        }

        binding.Tv2.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/library-rules2")
        }

        binding.Tv3.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/collection2")
        }

        binding.Tv4.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/services2")
        }

        binding.Tv5.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/pdf/library/Alphabetical%20List%20of%20Print%20Journals.pdf")
        }
        binding.Tv6.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/e-journals-available-through-ugc--inflibnet-e-shodhsindhu")
        }
        binding.Tv7.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/e-journals-available-through-subscription")
        }
        binding.Tv8.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/e-databases1")
        }
        binding.Tv9.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/e-books2")
        }
        binding.Tv10.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/seminar-conference-workshop-symposium2")
        }
        binding.Tv11.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/plagiarism2")
        }
        binding.Tv12.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/open-educational-resources")
        }
        binding.Tv13.setOnClickListener {
            openCustomTab(requireContext(), "https://idp.allduniv.ac.in/")
        }
        binding.Tv14.setOnClickListener {
            openCustomTab(requireContext(), "http://14.139.244.251/Webopac/Home.aspx")
        }
        binding.Tv15.setOnClickListener {
            openCustomTab(requireContext(), "http://172.16.103.228:8080/jspui/")
        }
        binding.Tv16.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/pdf/library/Submission%20of%20Ph.D.%20Thesis%20(Soft%20Copy).pdf")
        }
        binding.Tv17.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.irins.org/")
        }
        binding.Tv18.setOnClickListener {
            openCustomTab(requireContext(), "https://shodhchakra.inflibnet.ac.in/")
        }
        binding.Tv19.setOnClickListener {
            openCustomTab(requireContext(), "https://vidwan.inflibnet.ac.in/")
        }
        binding.Tv20.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/contact-us2")
        }
        binding.Tv21.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/downloads2")
        }
        binding.Tv22.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/amaranatha-jha-hostel")
        }
        binding.Tv23.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/diamond-jubilee-hostel")
        }
        binding.Tv24.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/sir-g-n-jha-hostel")
        }
        binding.Tv25.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/international-house")
        }
        binding.Tv26.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/sir-p-c-b-hostel")
        }
        binding.Tv27.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/sir-sunder-lal-hostel")
        }
        binding.Tv28.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/dr-tara-chand-hostel")
        }
        binding.Tv29.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/sir-radha-krishnan-boys-hostel")
        }
        binding.Tv30.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/shatabdi-boys-hostel")
        }
        binding.Tv31.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/priya-darshini-hostel")
        }
        binding.Tv32.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/shatabdi-girls-hostel")
        }
        binding.Tv33.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/sarojini-naidu-hostel")
        }
        binding.Tv34.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/kalpana-chawala-hostel")
        }
        binding.Tv35.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/hall-of-residence")
        }
        binding.Tv36.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/mahadevi-verma-girl-s-hostel")
        }

//        binding.card4.setOnClickListener {
//            openCustomTab(requireContext(), "https://allduniv.ac.in/contact_us")
//        }

        binding.card5.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/national-cadet-corps")
        }
        binding.card6.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/national-service-scheme")
        }
        binding.card7.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/athletic-association")
        }
        binding.card8.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/facilities/guest-house")
        }

        return binding.root
    }

    private fun openCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    }
}