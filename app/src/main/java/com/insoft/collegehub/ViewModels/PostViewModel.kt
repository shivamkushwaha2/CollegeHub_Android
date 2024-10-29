package com.insoft.collegehub.ViewModels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insoft.collegehub.Repository.PostRepository
import com.insoft.collegehub.models.Post
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>?>()
    val posts: LiveData<List<Post>?> get() = _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchPosts(token: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val postList = postRepository.getPosts(token)
                _posts.postValue(postList)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun likePost(postId: String, token: String) {
        viewModelScope.launch {
            try {
                val updatedPost = postRepository.likePost(postId, token)

                if (updatedPost != null) {
                    updatedPost.isLiked = !(updatedPost.isLiked)

                    // Update post in the list after liking/unliking
                    val updatedList = _posts.value?.map { post ->
                        if (post._id == updatedPost._id) updatedPost else post
                    }
                    _posts.postValue(updatedList)
                }
            } catch (e: Exception) {
                _error.postValue("Failed to like post: ${e.message}")
            }
        }
    }

    fun createPost(content: String, imageUri: Uri?, token: String, context: Context) {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val newPost = postRepository.createPost(content, imageUri, token, context)

                if (newPost != null) {
                    val updatedList = _posts.value?.toMutableList()
                    updatedList?.add(0, newPost)
                    _posts.postValue(updatedList)
                }
            } catch (e: Exception) {
                _error.postValue("Failed to create post: ${e.message}")
            } finally {
                _loading.postValue(false)
            }
        }
    }

}
