package com.example.mobilecourseworktwo

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [DataBase::class], version = 1)
    abstract class DBConnection:RoomDatabase() {
        abstract fun databaseDao():MealDao
    }
