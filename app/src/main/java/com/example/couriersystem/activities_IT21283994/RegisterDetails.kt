package com.example.couriersystem.activities_IT21283994

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.couriersystem.R
import com.example.driverreg.models.DriverModel
import com.google.firebase.database.FirebaseDatabase

class RegisterDetails : AppCompatActivity() {

    //private lateinit var tvDriverId: TextView
    private lateinit var tvName: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvLicence: TextView
    private lateinit var tvEmail: TextView

    // private lateinit var etPassword: TextView
    private lateinit var btDelete: Button
    private lateinit var btnEdit: Button
    private lateinit var cancelButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.it21283994_register_details)

        tvName = findViewById(R.id.tvName)
        tvAddress = findViewById(R.id.tvAddress)
        tvPhone = findViewById(R.id.tvPhone)
        tvLicence = findViewById(R.id.tvLicence)
        tvEmail = findViewById(R.id.tvEmail)



        initView()
        setValuesToView()

        btnEdit = findViewById(R.id.btnUpdate)
        btnEdit.setOnClickListener{
             openUpdateDialog(
                intent.getStringExtra("driverId").toString(),
                intent.getStringExtra("Name").toString()
               )
            }

        btDelete = findViewById(R.id.btnDelete)
           btDelete.setOnClickListener{
                deleteRecord(
                    intent.getStringExtra("driverId").toString()
                )
            }

        cancelButton = findViewById(R.id.cancelButton)

        cancelButton.setOnClickListener{
            finish()
        }
    }

    private fun initView() {}
    private fun setValuesToView() {
        //tvDriverId.text = intent.getStringExtra("driverId")
        tvName.text = intent.getStringExtra("Name")
        tvAddress.text = intent.getStringExtra("Address")
        tvPhone.text = intent.getStringExtra("Phone")
        tvLicence.text = intent.getStringExtra("Licence")
        tvEmail.text = intent.getStringExtra("Email")

    }

    private fun openUpdateDialog(

        driverId : String,
        Name :String

    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.it21283994_edit_register,null)

        mDialog.setView(mDialogView)

        val etName = mDialogView.findViewById<EditText>(R.id.editName)
        val etAddress = mDialogView.findViewById<EditText>(R.id.editAddress)
        val etPhone = mDialogView.findViewById<EditText>(R.id.editPhone)
        val etLicence = mDialogView.findViewById<EditText>(R.id.editLicence)
        val etEmail = mDialogView.findViewById<EditText>(R.id.editEmail)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btUpdateData)

        etName.setText(intent.getStringExtra("Name").toString())
        etAddress.setText(intent.getStringExtra("Address").toString())
        etPhone.setText(intent.getStringExtra("Phone").toString())
        etLicence.setText(intent.getStringExtra("Licence").toString())
        etEmail.setText(intent.getStringExtra("Email").toString())

        mDialog.setTitle("Updating.. $Name Record ")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateDriverData(
                driverId,
                etName.text.toString(),
                etAddress.text.toString(),
                etPhone.text.toString(),
                etLicence.text.toString(),
                etEmail.text.toString()

            )
            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvName.text = etName.text.toString()
            tvAddress.text = etAddress.text.toString()
            tvPhone.text = etPhone.text.toString()
            tvLicence.text = etLicence.text.toString()
            tvEmail.text = etEmail.text.toString()

            alertDialog.dismiss()
        }


    }
    private fun deleteRecord(
        driverId: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Drivers").child(driverId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Driver data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
    //private fun updateDriverData(driverId: String, toString: String, toString1: String, toString2: String, toString3: String, toString4: String) {

    private fun updateDriverData(
        driverId: String,
        name: String,
        address: String,
        phone: String,
        licence: String,
        email: String,
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Drivers").child(driverId)
        val driverInfo = DriverModel(driverId, name, address, phone, licence, email)
        dbRef.setValue(driverInfo)
    }
}