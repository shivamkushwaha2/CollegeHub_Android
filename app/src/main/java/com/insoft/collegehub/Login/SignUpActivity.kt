package com.insoft.collegehub.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.insoft.collegehub.MainActivity
import com.insoft.collegehub.R
import com.insoft.collegehub.Repository.AuthRepository
import com.insoft.collegehub.ViewModels.LoginViewModel
import com.insoft.collegehub.ViewModels.LoginViewModelFactory
import com.insoft.collegehub.ViewModels.ViewModelFactory

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    lateinit var progressBar: ProgressBar
    lateinit var firstNameEditText:EditText
    lateinit var lastNameEditText:EditText
    lateinit var emailEditText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        // Initialize AuthRepository
        val authRepository = AuthRepository()
        progressBar = findViewById(R.id.progressBar)

        // Initialize ViewModel with the factory
//        val factory = LoginViewModelFactory(authRepository)
//        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        viewModel = ViewModelProvider(this, ViewModelFactory { LoginViewModel(authRepository) }).get(LoginViewModel::class.java)

        firstNameEditText = findViewById(R.id.firstname)
         lastNameEditText = findViewById(R.id.lastname)
        emailEditText = findViewById(R.id.email)

        firstNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Regular expression to allow only English letters (A-Z, a-z)
                val regex = "^[a-zA-Z]*$".toRegex()

                if (s != null && !regex.matches(s.toString())) {
                    // Show error if input contains non-English characters
                    firstNameEditText.error = "Only English alphabetic characters are allowed."
                } else {
                    firstNameEditText.error = null // Clear error
                }
            }
        })

        lastNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val regex = "^[a-zA-Z]*$".toRegex()

                if (s != null && !regex.matches(s.toString())) {
                    lastNameEditText.error = "Only English alphabetic characters are allowed."
                } else {
                    lastNameEditText.error = null // Clear error
                }

            }
        })
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && !Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    emailEditText.error = "Invalid email address."
                } else {
                    emailEditText.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val submit = findViewById<MaterialButton>(R.id.submit)
        submit.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            // Get inputs from EditText fields
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = findViewById<EditText>(R.id.password_edittext).text.toString()

            // Validate fields
            if (firstNameEditText.error == null && lastNameEditText.error == null &&
                emailEditText.error == null && password.isNotEmpty()) {

                // Combine first and last name
                val name = "$firstName $lastName"

                // No errors, proceed with signup
                viewModel.signUp(name, email, password)
            } else {
                // Show message to the user if there are errors
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Please fix the errors before proceeding", Toast.LENGTH_LONG).show()
            }
        }


        viewModel.signUpResult.observe(this, Observer { loginResponse ->
            // Handle successful login
            progressBar.visibility = View.GONE
            val token = loginResponse?.token
            Log.d("token", token.toString() +"response -"+loginResponse)

            // Save the token to SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("token", token)
            editor.putString("email", loginResponse?.user?.email)
            editor.putString("name", loginResponse?.user?.name)
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


    }
}