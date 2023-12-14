package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ViewCard : AppCompatActivity() {
    private lateinit var tvFront: TextView
    private lateinit var tvBack : TextView
    private lateinit var tvTitle: TextView
    private lateinit var btnFlip: Button
    private lateinit var btnHome: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_card)

        init_view()
        setValuesToView()

        btnFlip.setOnClickListener{
            flipCard()
        }

        btnHome.setOnClickListener {
            val intent = Intent(this,PublicCards::class.java)
            startActivity(intent)
        }
    }
    private fun init_view(){
        tvFront = findViewById(R.id.tvFront)
        tvBack = findViewById(R.id.tvBack)
        tvTitle = findViewById(R.id.txtCardTitle)
        btnFlip = findViewById(R.id.btnFlip)
        btnHome = findViewById(R.id.btnHome)
    }
    private fun setValuesToView(){
        tvFront.text = intent.getStringExtra("front")
        tvBack.text = intent.getStringExtra("back")
        tvTitle.text = intent.getStringExtra("title")
    }

    private fun flipCard(){
        if (tvFront.visibility == View.VISIBLE) {
            tvFront.visibility = View.GONE
            tvBack.visibility = View.VISIBLE
        } else {
            tvFront.visibility = View.VISIBLE
            tvBack.visibility = View.GONE
        }
    }
}