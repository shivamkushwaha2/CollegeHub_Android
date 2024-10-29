package com.insoft.collegehub.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.insoft.collegehub.R

class SubjectsBottomSheet : BottomSheetDialogFragment() {
    private var listener: SubjectSelectionListener? = null

    fun setSubjectSelectionListener(listener: SubjectSelectionListener) {
        this.listener = listener
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_subjects_bottom_sheet, container, false)
        val cancelButton = view.findViewById<ImageView>(R.id.cancelSheet)
        cancelButton.setOnClickListener {
            dismiss() // Dismiss the bottom sheet when cancel button is clicked
        }
        val card1: CardView = view.findViewById(R.id.card1)
        card1.setOnClickListener {
            listener?.onSubjectSelected("Programming in ‘C’ with Data Structures")
            dismiss()
        }
        val card2: CardView = view.findViewById(R.id.card2)
        card2.setOnClickListener {
            listener?.onSubjectSelected("Database Management System")
            dismiss()
        }
        val card3: CardView = view.findViewById(R.id.card3)
        card3.setOnClickListener {
            listener?.onSubjectSelected("Computer Organization &amp; Architecture")
            dismiss()
        }
        val card4: CardView = view.findViewById(R.id.card4)
        card4.setOnClickListener {
            listener?.onSubjectSelected("Principles of Management")
            dismiss()
        }
        val card5: CardView = view.findViewById(R.id.card5)
        card5.setOnClickListener {
            listener?.onSubjectSelected("Software Engineering &amp; CASE Tools")
            dismiss()
        }

        val card6: CardView = view.findViewById(R.id.card6)
        card6.setOnClickListener {
            listener?.onSubjectSelected("Discrete Structures &amp; Graph Theory\"")
            dismiss()
        }

        val card7: CardView = view.findViewById(R.id.card7)
        card7.setOnClickListener {
            listener?.onSubjectSelected("Theory of Computation")
            dismiss()
        }

        val card9: CardView = view.findViewById(R.id.card9)
        card9.setOnClickListener {
            listener?.onSubjectSelected("OOPS with PYTHON")
            dismiss()
        }

        val card10: CardView = view.findViewById(R.id.card10)
        card10.setOnClickListener {
            listener?.onSubjectSelected("Artificial Intelligence &amp; Robotics")
            dismiss()
        }

        val card11: CardView = view.findViewById(R.id.card11)
        card11.setOnClickListener {
            listener?.onSubjectSelected("Design and Analysis of Algorithms")
            dismiss()
        }

        val card12: CardView = view.findViewById(R.id.card12)
        card12.setOnClickListener {
            listener?.onSubjectSelected("Operating System &amp; LINUX Shell Programming")
            dismiss()
        }

        val card13: CardView = view.findViewById(R.id.card13)
        card13.setOnClickListener {
            listener?.onSubjectSelected("Data Communication &amp; Networks")
            dismiss()
        }

        val card14: CardView = view.findViewById(R.id.card14)
        card14.setOnClickListener {
            listener?.onSubjectSelected(".Net Framework with C#")
            dismiss()
        }

        val card15: CardView = view.findViewById(R.id.card15)
        card15.setOnClickListener {
            listener?.onSubjectSelected("Principles of Cryptography and Cyber Security")
            dismiss()
        }

        val card16: CardView = view.findViewById(R.id.card16)
        card16.setOnClickListener {
            listener?.onSubjectSelected("Compiler Design")
            dismiss()
        }

        val card17: CardView = view.findViewById(R.id.card17)
        card17.setOnClickListener {
            listener?.onSubjectSelected("Optimization Techniques")
            dismiss()
        }
        val card18: CardView = view.findViewById(R.id.card18)
        card18.setOnClickListener {
            listener?.onSubjectSelected("Core and Advanced Java Script")
            dismiss()
        }
        val card19: CardView = view.findViewById(R.id.card19)
        card19.setOnClickListener {
            listener?.onSubjectSelected("Mobile Computing &amp; Applications")
            dismiss()
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

    interface SubjectSelectionListener {
        fun onSubjectSelected(subject: String)
    }


}