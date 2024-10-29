package com.insoft.collegehub.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//    private const val BASE_URL = "https://college-hub-three.vercel.app/"
//  private const val BASE_URL = "http://localhost:5000/"
private const val BASE_URL = "https://collegehub-mu.vercel.app/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // This will log the full request and response body
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Create Retrofit instance
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to Kotlin objects
            .client(client)
            .build()
    }

    // Create ApiService instance
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
