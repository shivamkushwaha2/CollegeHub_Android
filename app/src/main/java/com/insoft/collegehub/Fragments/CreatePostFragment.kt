package com.insoft.collegehub.Fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.insoft.collegehub.R
import com.insoft.collegehub.Repository.PostRepository
import com.insoft.collegehub.ViewModels.PostViewModel
import com.insoft.collegehub.ViewModels.PostViewModelFactory
import okhttp3.MultipartBody
import java.io.File

class CreatePostFragment : Fragment() {
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    private lateinit var contentEditText: TextInputEditText
    private lateinit var imageButton: ImageView
    private lateinit var createPostButton: MaterialButton
    lateinit var imageName:TextView
    lateinit var progressBar: ProgressBar

    private val postViewModel: PostViewModel by viewModels { PostViewModelFactory(PostRepository()) }
    private var selectedImageUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_post, container, false)
        progressBar = view.findViewById(R.id.progressBar)

        contentEditText = view.findViewById(R.id.posttext)
        imageButton = view.findViewById(R.id.pickimg)
        createPostButton = view.findViewById(R.id.post)
        imageName = view.findViewById(R.id.imageName)

        imageButton.setOnClickListener {
            requestImagePermissions()
        }
        createPostButton.setOnClickListener {
            val content = contentEditText.text.toString().trim()

            if (content.isNotEmpty()) {

                // Get the token
                val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")

                // Proceed to create the post
                token?.let {
                    postViewModel.createPost(content, selectedImageUri, it, requireContext())
                }
            } else {
                Toast.makeText(context, "Content cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        postViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                Toast.makeText(context, "Posted Successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_createPostFragment_to_homeFragment)
            }
        }

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val data: Intent? = result.data
                // Handle the image selection
                data?.data?.let { uri ->
                    selectedImageUri = uri
                    imageName.text = uri.toString()
                }
            }
        }


        return view
    }

    private fun requestImagePermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES),
                REQUEST_IMAGE_PICK
            )
        } else {
            openImagePicker()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_IMAGE_PICK) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker()
            } else {
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        imagePickerLauncher.launch(intent)
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
//            data?.data?.let { uri ->
//                // Handle the selected image URI (e.g., display it, upload it, etc.)
//                selectedImageUri = uri
//            }
//        }
//    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}