package com.example.couriersystem.IT21277504

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.couriersystem.R
import com.example.couriersystem.databinding.It21277504CustomerProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerProfile : AppCompatActivity() {
    private lateinit var binding: It21277504CustomerProfileBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = It21277504CustomerProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thirdActButton = findViewById<Button>(R.id.btUpdate)
        thirdActButton.setOnClickListener {
            val intent = Intent(this, CustomerEdit::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            var name : String = binding.username.text.toString()


            if(name.isNotEmpty()){

                readData(name)

            }else{

                Toast.makeText(this,"Please enter correct username and password", Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun readData(name : String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(name).get().addOnSuccessListener {

            if(it.exists()){

                val address = it.child("address").value
                val mnumber = it.child("mnumber").value
                Toast.makeText(this,"Successfully Read", Toast.LENGTH_SHORT).show()
                binding.username.text.clear()
                binding.address.text.clear()
                binding.rdAddress.setText(address.toString())
                binding.rdMobile.setText(mnumber.toString())


            }else{
                Toast.makeText(this,"Invalid User", Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener{

            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()

        }



    }



}

