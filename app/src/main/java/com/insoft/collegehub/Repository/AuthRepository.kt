package com.insoft.collegehub.Repository

import com.insoft.collegehub.Api.ApiService
import com.insoft.collegehub.Api.RetrofitInstance
import com.insoft.collegehub.models.LoginRequest
import com.insoft.collegehub.models.LoginResponse
import com.insoft.collegehub.models.SignupRequest
import retrofit2.Response

class AuthRepository {

    // Use RetrofitInstance to access the ApiService
    suspend fun login(email: String, password: String): LoginResponse? {
        val request = LoginRequest(email, password)
        try {
            val response: Response<LoginResponse> = RetrofitInstance.apiService.login(request)

            if (response.isSuccessful) {
                // Return the successful login response
                return response.body()
            } else {
                // Parse error body if the response is not successful
                val errorBody = response.errorBody()?.string()
                throw Exception(errorBody)
            }
        } catch (e: Exception) {
            // Handle exceptions and propagate them upwards
            throw Exception(e.message)
        }
    }

    suspend fun signup(name:String,email: String, password: String): LoginResponse? {
        val request = SignupRequest(name,email, password)
        try {
            val response: Response<LoginResponse> = RetrofitInstance.apiService.SignUp(request)

            if (response.isSuccessful) {
                // Return the successful login response
                return response.body()
            } else {
                // Parse error body if the response is not successful
                val errorBody = response.errorBody()?.string()
                throw Exception(errorBody)
            }
        } catch (e: Exception) {
            // Handle exceptions and propagate them upwards
            throw Exception(e.message)
        }
    }
}


