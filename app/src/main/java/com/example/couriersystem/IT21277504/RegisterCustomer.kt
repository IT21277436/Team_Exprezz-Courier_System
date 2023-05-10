package com.example.couriersystem.IT21277504

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.couriersystem.databinding.It21277504RegisterCustomerBinding
import com.example.madhuka.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterCustomer : AppCompatActivity() {
    private lateinit var binding :  It21277504RegisterCustomerBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = It21277504RegisterCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val name = binding.username.text.toString()
            val address = binding.address.text.toString()
            val mnumber = binding.rdMobile.text.toString()
            val password = binding.rdAddress.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(name,address, mnumber,password)
            database.child(name).setValue(User).addOnSuccessListener{

                binding.username.text.clear()
                binding.address.text.clear()
                binding.rdMobile.text.clear()
                binding.rdAddress.text.clear()

                Toast.makeText(this,"Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{

                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }
        }


    }
}