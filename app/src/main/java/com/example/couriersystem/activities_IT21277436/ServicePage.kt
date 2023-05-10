package com.example.couriersystem.activities_IT21277436

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.couriersystem.IT21277504.CustomerLogin
import com.example.couriersystem.R
import com.example.couriersystem.activities_IT21283994.DriverHome

class ServicePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.it21277436_service_page)

        var btnDashboard = findViewById<Button>(R.id.btDelivery)
        var btnRider = findViewById<Button>(R.id.btRider)
        var btnVehicle = findViewById<Button>(R.id.btGetStarted)

        btnDashboard.setOnClickListener{
            val intent = Intent(this, DeliveryDashboard::class.java)
            startActivity(intent)
        }

        btnRider.setOnClickListener{
            val intent = Intent(this, DriverHome::class.java)
            startActivity(intent)
        }

        btnVehicle.setOnClickListener{
            val intent = Intent(this, CustomerLogin::class.java)
            startActivity(intent)
        }
    }
}