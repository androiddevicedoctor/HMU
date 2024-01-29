package com.hmudevicedoctor.hmu.authenticationhmu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.hmudevicedoctor.hmu.R

class EnterOtpActivity : AppCompatActivity() {

    private lateinit var otp:EditText
    private lateinit var continue_button: TextView
    private lateinit var textView4: TextView
    private lateinit var back_button: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_otp)

//        textView4=findViewById(R.id.textView4)
//        textView4.text=message

        init()
        onclick()
    }

    private fun onclick() {
        continue_button.setOnClickListener {
            val OTP: String = otp.text.toString()
            val mobile_number=intent.getStringExtra("mobile")
            val Verify_OTP=intent.getStringExtra("verify_otp")

            continue_button.setBackgroundResource(R.drawable.shape_black_round_corner)
            continue_button.setTextColor(ContextCompat.getColor(this@EnterOtpActivity, R.color.white))

            if (OTP.isEmpty()){
                otp.error="Otp is Empty"
            }else{
                if (Verify_OTP != null){
                    val phoneAuthCredential = PhoneAuthProvider.getCredential(Verify_OTP, OTP)
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Verify OTP Successfully", Toast.LENGTH_SHORT).show()
                                // Push data to Firestore here
                                val db = FirebaseFirestore.getInstance()
                                val users: MutableMap<String, Any> = HashMap()
//                                users["Mobile Number"] = mobile_number?.toString() :
                                users?.put("Mobile Number", mobile_number.toString())
                                // Add a new document with a generated ID
//                                val currentUser = FirebaseAuth.getInstance().currentUser
                                FirebaseAuth.getInstance().currentUser?.let { it1 ->
                                    db.collection("users")

                                        .document(it1.uid) // Set the document ID as the user's UID
                                        .set(users)
                                        .addOnSuccessListener {
                                            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                                            startActivity(Intent(this@EnterOtpActivity, AgeActivity::class.java))
//                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                            startActivity(intent)
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(this, "Error adding document", Toast.LENGTH_SHORT).show()
                                        }
                                }
                            } else {
                                Toast.makeText(this, "Enter correct OTP", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
        back_button.setOnClickListener {
            startActivity(Intent(this@EnterOtpActivity, EnterNumberActivity::class.java))
        }
    }
    private fun init() {
        otp=findViewById(R.id.otp)
        continue_button=findViewById(R.id.continue_button)
        back_button=findViewById(R.id.back_button)
        textView4=findViewById(R.id.textView4)
    }
}