package com.utn.consola_dmx.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "Canales")
class Canal (escena_id : Int, canal : Int, valor : String){


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "escena_id")
    var escena_id : Int

    @ColumnInfo(name = "Canal")
    var canal : Int

    @ColumnInfo(name = "valor")
    var valor : String

    init {

        this.escena_id = escena_id
        this.canal = canal
        this.valor = valor


    }

}

