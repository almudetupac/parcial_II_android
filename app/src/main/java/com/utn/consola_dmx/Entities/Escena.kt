package com.utn.consola_dmx.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "Escenas")
class Escena (escena : String){


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "escena")
    var escena : String


    init {

        this.escena = escena


    }

}

