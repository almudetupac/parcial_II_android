package com.utn.consola_dmx.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utn.consola_dmx.Entities.Canal
import com.utn.consola_dmx.Entities.Escena

@Database(entities = [Escena::class, Canal::class], version = 1  , exportSchema = false)

public  abstract class appDatabase : RoomDatabase() {

    abstract fun escenaDao(): escenaDao
    abstract fun canalDao(): canalDao


    companion object {
        var INSTANCE: appDatabase? = null

        fun getAppDataBase(context: Context): appDatabase? {
            if (INSTANCE == null) {
                synchronized(appDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
                        "DMX_DB"
                    ).allowMainThreadQueries().build() // No es lo mas recomendable que se ejecute en el mainthread
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}