package com.example.couriersystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.couriersystem.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnDashboard = findViewById<Button>(R.id.button)
        btnDashboard.setOnClickListener{
            val intent = Intent(this, DeliveryDashboard::class.java)
            startActivity(intent)
        }

    }
}