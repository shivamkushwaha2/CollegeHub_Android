package com.insoft.collegehub.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.insoft.collegehub.R
import com.insoft.collegehub.Repository.notesRepository
import com.insoft.collegehub.ViewModels.NotesViewModel
import com.insoft.collegehub.ViewModels.ViewModelFactory
import com.insoft.collegehub.adapter.NotesAdapter


class ViewNotesFragment : Fragment() {
     var course:String = ""
    var semester: String = ""
     var subject: String = ""
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: NotesViewModel
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_notes, container, false)

         course = arguments?.getString("course").toString()
         semester = arguments?.getString("semester").toString()
         subject = arguments?.getString("subject").toString()
        Log.d("onCreateView", " $course $semester $subject")

        progressBar=view.findViewById(R.id.progressBar)
        progressBar.visibility =  View.VISIBLE

        recyclerView = view.findViewById(R.id.recyclerView)


        val notesRepository = notesRepository()
        viewModel = ViewModelProvider(this, ViewModelFactory { NotesViewModel(notesRepository) }).get(NotesViewModel::class.java)

        viewModel.getNotes(course,semester,subject)


        viewModel.getNoteStatus.observe(viewLifecycleOwner) { response ->
            val adapter = NotesAdapter(requireContext(),response)
            recyclerView.layoutManager= LinearLayoutManager(requireContext())
            recyclerView.adapter =adapter
            progressBar.visibility = View.GONE
        }

        // Observe errors
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            progressBar.visibility = View.GONE
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }

        return view
    }

}