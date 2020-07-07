package com.utn.consola_dmx.Fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.akexorcist.bluetotohspp.library.BluetoothSPP

class ManualViewModel : ViewModel() {
    private var canalesList: MutableLiveData<MutableList<String>> = MutableLiveData()
    private var toolbarX: MutableLiveData<Boolean> = MutableLiveData()

    // TODO: Implement the ViewModel

    fun setToolbarX (valor : Boolean)
    {
        toolbarX.value = valor
    }


    fun getToolbarX () : Boolean {
        if (toolbarX.value != null)
        {
            return  toolbarX.value!!
        }
        return false
    }

    fun initCanal()
    {
        if(canalesList.value == null)
        {
            canalesList.value = mutableListOf()
            var canal:Int = 0
            while (canal < 192)
            {
                canalesList.value!!.add("0")
                canal += 1
            }
        }

    }
    fun setCanal(canal : Int, valor: String)
    {
        canalesList.value!![canal] =  valor
    }

    fun getCanales() : MutableList<String>
    {
        return  canalesList.value!!
    }
}
