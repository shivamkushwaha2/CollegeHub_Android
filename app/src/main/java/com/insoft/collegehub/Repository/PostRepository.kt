package com.insoft.collegehub.Repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.insoft.collegehub.Api.RetrofitInstance
import com.insoft.collegehub.Utils.Utils
import com.insoft.collegehub.models.Post
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File

class PostRepository {

    suspend fun getPosts(token: String): List<Post>? {
        try {
            val bearerToken = "Bearer $token"  // Prepare the Bearer token
            val response: Response<List<Post>> = RetrofitInstance.apiService.getPosts(bearerToken)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.d("getPosts", response.errorBody()!!.string())
                throw Exception("Error: ${response.code()} - ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to fetch posts: ${e.message}")
        }
    }
    suspend fun likePost(postId: String, token: String): Post? {
        try {
            val bearerToken = "Bearer $token"
            val response: Response<Post> = RetrofitInstance.apiService.likePost(postId, bearerToken)
            if (response.isSuccessful) {
                return response.body()
            } else {
                throw Exception("Error: ${response.code()} - ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to like post: ${e.message}")
        }
    }
    suspend fun createPost(content: String, imageUri: Uri?, token: String, context: Context): Post? {
        try {
            val bearerToken = "Bearer $token"

            // Prepare the content part
            val contentRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), content)

            // Prepare the image part if an image is selected
            var imagePart: MultipartBody.Part? = null
            if (imageUri != null) {
                val file = File(Utils.getRealPathFromUri(context, imageUri)) // Convert Uri to actual file path
                val imageRequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                imagePart = MultipartBody.Part.createFormData("image", file.name, imageRequestBody)
            }

            val response: Response<Post> = RetrofitInstance.apiService.createPost(contentRequestBody, imagePart, bearerToken)
            if (response.isSuccessful) {
                return response.body()
            } else {
                throw Exception("Error: ${response.code()} - ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to create post: ${e.message}")
        }
    }
}
