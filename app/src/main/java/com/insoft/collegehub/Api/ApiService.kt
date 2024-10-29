package com.insoft.collegehub.Api

import com.insoft.collegehub.models.LoginRequest
import com.insoft.collegehub.models.LoginResponse
import com.insoft.collegehub.models.NotesUploadResponse
import com.insoft.collegehub.models.PermissionRequest
import com.insoft.collegehub.models.Post
import com.insoft.collegehub.models.SignupRequest
import com.insoft.collegehub.models.noteResponse
import com.insoft.collegehub.models.permissionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

// ApiService.kt
interface ApiService {

    @POST("user/signin/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("user/signup/")
    suspend fun SignUp(@Body loginRequest: SignupRequest): Response<LoginResponse>

    @GET("posts/all")
    suspend fun getPosts(@Header("Authorization") token :String): Response<List<Post>>

    @POST("posts/like/{postId}")
    suspend fun likePost(@Path("postId") postId: String, @Header("Authorization") token: String): Response<Post>

    @Multipart
    @POST("posts/create")
    suspend fun createPost(@Part("content") content: RequestBody, @Part image: MultipartBody.Part?, @Header("Authorization") token: String): Response<Post>

    @POST("notes/checkPermission")
    suspend fun checkUploadPermission(@Body request: PermissionRequest):Response<permissionResponse>

    @Multipart
    @POST("notes/upload")
    suspend fun uploadNotes(@Part file: MultipartBody.Part, @Part("course") course: RequestBody, @Part("semester") semester: RequestBody, @Part("subject") subject: RequestBody): Response<NotesUploadResponse>

    @GET("notes/getnotes")
    suspend fun getNotes(@Query("course") course: String, @Query("semester") semester: String,@Query("subject") subject: String): Response<List<noteResponse>>

}
