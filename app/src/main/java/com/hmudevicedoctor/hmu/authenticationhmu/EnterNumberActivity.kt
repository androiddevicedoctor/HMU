package com.hmudevicedoctor.hmu.authenticationhmu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.hbb20.CountryCodePicker
import com.hmudevicedoctor.hmu.R
import java.util.concurrent.TimeUnit

class EnterNumberActivity : AppCompatActivity() {

    private lateinit var number: EditText
    private lateinit var continue_button: TextView
    private lateinit var back_button: ImageView
    private lateinit var country_code: CountryCodePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_number)
        init()
        onclick()
    }

    private fun onclick() {
        continue_button.setOnClickListener {
            val numberstr: String = number.text.toString()
            val plus = "+"
            val country_code = country_code.selectedCountryCode

            val fullNumber= plus+country_code+numberstr

            continue_button.setBackgroundResource(R.drawable.shape_black_round_corner)
            continue_button.setTextColor(ContextCompat.getColor(this@EnterNumberActivity, R.color.white))

            if (numberstr.length != 10 || !numberstr.matches(Regex("[0-9]+"))) {
                number.error = "Invalid Number"
                // You could also display a Toast here for a more user-friendly message
            } else {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    fullNumber,
                    60,
                    TimeUnit.SECONDS,
                    this,
                    object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                            // Handle verification completion if needed.
                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                            Toast.makeText(this@EnterNumberActivity, "Check your internet", Toast.LENGTH_SHORT).show()
                        }

                        override fun onCodeSent(s: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                            super.onCodeSent(s, forceResendingToken)
                            Toast.makeText(this@EnterNumberActivity, "OTP sent successfully....", Toast.LENGTH_SHORT).show()
                            // Pass the phone number to the next activity
                            startActivity(Intent(this@EnterNumberActivity, EnterOtpActivity::class.java)
                                .putExtra("mobile", fullNumber)
                                .putExtra("verify_otp", s)
                            )
                        }
                    }
                )
            }
        }

        back_button.setOnClickListener {
            startActivity(Intent(this@EnterNumberActivity, GetStarted::class.java))
        }
    }
    private fun init() {
        country_code = findViewById(R.id.country_code)
        number = findViewById(R.id.number)
        continue_button = findViewById(R.id.continue_button)
        back_button = findViewById(R.id.back_button)
    }
}
