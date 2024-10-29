package com.insoft.collegehub.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.insoft.collegehub.MainActivity
import com.insoft.collegehub.R
import com.insoft.collegehub.Repository.AuthRepository
import com.insoft.collegehub.ViewModels.LoginViewModel
import com.insoft.collegehub.ViewModels.LoginViewModelFactory
import com.insoft.collegehub.ViewModels.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the user is already logged in
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("token", null)

        // If token exists, directly go to MainActivity
        if (token != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // End the LoginActivity so the user can't go back to it
            return // Exit the onCreate() so the rest of the code doesn't execute
        }



        setContentView(R.layout.activity_login)

        // Initialize AuthRepository
        val authRepository = AuthRepository()
        progressBar = findViewById(R.id.progressBar)

        // Initialize ViewModel with the factory
        val factory = LoginViewModelFactory(authRepository)
//        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        viewModel = ViewModelProvider(this, ViewModelFactory { LoginViewModel(authRepository) }).get(LoginViewModel::class.java)

        val submit = findViewById<MaterialButton>(R.id.get_otp)
        submit.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val email = findViewById<EditText>(R.id.email_et).text.toString()
            val password = findViewById<EditText>(R.id.password_et).text.toString()
            viewModel.login(email, password)
        }

        viewModel.loginResult.observe(this, Observer { loginResponse ->
            // Handle successful login
            progressBar.visibility = View.GONE
             token = loginResponse?.token
            Log.d("token", token.toString() +"response -"+loginResponse)

            // Save the token to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("token", token)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        })
        // Observe errors
        viewModel.error.observe(this, Observer { errorMessage ->
            progressBar.visibility = View.GONE
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })


        val signup = findViewById<TextView>(R.id.login_tv4)
        signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

//    fun logout() {
//        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.remove("token") // Remove the saved token
//        editor.apply()
//
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish() // End MainActivity so the user can't go back
//    }

}