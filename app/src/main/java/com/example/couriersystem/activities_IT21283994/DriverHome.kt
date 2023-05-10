package com.example.couriersystem.activities_IT21283994

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.couriersystem.R
import com.example.couriersystem.activities_IT21283994.FetchingActivity
import com.example.couriersystem.activities_IT21283994.RegisterDriver
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DriverHome : AppCompatActivity() {

    private lateinit var btnInsertData : Button
    private lateinit var btnFetchData : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.it21283994_driver_home)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsertData = findViewById(R.id.btnInsertData)
        btnFetchData = findViewById(R.id.btnFetchButton)
        //  btnFetchData = findViewById(R.id.btnFitchButton)

        btnInsertData.setOnClickListener{
            val intent = Intent(this, RegisterDriver::class.java)
            startActivity(intent)
        }
        btnFetchData.setOnClickListener{
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }
    }
}