package com.hmudevicedoctor.hmu.authenticationhmu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hmudevicedoctor.hmu.R

class FlotsBoat : AppCompatActivity() {
    private lateinit var girl:TextView
    private lateinit var guy:TextView
    private lateinit var non_binary:TextView
    private lateinit var other:TextView
    private lateinit var back_button: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flots_boat)

        init()
        onclick()
    }

    private fun onclick() {
        girl.setOnClickListener {
            girl.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            girl.setTextColor(ContextCompat.getColor(this@FlotsBoat, R.color.white))
            val str="Girl"
            intent(str)
        }
        guy.setOnClickListener {
            guy.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            guy.setTextColor(ContextCompat.getColor(this@FlotsBoat, R.color.white))
            val str="Guy"
            intent(str)
        }
        non_binary.setOnClickListener {
            non_binary.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            non_binary.setTextColor(ContextCompat.getColor(this@FlotsBoat, R.color.white))
            val str="Non binary"
            intent(str)
        }
        other.setOnClickListener {
            other.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            other.setTextColor(ContextCompat.getColor(this@FlotsBoat, R.color.white))
            startActivity(Intent(this@FlotsBoat, BoatOtherGender::class.java))
        }
        back_button.setOnClickListener {
            startActivity(Intent(this@FlotsBoat, GenderActivity::class.java))
        }
    }

    private fun init(){
        girl=findViewById(R.id.girl)
        guy=findViewById(R.id.guy)
        non_binary=findViewById(R.id.non_binary)
        other=findViewById(R.id.other)
        back_button=findViewById(R.id.back_button)
    }

    private fun intent(str: String) {
        val db = FirebaseFirestore.getInstance()

        val userDocRef = db.collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)

        userDocRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val user = documentSnapshot.data
                    user?.put("Boat Gender",str)

                    userDocRef.set(user!!)
                        .addOnSuccessListener {
                            startActivity(Intent(this@FlotsBoat, NicknameActivity::class.java))
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this@FlotsBoat, "Check your internet connection....", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this@FlotsBoat, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@FlotsBoat, "Error fetching user data", Toast.LENGTH_SHORT).show()
            }

    }
}