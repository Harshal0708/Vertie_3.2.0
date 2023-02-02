package com.vertie.data

import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.room.Database


//@Database(
//    entities = [
//
//    ],
//    version = 1,
//    exportSchema = true
//)

abstract class Database : RoomDatabase(){

    fun clearTables() {
        GlobalScope.launch(Dispatchers.IO) {
            this@Database.clearAllTables()
        }
    }
    companion object {
    }


}