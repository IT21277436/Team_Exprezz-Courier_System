package com.example.couriersystem.IT21277504

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.couriersystem.R
import com.example.couriersystem.activities_IT21277436.ServicePage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.it21277504_main)

        val btnConGuest = findViewById<Button>(R.id.btConGuest)
        btnConGuest.setOnClickListener {
            val intent = Intent(this, ServicePage::class.java)
            startActivity(intent)
        }

        val btnGetStarted = findViewById<Button>(R.id.btGetStarted)
        btnGetStarted.setOnClickListener {
            val intent = Intent(this, CustomerLogin::class.java)
            startActivity(intent)
        }
    }
}
