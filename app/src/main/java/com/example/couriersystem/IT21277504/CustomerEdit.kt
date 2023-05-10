package com.example.couriersystem.IT21277504

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.couriersystem.R
import com.example.couriersystem.databinding.It21277504CustomerEditBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerEdit : AppCompatActivity() {

    private lateinit var binding: It21277504CustomerEditBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = It21277504CustomerEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCusEdit.setOnClickListener {

            var name = binding.username.text.toString()
            var address = binding.address.text.toString()
            var mnumber = binding.rdMobile.text.toString()
            var password = binding.rdAddress.text.toString()

            updateData(name,address,mnumber,password)

        }
    }
    private fun updateData(name :String,address:String,mnumber:String,password:String){

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
            "name" to name,
            "address" to address,
            "mnumber" to mnumber,
            "password" to password,
        )

        database.child(name).updateChildren(user).addOnSuccessListener {

            binding.username.text.clear()
            binding.address.text.clear()
            binding.rdMobile.text.clear()
            binding.rdAddress.text.clear()
            Toast.makeText(this,"Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{

            Toast.makeText(this," Update Unsuccessful", Toast.LENGTH_SHORT).show()

        }
    }
}