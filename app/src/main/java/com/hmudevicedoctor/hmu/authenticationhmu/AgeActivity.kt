package com.hmudevicedoctor.hmu.authenticationhmu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hmudevicedoctor.hmu.R

class AgeActivity : AppCompatActivity() {

    private lateinit var age: TextView
    private lateinit var continue_button: TextView
    private lateinit var back_button: ImageView
    private lateinit var number_picker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)

        init()
        onclick()


        // Set the range for the number picker
        number_picker.minValue = 18
        number_picker.maxValue = 70

        // Set a default value
        number_picker.value = 1

        // Set the listener to update the TextView when the number is changed
        number_picker.setOnValueChangedListener { _, _, newVal ->
            age.text = "$newVal"
        }
    }

    private fun init() {
        age = findViewById(R.id.age)
        continue_button = findViewById(R.id.continue_button)
        back_button = findViewById(R.id.back_button)
        number_picker = findViewById(R.id.number_picker)
    }

    private fun onclick() {
        continue_button.setOnClickListener {
            val Age: String = age.text.toString()

            continue_button.setBackgroundResource(R.drawable.shape_white_black_stork_round_corner)
            continue_button.setTextColor(ContextCompat.getColor(this@AgeActivity, R.color.black))

            val db = FirebaseFirestore.getInstance()

            val userDocRef = db.collection("users")
                .document(FirebaseAuth.getInstance().currentUser!!.uid)

            userDocRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val user = documentSnapshot.data
                        user?.put("Age", Age)

                        userDocRef.set(user!!)
                            .addOnSuccessListener {
                                startActivity(Intent(this@AgeActivity, GenderActivity::class.java))
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this@AgeActivity, "Check your internet connection....", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this@AgeActivity, "User does not exist", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@AgeActivity, "Error fetching user data", Toast.LENGTH_SHORT).show()
                }
        }
        
        back_button.setOnClickListener {
            startActivity(Intent(this@AgeActivity, EnterOtpActivity::class.java))
        }
    }
}