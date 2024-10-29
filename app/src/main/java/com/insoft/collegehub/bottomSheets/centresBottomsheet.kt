package com.insoft.collegehub.bottomSheets

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.insoft.collegehub.R

class centresBottomsheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_centres_bottomsheet, container, false)

        val cancelButton = view.findViewById<ImageView>(R.id.cancelSheet)
        cancelButton.setOnClickListener {
            dismiss() // Dismiss the bottom sheet when cancel button is clicked
        }


        val card1 = view.findViewById<CardView>(R.id.card1)
        card1.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-of-bioinformatics")
        }
        val card2 = view.findViewById<CardView>(R.id.card2)
        card2.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-of-biotechnology")
        }
        val card3 = view.findViewById<CardView>(R.id.card3)
        card3.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-of-material-science")
        }
        val card4 = view.findViewById<CardView>(R.id.card4)
        card4.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-for-globalization-and-developmental-studies")
        }
        val card5 = view.findViewById<CardView>(R.id.card5)
        card5.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-of-atmospheric-and-ocean-studies")
        }
        val card6 = view.findViewById<CardView>(R.id.card6)
        card6.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-of-environmental-science")
        }
        val card7 = view.findViewById<CardView>(R.id.card7)
        card7.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-of-science-and-society-rural-technology-and-development-")
        }
        val card9 = view.findViewById<CardView>(R.id.card9)
        card9.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-for-theatre-film")
        }
        val card10 = view.findViewById<CardView>(R.id.card10)
        card10.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/centre-institute/centre-of-computer-education")
        }
        val card11 = view.findViewById<CardView>(R.id.card11)
        card11.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/centre-institute/centre-of-food-technology")
        }
        val card12 = view.findViewById<CardView>(R.id.card12)
        card12.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/centre-institute/centre-of-media-studies")
        }
        val card13 = view.findViewById<CardView>(R.id.card13)
        card12.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/centre-institute/centre-of-fashion-design-technology")
        }
        val card14 = view.findViewById<CardView>(R.id.card14)
        card12.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-for-women-s-studies")
        }
        val card15 = view.findViewById<CardView>(R.id.card15)
        card12.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-of-vocational-studies-skill-development")
        }
        val card16 = view.findViewById<CardView>(R.id.card16)
        card12.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/centre-of-behavioural-and-cognitive-science")
        }
        val card17 = view.findViewById<CardView>(R.id.card17)
        card17.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/national-centre-of-experimental-petrology-and-mineralogy")
        }
        val card18 = view.findViewById<CardView>(R.id.card18)
        card18.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/design-innovation-centre")
        }
        val card19 = view.findViewById<CardView>(R.id.card19)
        card19.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/agro-economic-research-centre2")
        }
        val card20 = view.findViewById<CardView>(R.id.card20)
        card20.setOnClickListener {
            openCustomTab(requireContext(), "https://allduniv.ac.in/centre-institute/national-genomics-core-centre")
        }
        val card21 = view.findViewById<CardView>(R.id.card21)
        card21.setOnClickListener {
            openCustomTab(requireContext(), "https://www.allduniv.ac.in/p/234/Dr.-Ambedkar-Centre-of-Excellence-(DACE)")
        }

        return view
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = context?.let { BottomSheetDialog(it, theme) }
        dialog?.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { bottomSheet ->
                val behaviour = BottomSheetBehavior.from(bottomSheet)
                setupNinetyPercentHeight(bottomSheet)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
//                behaviour.isDraggable = false
            }
        }
        return dialog ?: super.onCreateDialog(savedInstanceState)
    }

    private fun setupNinetyPercentHeight(bottomSheet: View) {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels
        val ninetyPercentHeight = (screenHeight * 0.9).toInt() // 90% of screen height

        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = ninetyPercentHeight
        bottomSheet.layoutParams = layoutParams
    }

    private fun openCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    }
}