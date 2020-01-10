package com.example.fuel_log

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface fuelLog_DAOs {

    //Add new car detail
    @Insert
    fun save_car_data(car_data:car_Entity)

    // Read all car detail
    @Query ("select * from car_detail ")
    fun readallcars() : List<car_Entity>
}

@Dao
interface fuelLogDetails_DAOs {

    // Add new service detail
    @Insert
    fun save_service_data(carservice : car_fuel_detail)

    // Read all car service detail
    @Query ("select * from car_fuel_details ")
    fun readallcars_details(): List<car_fuel_detail>
}