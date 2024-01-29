package com.hmudevicedoctor.hmu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import com.google.firebase.auth.FirebaseAuth
import com.hmudevicedoctor.hmu.authenticationhmu.GetStarted
import com.hmudevicedoctor.hmu.bouttomnavbar.NavBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// To hide the status bar
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN

// To make the activity fullscreen
        actionBar?.hide()

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            if (FirebaseAuth.getInstance().currentUser != null) {
                val intent = Intent(this@MainActivity, NavBar::class.java)
                startActivity(intent)
                finish()
                return@postDelayed
            }
            val intent = Intent(this@MainActivity, GetStarted::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}