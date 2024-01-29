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

class BoatOtherGender : AppCompatActivity() {

    private lateinit var Transgender: TextView
    private lateinit var Transsexual: TextView
    private lateinit var Gender_queer: TextView
    private lateinit var Gender_fluid: TextView
    private lateinit var Gender_variant: TextView
    private lateinit var Crossdresser: TextView
    private lateinit var back_button: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_gender)

        init()
        onclick()
    }
    private fun onclick() {
        Transgender.setOnClickListener {
            Transgender.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            Transgender.setTextColor(ContextCompat.getColor(this@BoatOtherGender, R.color.white))
            val str="Transgender"
            intent(str)
        }
        Transsexual.setOnClickListener {
            Transsexual.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            Transsexual.setTextColor(ContextCompat.getColor(this@BoatOtherGender, R.color.white))
            val str="Transsexual"
            intent(str)
        }
        Gender_queer.setOnClickListener {
            Gender_queer.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            Gender_queer.setTextColor(ContextCompat.getColor(this@BoatOtherGender, R.color.white))
            val str="Gender queer"
            intent(str)
        }
        Gender_fluid.setOnClickListener {
            Gender_fluid.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            Gender_fluid.setTextColor(ContextCompat.getColor(this@BoatOtherGender, R.color.white))
            val str="Gender fluid"
            intent(str)
        }
        Gender_variant.setOnClickListener {
            Gender_variant.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            Gender_variant.setTextColor(ContextCompat.getColor(this@BoatOtherGender, R.color.white))
            val str="Gender variant"
            intent(str)
        }
        Crossdresser.setOnClickListener {
            Crossdresser.setBackgroundResource(R.drawable.shape_black_1234corrner_round)
            Crossdresser.setTextColor(ContextCompat.getColor(this@BoatOtherGender, R.color.white))
            val str="Crossdresser"
            intent(str)
        }
        back_button.setOnClickListener {
            startActivity(Intent(this@BoatOtherGender, GenderActivity::class.java))
        }
    }

    private fun init(){
        Transgender=findViewById(R.id.Transgender)
        Transsexual=findViewById(R.id.Transsexual)
        Gender_queer=findViewById(R.id.Gender_queer)
        Gender_fluid=findViewById(R.id.Gender_fluid)
        Gender_variant=findViewById(R.id.Gender_variant)
        Crossdresser=findViewById(R.id.Crossdresser)
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
                            startActivity(Intent(this@BoatOtherGender, NicknameActivity::class.java))
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this@BoatOtherGender, "Check your internet connection....", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this@BoatOtherGender, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@BoatOtherGender, "Error fetching user data", Toast.LENGTH_SHORT).show()
            }
    }
}