package com.insoft.collegehub.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.insoft.collegehub.R
import com.insoft.collegehub.Repository.PostRepository
import com.insoft.collegehub.ViewModels.LoginViewModel
import com.insoft.collegehub.ViewModels.PostViewModel
import com.insoft.collegehub.ViewModels.PostViewModelFactory
import com.insoft.collegehub.ViewModels.ViewModelFactory
import com.insoft.collegehub.adapter.PostAdapter


class FeedFragment : Fragment() {


    lateinit var  postViewModel: PostViewModel
//  by viewModels { PostViewModelFactory(PostRepository()) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    lateinit var progressBar: ProgressBar
    var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        progressBar = view.findViewById(R.id.progressBar)

        val postRepository = PostRepository()
        postViewModel = ViewModelProvider(this, ViewModelFactory { PostViewModel(postRepository) }).get(PostViewModel::class.java)

        val addpost = view.findViewById<CardView>(R.id.addPost)
        addpost.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createPostFragment)
        }
        recyclerView = view.findViewById(R.id.post_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Assuming token is retrieved from SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
         token = sharedPreferences.getString("token", "")
        Log.d("fetched token for post", token.toString())
        // Fetch posts using ViewModel
        token?.let {
            postViewModel.fetchPosts(it)

        }


//        // Observe the posts and update the RecyclerView adapter
//        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
//            if (posts != null) {
//                postAdapter = PostAdapter(posts)
//                recyclerView.adapter = postAdapter
//            }
//        }

        // Set up the RecyclerView adapter with a like callback
        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
            if (posts != null) {
                postAdapter = PostAdapter(posts.toMutableList()) { post ->
                    token?.let { authToken ->
                        postViewModel.likePost(post._id, authToken) // Handle like click
                    }
                }
                recyclerView.adapter = postAdapter
            }
        }

        //When posts are updated, notify the adapter of the changes
        postViewModel.posts.observe(viewLifecycleOwner) { updatedPosts ->
            updatedPosts?.let { updatedPostList ->
                for (updatedPost in updatedPostList) {
                    postAdapter.updatePost(updatedPost) // Update the specific post in the adapter
                }
            }
        }


        // Observe errors
        postViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Log.d("post errorMessage", errorMessage)
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        postViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "")

        token?.let {
            postViewModel.fetchPosts(it)

        }
    }

}