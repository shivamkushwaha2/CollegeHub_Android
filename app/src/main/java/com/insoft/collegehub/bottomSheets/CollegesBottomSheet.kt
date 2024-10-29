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

class CollegesBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_colleges_bottom_sheet, container, false)

        val cancelButton = view.findViewById<ImageView>(R.id.cancelSheet)
        cancelButton.setOnClickListener {
            dismiss() // Dismiss the bottom sheet when cancel button is clicked
        }

        val card1 = view.findViewById<CardView>(R.id.card1)
        card1.setOnClickListener {
            openCustomTab(requireContext(), "https://adc-au.in/")
        }
        val card2 = view.findViewById<CardView>(R.id.card2)
        card2.setOnClickListener {
            openCustomTab(requireContext(), "https://akpgc.in/")
        }
        val card3 = view.findViewById<CardView>(R.id.card3)
        card3.setOnClickListener {
            openCustomTab(requireContext(), "https://cmpcollege.ac.in/")
        }
        val card4 = view.findViewById<CardView>(R.id.card4)
        card4.setOnClickListener {
            openCustomTab(requireContext(), "https://ecc.ac.in/")
        }
        val card5 = view.findViewById<CardView>(R.id.card5)
        card5.setOnClickListener {
            openCustomTab(requireContext(), "https://hgdc.ac.in/")
        }
        val card6 = view.findViewById<CardView>(R.id.card6)
        card6.setOnClickListener {
            openCustomTab(requireContext(), "https://isdc.ac.in/")
        }
        val card7 = view.findViewById<CardView>(R.id.card7)
        card7.setOnClickListener {
            openCustomTab(requireContext(), "https://jtgdc.ac.in/")
        }
        val card9 = view.findViewById<CardView>(R.id.card9)
        card9.setOnClickListener {
            openCustomTab(requireContext(), "https://kptc.ac.in/")
        }
        val card10 = view.findViewById<CardView>(R.id.card10)
        card10.setOnClickListener {
            openCustomTab(requireContext(), "https://www.rtmmalld.ac.in/")
        }
        val card11 = view.findViewById<CardView>(R.id.card11)
        card11.setOnClickListener {
            openCustomTab(requireContext(), "https://sskhannagirlsdc.ac.in/")
        }
        val card12 = view.findViewById<CardView>(R.id.card12)
        card12.setOnClickListener {
            openCustomTab(requireContext(), "https://spmau.ac.in/")
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