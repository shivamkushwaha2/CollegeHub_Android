package com.insoft.collegehub

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.insoft.collegehub.Intro.IntroActivity
import com.insoft.collegehub.Login.LoginActivity
import com.insoft.collegehub.Utils.Constants

class SplashScreen : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = this.getSharedPreferences(Constants.LOCAL_STORAGE, Context.MODE_PRIVATE)

        if (sharedPreferences.getString(Constants.LETSGETSTARTEDCLICKED, "false").equals("false")) {
            startActivity(Intent(this@SplashScreen, IntroActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
            finish()
        }
    }
}