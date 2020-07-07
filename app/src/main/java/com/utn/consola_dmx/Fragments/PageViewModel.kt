package com.utn.consola_dmx.Fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.contracts.Returns

class PageViewModel : ViewModel() {

    private var page_A: MutableLiveData<Boolean> = MutableLiveData()
    private var page_B: MutableLiveData<Boolean> = MutableLiveData()
    private var pagesList: MutableLiveData<MutableList<Boolean>> = MutableLiveData()

    fun initPage()
    {
        page_A.value = false
        page_B.value = false

        pagesList.value = mutableListOf()

        var page:Int = 0
        while (page < 12)
        {
            pagesList.value?.add(false)
            page += 1
        }
    }


    fun setPage(tbt : String, valor: Boolean) {
        if ("A" == tbt) {
            page_A.value = valor
        }

        if ("B" == tbt) {
            page_B.value = valor
        }
    }


    fun setPage(tbt : Int, valor: Boolean) {

            pagesList.value!![tbt] = valor
        }


    fun getPage(tbt : String) : Boolean {
        if ("A" == tbt) {
            return page_A.value!!
        }

        if ("B" == tbt) {
            return page_B.value!!
        }
        return true
    }


    fun getPage(tbt : Int) : Boolean {

        return  pagesList.value!![tbt]

    }

    fun getPages() : MutableList<Boolean> {

        var lista :MutableList<Boolean> = pagesList.value!!
        return lista

    }

}
