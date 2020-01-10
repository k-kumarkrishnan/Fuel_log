package com.example.fuel_log

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.*


class CarAddition : App_theme() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_addition)

        val saveButton = findViewById<Button>(R.id.carNameSaveButton)
        //************************************************************************************
        val CancelButton = findViewById<Button>(R.id.cancelButton)

        val fuel_array_units_spinner = findViewById<Spinner>(R.id.fuelUnitsText)
        val fuel_currecncy_spinner = findViewById<Spinner>(R.id.Currency_toSaveText)
        val distance_spinner = findViewById<Spinner>(R.id.distance_toSaveText)
        val fuel_type_spinner = findViewById<Spinner>(R.id.fuel_typeText)
        var car_unit_selected: String = "Rupees"
        var car_currency_selected : String = "Liters"
        var distance_selected : String = "kilometers"
        var fuel_type_selected : String = "Petrol/gas"
        val car_units = resources.getStringArray(R.array.fuel_units)
        val car_currency = resources.getStringArray(R.array.fuel_currency)
        val distance_array_data = resources.getStringArray(R.array.distance_array)
        val fuel_type_array = resources.getStringArray(R.array.fuel_type_array)

        //*****************************************************************************************
        //****************  Spinner for Units *****************************************************
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, car_units)
        fuel_array_units_spinner.adapter = adapter

        fuel_array_units_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@CarAddition, "Nothing selected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                car_unit_selected = car_units[position].toString()
            }
        }


        //*****************************************************************************************
        //****************  Spinner for Currency *****************************************************
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, car_currency)
        fuel_currecncy_spinner.adapter = adapter2

        fuel_currecncy_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@CarAddition, "No Currency selected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                car_currency_selected = car_currency[position].toString()
            }
        }


        //*****************************************************************************************
        //****************  Spinner for Distance *****************************************************
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, distance_array_data)
        distance_spinner.adapter = adapter3

        distance_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@CarAddition, "No Distance selected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                distance_selected = distance_array_data[position].toString()
            }
        }



        //*****************************************************************************************
        //****************  Spinner for Fuel type *****************************************************
        val adapter4 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fuel_type_array)
        fuel_type_spinner.adapter = adapter4

        fuel_type_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@CarAddition, "No fuel Type selected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                fuel_type_selected = fuel_type_array[position].toString()
            }
        }


        //******************************************************************************************
        //*******************  Save Button  ********************************************************
        saveButton.setOnClickListener()
        {
            cars_add.carNameEnt = (findViewById<EditText>(R.id.carNameSave)).getText().toString()

            cars_add.carMakeEnt = (findViewById<EditText>(R.id.carMakeText)).getText().toString()

            cars_add.carModelEnt = (findViewById<EditText>(R.id.carModelText)).getText().toString()

            var temp4 = (findViewById<EditText>(R.id.carCapacityText)).text.toString()
            try {
                val num = temp4.toInt()
                Log.i("KKK@", "$num is a number")
                cars_add.carFuelCapacityEnt = temp4.toInt()
            } catch (e: NumberFormatException) {
                Log.i("KKK@", "$temp4 is not a number")
                cars_add.carFuelCapacityEnt = 0
                Toast.makeText(this@CarAddition, "Car Fuel Capacity considered as 0", Toast.LENGTH_SHORT).show()
            }

            temp4 = (findViewById<EditText>(R.id.carYearText)).text.toString()
            try {
                val num = temp4.toInt()
                Log.i("KKK@", "$num is a number")
                cars_add.carYearEnt = temp4.toInt()
            } catch (e: NumberFormatException) {
                Log.i("KKK@", "$temp4 is not a number")
                cars_add.carYearEnt = 1990
                Toast.makeText(this@CarAddition, "Car year considered as 1990", Toast.LENGTH_SHORT).show()
            }

            cars_add.carCurrencyEnt = car_currency_selected
            cars_add.carUnitsEnt = car_unit_selected
            cars_add.distanceUnitsEnt = distance_selected
            cars_add.carfueltypeEnt = fuel_type_selected

            if(cars_add.carCurrencyEnt == "Rupee" && cars_add.carUnitsEnt == "Liters"
                && cars_add.distanceUnitsEnt == "kilometers")
            {
                cars_add.distancePerVolumeEnt = "kms/L"
                cars_add.pricePerDistanceEnt = "Rs/km"
            }
            if(cars_add.carCurrencyEnt == "US Dollar" && cars_add.carUnitsEnt == "US Gallons"
                && cars_add.distanceUnitsEnt == "miles")
            {
                cars_add.distancePerVolumeEnt = "mpg (US)"
                cars_add.pricePerDistanceEnt = "$/mile (US)"
            }

            if(cars_add.carCurrencyEnt == "UK Pounds" && cars_add.carUnitsEnt == "UK Gallons"
                && cars_add.distanceUnitsEnt == "miles")
            {
                cars_add.distancePerVolumeEnt = "mpg (UK)"
                cars_add.pricePerDistanceEnt = "£/mile (UK)"
            }



            val db1 = fuel_database.getInstance(this@CarAddition)

            Thread{
                db1?.fuelLog_DAOs_cars()?.save_car_data(cars_add)}.start()

            val backtoMainActivity_Intent = Intent()
            setResult(15, backtoMainActivity_Intent)
            finish()
        }


        CancelButton.setOnClickListener()
        {
            val backtoMainActivity_Intent = Intent()
            setResult(15, backtoMainActivity_Intent)
            finish()
        }
    }
}




