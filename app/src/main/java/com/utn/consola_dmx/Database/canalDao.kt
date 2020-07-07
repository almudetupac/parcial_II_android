package com.utn.consola_dmx.Database

import androidx.room.*
import com.utn.consola_dmx.Entities.Canal


@Dao
interface canalDao {


    @Query("SELECT * FROM Canales WHERE id = :id")
    fun getCanal(id: Int?): MutableList<Canal>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCanal(canal: Canal?)

    @Update
    fun updateCanal(canal: Canal?)

    @Delete
    fun delete(canal: Canal?)

    @Query("SELECT COUNT(*) FROM Canales")
    fun cantCanales(): Int?

    @Query("DELETE FROM Canales WHERE escena_id = :escena_id")
    fun deleteCanalFromEscena(escena_id: Int?)

    @Query("SELECT * FROM Canales  WHERE escena_id = :escena_id ORDER BY canal")
    fun loadAllCanales(escena_id: Int?): MutableList<Canal?>?

}