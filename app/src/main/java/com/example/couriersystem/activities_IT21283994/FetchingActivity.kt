package com.example.couriersystem.activities_IT21283994

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couriersystem.R
import com.example.driverreg.adapters.DriversAdapter
import com.example.driverreg.models.DriverModel
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var driverRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var driverList: ArrayList<DriverModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.it21283994_fetching)

        driverRecyclerView = findViewById(R.id.rvDriver)
        driverRecyclerView.layoutManager = LinearLayoutManager(this)
        driverRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        driverList = arrayListOf<DriverModel>()

        getDriverData()

        cancelButton = findViewById(R.id.cancelButton)

        cancelButton.setOnClickListener{
            finish()
        }
    }

    private fun getDriverData() {

        driverRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Drivers")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                driverList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(DriverModel::class.java)
                        driverList.add(empData!!)
                    }
                    val mAdapter = DriversAdapter(driverList)
                    driverRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : DriversAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingActivity, RegisterDetails::class.java)

                            //put extras
                            intent.putExtra("driverId", driverList[position].driverId)
                            intent.putExtra("Name", driverList[position].name)
                            intent.putExtra("Address", driverList[position].address)
                            intent.putExtra("Phone", driverList[position].phone)
                            intent.putExtra("Licence", driverList[position].licence)
                            intent.putExtra("Email", driverList[position].email)
                            intent.putExtra("Password", driverList[position].password)
                            startActivity(intent)
                        }

                    })

                    driverRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}