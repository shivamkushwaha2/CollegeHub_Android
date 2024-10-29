package com.insoft.collegehub.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.insoft.collegehub.Api.RetrofitInstance
import com.insoft.collegehub.models.NotesUploadResponse
import com.insoft.collegehub.models.PermissionRequest
import com.insoft.collegehub.models.Post
import com.insoft.collegehub.models.SignupRequest
import com.insoft.collegehub.models.noteResponse
import com.insoft.collegehub.models.permissionResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class notesRepository {
    suspend fun permission(email: String): permissionResponse? {
        try {
            val request = PermissionRequest(email,"false")

            val response: Response<permissionResponse> = RetrofitInstance.apiService.checkUploadPermission(request)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.d("checkUploadPermission", response.errorBody()!!.string())
                throw Exception("Error: ${response.code()} - ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to fetch posts: ${e.message}")
        }
    }

    suspend fun uploadNote(file: File, course: String, semester: String, subject: String): Result<NotesUploadResponse> {
        return try {
            val requestFile = file.asRequestBody("application/pdf".toMediaTypeOrNull()) // Adjust MIME type as needed
            val body = MultipartBody.Part.createFormData("notes", file.name, requestFile)
            val coursePart = course.toRequestBody("text/plain".toMediaTypeOrNull())
            val semesterPart = semester.toRequestBody("text/plain".toMediaTypeOrNull())
            val subjectPart = subject.toRequestBody("text/plain".toMediaTypeOrNull())

            val response = RetrofitInstance.apiService.uploadNotes(body, coursePart, semesterPart, subjectPart)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Upload failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getNotes(course: String, semester: String, subject: String): List<noteResponse>? {
        try {
            val response: Response<List<noteResponse>> = RetrofitInstance.apiService.getNotes(course,semester,subject)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.d("getNotes", response.errorBody()!!.string())
                throw Exception("Error: ${response.code()} - ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to getNotes ${e.message}")
        }
    }

    }