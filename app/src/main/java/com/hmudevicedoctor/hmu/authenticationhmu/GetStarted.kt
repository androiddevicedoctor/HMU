package com.hmudevicedoctor.hmu.authenticationhmu

import android.content.Intent
import android.graphics.Color
import android.media.session.MediaController
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hmudevicedoctor.hmu.R

class GetStarted : AppCompatActivity() {
    private lateinit var get_started: TextView
    private lateinit var login: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        login = findViewById(R.id.login)
        get_started = findViewById(R.id.get_started)

//        val login: String=login.toString()

        get_started.setOnClickListener {
            startActivity(Intent(this@GetStarted, EnterNumberActivity::class.java))
            get_started.setBackgroundResource(R.drawable.shape_black_round_corner)
            get_started.setTextColor(ContextCompat.getColor(this@GetStarted, R.color.white))
        }

        val loginstr = "Already have an account? Log in"
        val spannableString = SpannableString(loginstr)

        val termAndCondition = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@GetStarted, "Currently page is not available.", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLACK
                ds.isUnderlineText = false
            }
        }
        spannableString.setSpan(termAndCondition, 25, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        login.text = spannableString
        login.movementMethod = LinkMovementMethod.getInstance()
    }
}