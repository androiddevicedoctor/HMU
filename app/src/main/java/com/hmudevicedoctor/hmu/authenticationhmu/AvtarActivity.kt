package com.hmudevicedoctor.hmu.authenticationhmu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hmudevicedoctor.hmu.R

class AvtarActivity : AppCompatActivity() {

    private lateinit var start_chatting: TextView
    private lateinit var back_button: ImageView
    private lateinit var avtar_image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avtar)

        init()
        onclick()

    }

    private fun onclick() {
        start_chatting.setOnClickListener {
            start_chatting.setBackgroundResource(R.drawable.shape_white_black_stork_round_corner)
            start_chatting.setTextColor(ContextCompat.getColor(this@AvtarActivity, R.color.black))
            startActivity(Intent(this@AvtarActivity, GoPremimum::class.java))
        }
        back_button.setOnClickListener {
            startActivity(Intent(this@AvtarActivity, NicknameActivity::class.java))
        }
    }

    private fun init() {
        start_chatting = findViewById(R.id.start_chatting)
        back_button = findViewById(R.id.back_button)
        avtar_image = findViewById(R.id.avtar_image)
    }
}