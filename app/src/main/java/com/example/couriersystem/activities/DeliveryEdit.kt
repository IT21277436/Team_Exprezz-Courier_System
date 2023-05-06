package com.example.couriersystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.*
import com.example.couriersystem.R
import com.example.couriersystem.models.DeliveryModel
import com.google.firebase.database.FirebaseDatabase

class DeliveryEdit : AppCompatActivity() {

    private lateinit var etpickUp: Spinner
    private lateinit var etpkgWeight: EditText
    private lateinit var etCategory: Spinner
    private lateinit var etRName: EditText
    private lateinit var etRAddress: EditText
    private lateinit var etRMobile: EditText

    private lateinit var btUpdate: Button
    private lateinit var btDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_edit)

        etpickUp = findViewById(R.id.spPickup)
        etpkgWeight = findViewById(R.id.txtWeight)
        etCategory = findViewById(R.id.spCategory)
        etRName = findViewById(R.id.etRName)
        etRAddress = findViewById(R.id.etRAddress)
        etRMobile = findViewById(R.id.etRMobile)
        btUpdate = findViewById(R.id.btnUpdate)
        btDelete = findViewById(R.id.btnDelete)

        initView()
        setValuesToViews()

        btUpdate.setOnClickListener {
            val delId = intent.getStringExtra("delId").toString()
            val rPickup = etpickUp.selectedItem.toString()
            val rPkgWeight = etpkgWeight.text.toString()
            val rCategory = etCategory.selectedItem.toString()
            val rName = etRName.text.toString()
            val rAddress = etRAddress.text.toString()
            val rMobile = etRMobile.text.toString()
            val rProgLabel = intent.getStringExtra("delProgLabel").toString()
            val rProgress = intent.getIntExtra("delProgress", 0)

            updateDeliveryData(delId, rPkgWeight, rPickup, rCategory, rName, rAddress, rMobile, rProgLabel, rProgress)
        }

        btDelete.setOnClickListener {
            deleteData(
                intent.getStringExtra("delId").toString()
            )
        }
    }

    private fun deleteData(
        id: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Delivery").child(id)
        val dTask = dbRef.removeValue()

        dTask.addOnSuccessListener {
            Toast.makeText(this, "Delivery deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, DeliveryDashboard::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {

    }
    private fun setValuesToViews() {

        val pickupLocations = resources.getStringArray(R.array.pickup_locations).toMutableList()
        val delCategory = resources.getStringArray(R.array.delivery_category).toMutableList()

        etpickUp.setSelection(pickupLocations.indexOf(intent.getStringExtra("delPickup")));
        etCategory.setSelection(delCategory.indexOf(intent.getStringExtra("delCategory")));

        etpkgWeight.text = intent.getStringExtra("delPkgWeight")?.toEditable()
        etRName.text = intent.getStringExtra("delName")?.toEditable()
        etRAddress.text = intent.getStringExtra("delAddress")?.toEditable()
        etRMobile.text = intent.getStringExtra("delMobile")?.toEditable()

    }
    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private fun updateDeliveryData(
        id: String,
        weight: String,
        pickup: String,
        category: String,
        name: String,
        address: String,
        mobile: String,
        progresslabel: String,
        progress: Int
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Delivery").child(id)
        val delInfo = DeliveryModel(id, pickup, weight, category, name, address, mobile, progresslabel, progress)
        val dTask = dbRef.setValue(delInfo)

        dTask.addOnSuccessListener {
            Toast.makeText(this, "Delivery Edited Successfully", Toast.LENGTH_LONG).show()

            val intent = Intent(this, DeliveryDashboard::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Editing error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}