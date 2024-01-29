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
import com.google.firebase.firestore.FirebaseFirestore
import com.hmudevicedoctor.hmu.R

class NicknameActivity : AppCompatActivity() {

    private lateinit var nick_name: EditText
    private lateinit var continue_button: TextView
    private lateinit var back_button: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        nick_name=findViewById(R.id.nick_name)
        continue_button=findViewById(R.id.continue_button)
        back_button=findViewById(R.id.back_button)

        val nickName: String= nick_name.text.toString()

        continue_button.setOnClickListener {
//            aa?
            continue_button.setBackgroundResource(R.drawable.shape_black_round_corner)
            continue_button.setTextColor(ContextCompat.getColor(this@NicknameActivity, R.color.white))

            val db = FirebaseFirestore.getInstance()

            val userDocRef = db.collection("users")
                .document(FirebaseAuth.getInstance().currentUser!!.uid)

            userDocRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val user = documentSnapshot.data
                        user?.put("Nickname",nickName+"/Stormy.Fog")

                        userDocRef.set(user!!)
                            .addOnSuccessListener {
                                startActivity(Intent(this@NicknameActivity, NicknameActivity::class.java))
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this@NicknameActivity, "Check your internet connection....", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this@NicknameActivity, "User does not exist", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@NicknameActivity, "Error fetching user data", Toast.LENGTH_SHORT).show()
                }

            startActivity(Intent(this@NicknameActivity, AvtarActivity::class.java))
        }
        back_button.setOnClickListener {
            startActivity(Intent(this@NicknameActivity, FlotsBoat::class.java))
        }
    }
}