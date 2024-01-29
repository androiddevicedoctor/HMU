package com.hmudevicedoctor.hmu.authenticationhmu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hmudevicedoctor.hmu.R
import com.hmudevicedoctor.hmu.bouttomnavbar.NavBar

class GoPremimum : AppCompatActivity() {
    private lateinit var done:TextView
    private lateinit var month:TextView
    private lateinit var week:TextView
    private lateinit var restore_pur:TextView
    private lateinit var terms_condi:TextView
    private lateinit var continue_button:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_premimum)

        init()
        onClick()

    }

    private fun onClick() {
//        done.setOnClickListener {
//            startActivity(Intent(this@GoPremimum, NavBar::class.java))
//        }
//        month.setOnClickListener {
//            startActivity(Intent(this@GoPremimum, NavBar::class.java))
//        }
//        week.setOnClickListener {
//            startActivity(Intent(this@GoPremimum, NavBar::class.java))
//        }
//        restore_pur.setOnClickListener {
//            startActivity(Intent(this@GoPremimum, NavBar::class.java))
//        }
//        terms_condi.setOnClickListener {
//            startActivity(Intent(this@GoPremimum, NavBar::class.java))
//        }
        continue_button.setOnClickListener {
            continue_button.setBackgroundResource(R.drawable.shape_white_black_stork_round_corner)
            continue_button.setTextColor(ContextCompat.getColor(this@GoPremimum, R.color.black))
            startActivity(Intent(this@GoPremimum, NavBar::class.java))
        }

    }

    private fun init(){
        done=findViewById(R.id.done)
        month=findViewById(R.id.month)
        week=findViewById(R.id.week)
        restore_pur=findViewById(R.id.restore_pur)
        terms_condi=findViewById(R.id.terms_condi)
        continue_button=findViewById(R.id.continue_button)
    }
}