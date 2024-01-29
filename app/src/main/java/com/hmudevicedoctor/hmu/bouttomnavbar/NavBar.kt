package com.hmudevicedoctor.hmu.bouttomnavbar

import android.accounts.AccountManager.KEY_PASSWORD
import android.accounts.AccountManager.KEY_USERDATA
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.hmudevicedoctor.hmu.R
import com.hmudevicedoctor.hmu.authenticationhmu.AgeActivity
import com.hmudevicedoctor.hmu.authenticationhmu.GetStarted
import io.grpc.Context

class NavBar : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var button2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)
        button=findViewById(R.id.button)
        button2=findViewById(R.id.button2)
        button.setOnClickListener {
            val i = Intent(this, GetStarted::class.java)
            startActivity(i)
            val sp = getSharedPreferences("login",MODE_PRIVATE)
            sp.edit().putBoolean("logged", false).apply()
        }
        button2.setOnClickListener {
            val i = Intent(this, AgeActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}