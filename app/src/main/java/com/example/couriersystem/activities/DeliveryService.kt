package com.example.couriersystem.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.couriersystem.models.DeliveryModel
import com.example.couriersystem.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeliveryService : AppCompatActivity() {

    private lateinit var etpickUp: Spinner
    private lateinit var etpkgWeight: EditText
    private lateinit var etCategory: Spinner
    private lateinit var etRName: EditText
    private lateinit var etRAddress: EditText
    private lateinit var etRMobile: EditText
    private lateinit var btSubmit: Button

    private lateinit var btReset: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deliveryservice)

        etpickUp = findViewById(R.id.spPickup)
        etpkgWeight = findViewById(R.id.txtWeight)
        etCategory = findViewById(R.id.spCategory)
        etRName = findViewById(R.id.etRName)
        etRAddress = findViewById(R.id.etRAddress)
        etRMobile = findViewById(R.id.etRMobile)
        btSubmit = findViewById(R.id.btnUpdate)
        btReset = findViewById(R.id.btnDelete)

        dbRef = FirebaseDatabase.getInstance().getReference("Delivery")

        btSubmit.setOnClickListener {
            saveDeliveryData()
        }

        btReset.setOnClickListener {
            clearAll()
        }
    }

    private fun clearAll() {
        etpickUp.setSelection(0)
        etpkgWeight.text.clear()
        etCategory.setSelection(0)
        etRName.text.clear()
        etRAddress.text.clear()
        etRMobile.text.clear()
    }

    private fun saveDeliveryData() {

        //getting values
        val rPickup = etpickUp.selectedItem.toString()
        val rPkgWeight = etpkgWeight.text.toString()
        val rCategory = etCategory.selectedItem.toString()
        val rName = etRName.text.toString()
        val rAddress = etRAddress.text.toString()
        val rMobile = etRMobile.text.toString()
        val delProgLabel = "Waiting for package"
        val delProgress = 10

        var errorMessage = ""

        if (rPickup == "Select Pickup Location") {
            errorMessage = "Please select a pickup location!"
            etpickUp.requestFocus()
        }
        if (rPkgWeight.isEmpty()) {
            errorMessage = "Please enter the weight of the package!"
            etpkgWeight.requestFocus()
        }
        if (rCategory == "Select Category") {
            errorMessage = "Please select a category!"
            etCategory.requestFocus()
        }
        if (rName.isEmpty()) {
            errorMessage = "Please enter the receiver's name!"
            etRName.requestFocus()
        }
        if (rAddress.isEmpty()) {
            errorMessage = "Please enter the receiver's address!"
            etRAddress.requestFocus()
        }
        if (rMobile.isEmpty()) {
            errorMessage = "Please enter the receiver's mobile!"
            etRMobile.requestFocus()
        }
        else if(rMobile.length != 10)
        {
            errorMessage = "Mobile number should be valid"
            etRMobile.requestFocus()
        }

        if (errorMessage.isNotEmpty()) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        } else {
            val delId = dbRef.push().key!!
            val delivery =
                DeliveryModel(delId, rPickup, rPkgWeight, rCategory, rName, rAddress, rMobile, delProgLabel, delProgress)

            dbRef.child(delId).setValue(delivery)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                    clearAll()
                }
                .addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}