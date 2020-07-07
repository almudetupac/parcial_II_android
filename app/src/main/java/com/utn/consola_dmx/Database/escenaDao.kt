package com.utn.consola_dmx.Database

import androidx.room.*
import com.utn.consola_dmx.Entities.Escena

@Dao
interface escenaDao {

    @Query("SELECT * FROM Escenas WHERE id = :id")
    fun getEscena(id: Int?): MutableList<Escena>?

    @Query("SELECT id FROM Escenas WHERE escena = :escena")
    fun getIdporEscena(escena: String?): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEscena(escena: Escena?)

    @Update
    fun updateEscena(escena: Escena?)

    @Delete
    fun delete(escena: Escena?)

    @Query("SELECT COUNT(*) FROM Escenas")
    fun cantEscenas(): Int?

    @Query("DELETE FROM Escenas WHERE id = :id")
    fun deleteEscenaFromId(id: Int?)

    @Query("SELECT * FROM Escenas ORDER BY id")
    fun loadAllEscenas(): MutableList<Escena?>?

}