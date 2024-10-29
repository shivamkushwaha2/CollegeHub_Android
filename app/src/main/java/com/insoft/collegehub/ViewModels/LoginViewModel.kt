package com.insoft.collegehub.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insoft.collegehub.Repository.AuthRepository
import com.insoft.collegehub.models.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: LiveData<LoginResponse?> get() = _loginResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _signUpResult = MutableLiveData<LoginResponse?>()
    val signUpResult: LiveData<LoginResponse?> get() = _signUpResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.login(email, password)
                Log.d("response", "login: "+response)
                _loginResult.postValue(response)
            } catch (e: Exception) {
                Log.d("error", e.toString())
                _error.postValue(e.message)
            }
        }
    }

    fun signUp(name:String,email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.signup(name,email, password)
                Log.d("response", "signup: "+response)
                _signUpResult.postValue(response)
            } catch (e: Exception) {
                Log.d("error", e.toString())
                _error.postValue(e.message.toString())
            }
        }
    }
}
