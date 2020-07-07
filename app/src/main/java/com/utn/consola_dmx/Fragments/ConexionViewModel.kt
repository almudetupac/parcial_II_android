package com.utn.consola_dmx.Fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.akexorcist.bluetotohspp.library.BluetoothSPP

class ConexionViewModel : ViewModel() {

    private var bt: MutableLiveData<BluetoothSPP> = MutableLiveData()
    var conectado: MutableLiveData<Boolean> = MutableLiveData()

    fun setConectad (valor : Boolean)
    {
        conectado.value = valor
    }
    fun setBt(BT : BluetoothSPP)
    {
        bt.value =  BT
    }

    fun getConectado () : Boolean {
        if (conectado.value != null)
        {
            return  conectado.value!!
        }
        return false
    }

    fun getBt (): BluetoothSPP?
    {
        return bt.value
    }

    // TODO: Implement the ViewModel
}
