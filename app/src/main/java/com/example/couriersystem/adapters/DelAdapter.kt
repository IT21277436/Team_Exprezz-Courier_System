package com.example.couriersystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.couriersystem.R
import com.example.couriersystem.models.DeliveryModel

class DelAdapter (private val delList: ArrayList<DeliveryModel>) : RecyclerView.Adapter<DelAdapter.ViewHolder>() {

    private lateinit var dListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        dListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_delivery_list, parent, false)
        return ViewHolder(itemView, dListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentDel = delList[position]
        holder.tvPickup.text = currentDel.rPickup
        holder.tvAddress.text = currentDel.rAddress
        holder.tvProgLabel.text = currentDel.delProgLabel
        holder.progressBar.progress = currentDel.delProgress!!
    }

    override fun getItemCount(): Int {
        return delList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvPickup: TextView = itemView.findViewById(R.id.tvPickup)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val tvProgLabel: TextView = itemView.findViewById(R.id.tvProgLabel)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }
}