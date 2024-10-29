package com.insoft.collegehub.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.insoft.collegehub.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatFragment : Fragment() {
    // creating variables on below line.
    lateinit var responseTV: TextView
    lateinit var questionTV: TextView
    lateinit var send: MaterialCardView
    lateinit var progressBar: ProgressBar
    lateinit var queryEdt: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        responseTV = view.findViewById(R.id.idTVResponse)
        questionTV = view.findViewById(R.id.idTVQuestion)
        queryEdt =view. findViewById(R.id.idEdtQuery)
        progressBar = view.findViewById(R.id.progress_bar)

        val generativeModel =
            GenerativeModel(
                // Specify a Gemini model appropriate for your use case
                modelName = "gemini-1.5-flash",
                // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                apiKey = "apiKey"
//                requireContext().getString(R.string.key)
                )

        send = view.findViewById(R.id.send)
        send.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            responseTV.text = "Please wait.."
            questionTV.text = "Prompt: ${queryEdt.text.toString()}"

            if (queryEdt.text.toString().isNotEmpty()) {
                // Launch the coroutine to handle the network call
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        // Call the getResponse suspend function
                        val response = getResponse(generativeModel, queryEdt.text.toString())

                        // Update the UI on the main thread
                        launch(Dispatchers.Main) {
                            responseTV.text = response.toString()
                            progressBar.visibility = View.GONE
                            queryEdt.text = null
                        }
                    } catch (e: Exception) {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "Failed to get a response: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            queryEdt.text = null
                            progressBar.visibility = View.GONE

                        }
                    }
                }
            } else {
                progressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    "Please enter your query..",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

//            // validating text
//            if (queryEdt.text.toString().length > 0) {
//                // calling get response to get the response.
//                getResponse(queryEdt.text.toString(), queryEdt.text.toString())
//            } else {
//                Toast.makeText(
//                    requireContext(),
//                    "Please enter your query..",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
        // adding editor action listener for edit text on below line.

        queryEdt.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                // setting response tv on below line.
                responseTV.text = "Please wait.."
                questionTV.text = "Prompt: ${queryEdt.text.toString()}"
                progressBar.visibility = View.VISIBLE

                if (queryEdt.text.toString().isNotEmpty()) {
                    // Launch the coroutine to handle the network call
                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                        try {
                            // Call the getResponse suspend function
                            val response = getResponse(generativeModel, queryEdt.text.toString())

                            // Update the UI on the main thread
                            launch(Dispatchers.Main) {
                                responseTV.text = response.toString()
                                queryEdt.text = null
                                progressBar.visibility = View.GONE
                            }
                        } catch (e: Exception) {
                            launch(Dispatchers.Main) {
                                Toast.makeText(
                                    requireContext(),
                                    "Failed to get a response: ${e.message}",

                                    Toast.LENGTH_SHORT
                                ).show()
                                queryEdt.text = null
                                progressBar.visibility = View.GONE
                            }
                        }
                    }
                } else {
                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Please enter your query..",
                        Toast.LENGTH_SHORT
                    ).show()
                }


//                if (queryEdt.text.toString().length > 0) {
//                    // calling get response to get the response.
//                    getResponse(queryEdt.text.toString(), queryEdt.text.toString())
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Please enter your query..",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
                return@OnEditorActionListener true
            }
            false
        })



        return view
    }

    private suspend fun getResponse(generativeModel: GenerativeModel, prompt: String) : String{
        val response = generativeModel.generateContent(prompt)
        print(response.text)
        return response.text.toString()
    }
}