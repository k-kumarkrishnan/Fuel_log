package com.example.fuel_log

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : App_theme() {

    lateinit var textView1 : TextView
    lateinit var fuelthread : mythreads
    var arraylist = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val Request_code = 1
        sharedpref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE )
        //Writing in sharedPreferences
        with (sharedpref.edit()) {
            putInt("PREFERED_CAR", 1)
            commit()
        }



        textView1 = findViewById<TextView>(R.id.textView)
        val car_additionIntent = Intent(this, CarAddition::class.java)


        car_database = fuel_database.getInstance(this@MainActivity)!!
        fuelthread = mythreads("main_thread")
        fuelthread.start()
        var temp1: String
        var temp2: String = " ."


        //**************************************************************************************
        //****************   Reading data from database ****************************************
        val mythread = Thread {
            dataread = car_database?.fuelLog_DAOs_cars()?.readallcars()


            dataread.forEach {
                    arraylist.add(it.carNameEnt)
                    temp1 = temp2.plus(it.carNameEnt)
                        .plus(" \t").plus(it.carMakeEnt)
                        .plus(" \t").plus(it.carModelEnt)
                        .plus(" \t").plus(it.carCurrencyEnt)
                        .plus(" \t").plus(it.carFuelCapacityEnt)
                        .plus(" \t").plus(it.carUnitsEnt)
                        .plus(" \t").plus(it.carYearEnt)
                        .plus(" \t").plus(it.pricePerDistanceEnt)
                        .plus(" \t").plus(it.distancePerVolumeEnt)
                        .plus(" \t").plus(it.distanceUnitsEnt)
                        .plus(" \t").plus(it.carUnitsEnt)
                        .plus(" \t").plus(it.carfueltypeEnt)
                        .plus("\t\n")
                    temp2 = temp1
                }
                textView1.text = temp2
        }
        mythread.start()



        //*******************************************************************************
        // *********  New Car addition  **************************************************
        val car_add_view : CardView = findViewById(R.id.add_car_view)
        car_add_view.setOnClickListener(){
            startActivityForResult(car_additionIntent,Request_code)
            Toast.makeText(this, "Card view Clicked", Toast.LENGTH_SHORT).show()
        }

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //>>>>>>>>>>  backup Button  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        val backup_butt = findViewById<CardView>(R.id.backupData_cardview)
        backup_butt.setOnClickListener(){
            val defaultValue = sharedpref.getInt("PREFERED_CAR", 0)
            textView1.text = defaultValue.toString()
        }




        //************ Car listing starts with Recycler view  *******************************
        val car_recycler : RecyclerView = findViewById(R.id.recyclerView)
        car_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager?
        car_recycler.adapter = car_list_adapter(arraylist, this)
    }


    //**********************************************************************************************
    //*****************  Return from other activity  ***********************************************
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1)
        {
           Toast.makeText(this, "returned from CarAddition", Toast.LENGTH_SHORT).show()
        }
    }
}
