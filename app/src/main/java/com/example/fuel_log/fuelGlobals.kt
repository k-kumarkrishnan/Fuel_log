package com.example.fuel_log

import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import java.sql.Date

var cars_add = car_Entity()
var cars_read = car_Entity()

//lateinit var car_database : RoomDatabase.Builder()
lateinit var car_database : fuel_database
lateinit var dataread : List<car_Entity>

//shared preference for app data to save for
val PREF_NAME = "fuel_log"
lateinit var sharedpref : SharedPreferences