package com.example.couriersystem.IT21277504

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.couriersystem.R

class CustomerLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.it21277504_customer_login)

        val fourthActButton = findViewById<Button>(R.id.btSign)
        fourthActButton.setOnClickListener {
            val intent = Intent(this, RegisterCustomer::class.java)
            startActivity(intent)
        }
        val firstActButton = findViewById<Button>(R.id.button)
        firstActButton.setOnClickListener {
            val intent = Intent(this, CustomerProfile::class.java)
            startActivity(intent)
        }
        val twentyActButton = findViewById<Button>(R.id.btDel)
        twentyActButton.setOnClickListener {
            val intent = Intent(this, CustomerDelete::class.java)
            startActivity(intent)
        }

    }
}