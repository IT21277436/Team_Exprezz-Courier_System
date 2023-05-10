package com.example.couriersystem.IT21277504

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.couriersystem.R
import com.example.couriersystem.databinding.It21277504CustomerDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerDelete : AppCompatActivity() {

    private lateinit var binding : It21277504CustomerDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = It21277504CustomerDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button11.setOnClickListener {

            var name = binding.username.text.toString()
            if(name.isNotEmpty()) {
                deleteData(name)

            }else
                Toast.makeText(this,"Please enter the username", Toast.LENGTH_SHORT).show()

        }
    }

    private fun deleteData(name:String){

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(name).removeValue().addOnSuccessListener {

            binding.username.text.clear()
            Toast.makeText(this,"Succesfully Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{

            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()



        }
    }
}