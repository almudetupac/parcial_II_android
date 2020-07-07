package com.utn.consola_dmx.Services

import android.view.View
import android.view.WindowId
import com.utn.consola_dmx.Database.appDatabase
import com.utn.consola_dmx.Database.canalDao
import com.utn.consola_dmx.Database.escenaDao
import com.utn.consola_dmx.Entities.Canal
import com.utn.consola_dmx.Entities.Escena

class serviceEscena (v: View){

    private var escena : Escena? = null
    private var error : String =""
    private var db: appDatabase? = null
    private var escenaDao: escenaDao? = null
    private var canalDao: canalDao? = null
    private var escenasList: MutableList<Escena?>? = null




    init {
        db = appDatabase.getAppDataBase(v.context)
        escenaDao = db?.escenaDao()
        canalDao = db?.canalDao()
    }

    private fun setError (Error : String)
    {
        this.error  = Error
    }

    fun getError () : String
    {
        var aux = this.error

        this.error = ""
        return aux
    }

    fun getAllEscenas() : MutableList<Escena?>?
    {
       // Log.d("ser","1")
        if(escenaDao?.cantEscenas()==0)
        {


            //Log.d("ser","2")
            this.newEscena(Escena("BlackOut"), blackoutEsena())
        }
       // Log.d("ser","3")
        return escenaDao?.loadAllEscenas()

    }

    fun getCanalesporEscena(escena_id : Int) : MutableList<Canal?>?
    {
        var canales :MutableList<Canal?>? = canalDao?.loadAllCanales(escena_id)

        return canales

    }



    fun newEscena(escena: Escena?, canales: MutableList<String>)
    {

        escenaDao?.insertEscena(escena)

        //var canal:Int = 0
        for((canal, valor) in canales.withIndex())
        {

            canalDao?.insertCanal(Canal(escenaDao?.getIdporEscena(escena!!.escena)!! , canal, valor))
        }

    }



    fun deletEscena (id: Int?){
        canalDao?.deleteCanalFromEscena(id)
        escenaDao?.deleteEscenaFromId(id)

    }

    fun blackoutEsena() : MutableList<String>
    {
        var aux : MutableList<String> = mutableListOf()
        var canal:Int = 0
        while (canal < 192)
        {
            aux.add("0")
            canal += 1
        }

        return aux

    }

}