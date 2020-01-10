package com.example.fuel_log

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity (tableName = "Car_Detail")
 class car_Entity {
    @PrimaryKey
    var carNameEnt : String = "."

    @ColumnInfo
    var carMakeEnt : String = "."

    @ColumnInfo
    var carModelEnt : String = "."

    @ColumnInfo
    var carFuelCapacityEnt : Int = 17

    @ColumnInfo
    var carYearEnt : Int = 2020

    @ColumnInfo
    var carUnitsEnt : String = "Liters"

    @ColumnInfo
    var carCurrencyEnt : String = "Rupees"

    @ColumnInfo
    var distanceUnitsEnt : String = "kilometers"

    @ColumnInfo
    var carfueltypeEnt : String = "Petrol/gas"

    @ColumnInfo
    var distancePerVolumeEnt : String = "kms/L"

    @ColumnInfo
    var pricePerDistanceEnt : String = "Rs/km"

    @ColumnInfo
    var CarAdditional1Ent : String = "."

    @ColumnInfo
    var CarAdditional2Ent : String = "."

    @ColumnInfo
    var CarAdditional3Ent : Int = 12345

    @ColumnInfo
    var CarAdditional4Ent : Double = 45678.0
}


@Entity (tableName = "Car_fuel_details")
class car_fuel_detail {
    @PrimaryKey
    var TypeID : Int = 1

    @ColumnInfo
    var carData : String = "My Car"

    @ColumnInfo
    var typeLog : String = "fuel"

    @ColumnInfo
    var carOdometerEnt: Int = 100000

    @ColumnInfo
    var carFuelCostPerUnitEnt: Double = 0.02

    @ColumnInfo
    var carFuelFilledEnt: Double = 0.02

    @ColumnInfo
    var tankFullEnt: String = "yes"

    @ColumnInfo
    var fuelfilleddateent: String = "12/12/2010"

    @ColumnInfo
    var noteAny : String = "None"

    @ColumnInfo
    var car_fuel_add1 : String = "add1"

    @ColumnInfo
    var car_fuel_add2 : String = "add2"

    @ColumnInfo
    var car_fuel_add3 : Int = 12345

    @ColumnInfo
    var car_fuel_add4 : Double = 6789.00


}