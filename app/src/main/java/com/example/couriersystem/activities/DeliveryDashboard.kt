package com.example.couriersystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couriersystem.R
import com.example.couriersystem.adapters.DelAdapter
import com.example.couriersystem.models.DeliveryModel
import com.google.firebase.database.*

class DeliveryDashboard : AppCompatActivity() {

    private lateinit var delRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var delList: ArrayList<DeliveryModel>

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_dashboard)

        delRecyclerView = findViewById(R.id.rvDelivery)
        delRecyclerView.layoutManager = LinearLayoutManager(this)
        delRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        delList = arrayListOf<DeliveryModel>()

        getDeliveryData()

        var btnDelivery = findViewById<Button>(R.id.btnAddDelivery)
        btnDelivery.setOnClickListener{
            val intent = Intent(this, DeliveryService::class.java)
            startActivity(intent)
        }

    }

    private fun getDeliveryData() {
        delRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Delivery")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                delList.clear()
                if(snapshot.exists()){
                    for(delSnap in snapshot.children){
                        val delData = delSnap.getValue(DeliveryModel::class.java)
                        delList.add(delData!!)
                    }
                    val dAdapter = DelAdapter(delList)
                    delRecyclerView.adapter = dAdapter

                    dAdapter.setOnItemClickListener(object : DelAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@DeliveryDashboard, DeliveryEdit::class.java)

                            intent.putExtra("delId", delList[position].delId)
                            intent.putExtra("delName", delList[position].rName)
                            intent.putExtra("delPickup", delList[position].rPickup)
                            intent.putExtra("delPkgWeight", delList[position].rPkgWeight)
                            intent.putExtra("delMobile", delList[position].rMobile)
                            intent.putExtra("delCategory", delList[position].rCategory)
                            intent.putExtra("delAddress", delList[position].rAddress)
                            intent.putExtra("delProgLabel", delList[position].delProgLabel)
                            intent.putExtra("delProgress", delList[position].delProgress)

                            startActivity(intent)
                        }

                    })

                    delRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}