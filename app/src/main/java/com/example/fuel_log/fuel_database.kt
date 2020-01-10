package com.example.fuel_log

import android.content.Context
import android.os.Environment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

var File_location : String= (Environment.getExternalStorageDirectory().absolutePath).toString()
@Database(entities = [car_Entity::class, car_fuel_detail::class], version = 1)
abstract class fuel_database : RoomDatabase() {
    abstract fun fuelLog_DAOs_cars() : fuelLog_DAOs
    abstract fun fuelLog_DAOs_carFuels() : fuelLogDetails_DAOs



    companion object {
        private var INSTANCE: fuel_database? = null

          fun getInstance(context: Context): fuel_database? {
            if (INSTANCE == null) {
                synchronized(fuel_database::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        fuel_database::class.java, "fuelDb.db")
                    .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}