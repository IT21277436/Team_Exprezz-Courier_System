package com.example.couriersystem.activities_IT21283994

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.couriersystem.R
import com.example.driverreg.models.DriverModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterDriver : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPhone: EditText
    private lateinit var etLicence : EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnInsertData: Button
    private lateinit var cancelButton: Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.it212283994_register)

        etName = findViewById(R.id.etName)
        etAddress = findViewById(R.id.etAddress)
        etPhone = findViewById(R.id.etPhone)
        etLicence = findViewById(R.id.etLicence)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnInsertData = findViewById(R.id.btnInsertData)
        cancelButton = findViewById(R.id.cancelButton)

        dbRef = FirebaseDatabase.getInstance().getReference("Drivers")

        btnInsertData.setOnClickListener{
            saveDriverDetails()
        }

        cancelButton.setOnClickListener{
            finish()
        }
    }


    private fun saveDriverDetails(){

        //getting values
        val name = etName.text.toString()
        val address = etAddress.text.toString()
        val phone = etPhone.text.toString()
        val licence = etLicence.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        var errorMessage = ""

        if (name.isEmpty()){
            errorMessage = "Please enter Name"
            etName.requestFocus()
        }
        if (address.isEmpty()){
            errorMessage = "Please enter Address"
            etAddress.requestFocus()
        }
        if (phone.isEmpty()){
            errorMessage = "Please enter Phone number"
            etPhone.requestFocus()
        }
        if (licence.isEmpty()){
            errorMessage = "Please enter Licence number"
            etLicence.requestFocus()
        }
        if (email.isEmpty()){
            errorMessage = "Please enter email"
            etEmail.requestFocus()
        }
        if (password.isEmpty()){
            errorMessage = "Please enter a password"
            etPassword.requestFocus()
        }

        if (errorMessage.isNotEmpty()) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        } else {

            val driverId = dbRef.push().key!!

            val driver = DriverModel(driverId, name, address, phone, licence, email, password)

            dbRef.child(driverId).setValue(driver)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()

                    etName.text.clear()
                    etAddress.text.clear()
                    etPhone.text.clear()
                    etLicence.text.clear()
                    etEmail.text.clear()
                    etPassword.text.clear()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}