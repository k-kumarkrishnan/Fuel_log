package com.example.fuel_log

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.car_row.view.*

class car_list_adapter (val car_list : ArrayList<String>,  val context: Context) : RecyclerView.Adapter<car_list_adapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.car_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return car_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.car_name.text = car_list.get(position)
        //>>>>>>>>>>>>>>>>>  Recycler onClick Listener <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        holder?.car_name?.setOnClickListener()
        {
            //Saving last open car detail for next time direct open
            with (sharedpref.edit()) {
                putInt("PREFERED_CAR", position)
                apply()
            }
        }


    }

    class ViewHolder (view : View ) : RecyclerView.ViewHolder(view){
        val car_name = view.carName

    }

}