package com.insoft.collegehub.Fragments

import android.app.Activity
import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.google.android.material.button.MaterialButton
import com.insoft.collegehub.R
import com.insoft.collegehub.Repository.notesRepository
import com.insoft.collegehub.ViewModels.NotesViewModel
import com.insoft.collegehub.ViewModels.ViewModelFactory
import com.insoft.collegehub.bottomSheets.SubjectsBottomSheet
import java.io.File


class NotesFragment : Fragment(), SubjectsBottomSheet.SubjectSelectionListener {

    lateinit var course:TextView
    lateinit var semester:TextView
    lateinit var subject:TextView
    lateinit var progressBar: ProgressBar
    private lateinit var viewModel: NotesViewModel
    lateinit var DocumentName:TextView
    lateinit var UploadNotes:TextView
    lateinit var DocumentUri:Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        course = view.findViewById(R.id.course)
        semester = view.findViewById(R.id.semester)
        subject = view.findViewById(R.id.subject)

        progressBar = view.findViewById(R.id.progressBar)
        val notesRepository = notesRepository()
        viewModel = ViewModelProvider(this, ViewModelFactory { NotesViewModel(notesRepository) }).get(NotesViewModel::class.java)

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs",Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email",null)

        DocumentName = view.findViewById(R.id.documentName)
        DocumentName.visibility = View.GONE

        UploadNotes = view.findViewById(R.id.UploadNotesTv)
        UploadNotes.text = "Upload Notes"

        val upload = view.findViewById<CardView>(R.id.uploadNotes)
        upload.setOnClickListener {
            if(UploadNotes.text == "Submit"){
                uploadNote()
            }else {
                progressBar.visibility = View.VISIBLE
                viewModel.checkPermission(email.toString())
                Toast.makeText(
                    requireContext(),
                    "Checking Permission to Upload",
                    Toast.LENGTH_SHORT
                ).show()
//            selectDocument()
            }
        }

        val viewNotes = view.findViewById<MaterialButton>(R.id.viewnotes)
        viewNotes.setOnClickListener {
            val course = course.text.toString()
            val semester = semester.text.toString()
            val subject = subject.text.toString()

            if (course=="" || semester=="" || subject==""){
                Toast.makeText(context, "Please select course, semester and subject", Toast.LENGTH_SHORT).show()
            }else {
//                progressBar.visibility = View.VISIBLE
//                viewModel.getNotes(course,semester,subject)

                val bundle = Bundle().apply {
                    putString("course", course)
                    putString("semester", semester)
                    putString("subject", subject)
                }

                findNavController().navigate(R.id.action_notesFragment_to_viewNotesFragment, bundle)



            }
        }

        course.setOnClickListener {
            showCustomPopup()
        }
        semester.setOnClickListener {
            showSemesterPopup()
        }
        subject.setOnClickListener {
            val bottomSheetFragment = SubjectsBottomSheet()
            bottomSheetFragment.setSubjectSelectionListener(this)
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }

//        viewModel.getNoteStatus.observe(viewLifecycleOwner) { response ->
//
//            progressBar.visibility = View.GONE
//        }

        viewModel.permission.observe(viewLifecycleOwner) { response ->
            Log.d("permission response", response?.message.toString())
            Toast.makeText(requireContext(), response?.message.toString(), Toast.LENGTH_LONG).show()
            progressBar.visibility = View.GONE
           if (response?.message.toString()=="Upload Allowed")
           {
               selectDocument()
           }
        }
        // Observe errors
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            progressBar.visibility = View.GONE
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }


        return view
    }
    override fun onSubjectSelected(subject: String) {
        this.subject.text = subject
    }
    private fun showCustomPopup() {
        // Create a dialog instance
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Remove title bar
        dialog.setContentView(R.layout.custom_popup) // Use the custom layout
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        // Get references to each TextView in the custom popup
        val bcaOption: TextView = dialog.findViewById(R.id.bcaOption)
        val mcaOption: TextView = dialog.findViewById(R.id.mcaOption)
//        val dataScienceOption: TextView = dialog.findViewById(R.id.dataScienceOption)

        // Handle course selection
        bcaOption.setOnClickListener {
            course.text = "BCA"
            dialog.dismiss() // Close the popup when a course is selected
        }

        mcaOption.setOnClickListener {
            course.text = "MCA"
            dialog.dismiss()
        }


//        dataScienceOption.setOnClickListener {
//            courseTextView.text = "BCA Data Science"
//            dialog.dismiss()
//        }

        // Show the dialog
        dialog.show()
    }
    private fun showSemesterPopup() {
        // Create a dialog instance
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Remove title bar
        dialog.setContentView(R.layout.semester_popup) // Use the custom layout
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        // Get references to each TextView in the custom popup
        val one: TextView = dialog.findViewById(R.id.one)
        val two: TextView = dialog.findViewById(R.id.two)
        val three: TextView = dialog.findViewById(R.id.three)
        val four: TextView = dialog.findViewById(R.id.four)
        val five: TextView = dialog.findViewById(R.id.five)
        val six: TextView = dialog.findViewById(R.id.six)

//        val dataScienceOption: TextView = dialog.findViewById(R.id.dataScienceOption)

        // Handle course selection
        one.setOnClickListener {
            semester.text = one.text
            dialog.dismiss() // Close the popup when a course is selected
        }

        two.setOnClickListener {
            semester.text = two.text
            dialog.dismiss()
        }
        three.setOnClickListener {
            semester.text = three.text
            dialog.dismiss()
        }
        four.setOnClickListener {
            semester.text = four.text
            dialog.dismiss()
        }
        five.setOnClickListener {
            semester.text = five.text
            dialog.dismiss()
        }
        six.setOnClickListener {
            semester.text = six.text
            dialog.dismiss()
        }

//        dataScienceOption.setOnClickListener {
//            courseTextView.text = "BCA Data Science"
//            dialog.dismiss()
//        }

        // Show the dialog
        dialog.show()
    }

    // Activity result launcher to pick the document
    private val selectDocumentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val documentUri: Uri? = data?.data
            if (documentUri != null) {
                DocumentUri = documentUri
                Toast.makeText(requireContext(), "Document selected $documentUri", Toast.LENGTH_SHORT).show()
                DocumentName.text = documentUri.toString()
                DocumentName.visibility = View.VISIBLE
                UploadNotes.text = "Submit"
            } else {
                Toast.makeText(requireContext(), "Document selection failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun selectDocument() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // For Android 13+, specific permissions are not required for documents, only for media
            openDocumentPicker()
        } else {
            // For Android 12 and lower, handle storage permissions (if needed)
            if (checkStoragePermission()) {
                openDocumentPicker()
            } else {
                requestStoragePermission()
            }
        }
    }
    // Function to open the document picker
    private fun openDocumentPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*" // Allow any document type
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
        }
        selectDocumentLauncher.launch(intent)
    }

    // Function to check for storage permission (for older Android versions)
    private fun checkStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    // Function to request storage permission
    private fun requestStoragePermission() {
        requestPermissions(
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_CODE
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE && grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            openDocumentPicker()
        } else {
            Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadNote() {
//        val file = File(DocumentUri.path!!)
        val file = getFileFromUri(requireContext(), DocumentUri)
        val course = course.text.toString()
        val semester = semester.text.toString()
        val subject = subject.text.toString()

        if (course=="" || semester=="" || subject==""){
            Toast.makeText(context, "Please select course, semester and subject", Toast.LENGTH_SHORT).show()
        }
        else {
            progressBar.visibility = View.VISIBLE
            viewModel.uploadNote(file!!, course, semester, subject)

            viewModel.uploadStatus.observe(viewLifecycleOwner) { result ->
                result.onSuccess { response ->
                    Toast.makeText(
                        context,
                        "Upload successful: ${response.noteUrl}",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBar.visibility = View.GONE
                }.onFailure { error ->
                    Toast.makeText(context, "Upload failed: ${error.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("Upload failed:", error.toString())
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
    private fun getFileFromUri(context: Context, uri: Uri): File? {
        return try {
            val contentResolver = context.contentResolver

            // Retrieve the original file name from URI metadata
            var fileName = "tempFile"
            contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                fileName = cursor.getString(nameIndex)
            }

            val inputStream = contentResolver.openInputStream(uri) ?: return null
            val tempFile = File(context.cacheDir, fileName)

            // Copy the input stream data to the temp file
            tempFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }

            tempFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 101
    }

}