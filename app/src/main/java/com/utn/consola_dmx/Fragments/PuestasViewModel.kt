package com.utn.consola_dmx.Fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PuestasViewModel : ViewModel() {
    var list_delet : MutableLiveData<MutableList<Int>> =   MutableLiveData(mutableListOf())

    var delet_f : MutableLiveData<Boolean> = MutableLiveData(false)

    var borre: MutableLiveData<Boolean> = MutableLiveData(false)


    fun Vaciar_Delete(){
        if (list_delet.value != null)
        {
             list_delet.value!!.clear()

           delet_f.value = false
            borre.value = false
        }

        }
}
