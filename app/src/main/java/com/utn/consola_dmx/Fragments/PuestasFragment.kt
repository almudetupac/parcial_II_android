package com.utn.consola_dmx.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.utn.consola_dmx.Database.appDatabase
import com.utn.consola_dmx.Entities.Canal
import com.utn.consola_dmx.Entities.Escena
import androidx.lifecycle.Observer


import com.utn.consola_dmx.R
import com.utn.consola_dmx.Services.serviceEscena
import com.utn.consola_dmx.adapters.EscenaListAdapter
import com.wajahatkarim3.roomexplorer.RoomExplorer

class PuestasFragment : Fragment() {

    lateinit var v: View
    lateinit var sEscena: serviceEscena

    lateinit var recEscena: RecyclerView

    var listEscenas : MutableList<Escena?>? = null


    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var escenaListAdapter: EscenaListAdapter


    companion object {
        fun newInstance() = PuestasFragment()
    }

    private lateinit var viewModel_Puestas: PuestasViewModel
    private lateinit var viewModel_manual: ManualViewModel
    private lateinit var viewModel_Conexion: ConexionViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.puestas_fragment, container, false)

        recEscena = v.findViewById(R.id.rec_escena)
        sEscena = serviceEscena(v)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel_Puestas = ViewModelProvider(requireActivity()).get(PuestasViewModel::class.java)
        viewModel_manual = ViewModelProvider(requireActivity()).get(ManualViewModel::class.java)
        viewModel_Conexion = ViewModelProvider(requireActivity()).get(ConexionViewModel::class.java)
        // TODO: Use the ViewModel
    }


    ///////////Tolbar ////////////////

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()



        if (viewModel_Conexion.getConectado()) {
            if (viewModel_Puestas.list_delet.value?.size == 0)
            {
                inflater.inflate(R.menu.puestas_toolbar_c, menu)
                viewModel_manual.setToolbarX(true)
            }
            else
            {
                inflater.inflate(R.menu.puestas_toolbar_c_b, menu)
                viewModel_manual.setToolbarX(true)
            }

        }
        else
        {
            if (viewModel_Puestas.list_delet.value?.size == 0)
            {
                inflater.inflate(R.menu.puestas_toolbar_d, menu)
                viewModel_manual.setToolbarX(false)
            }
            else
            {
                inflater.inflate(R.menu.puestas_toolbar_d_b, menu)
                viewModel_manual.setToolbarX(false)
            }
        }


        viewModel_Conexion.conectado.observe(viewLifecycleOwner, Observer { result ->
            if (result != viewModel_manual.getToolbarX()) {
                menu.clear()

                if (viewModel_Conexion.getConectado()) {
                    if (viewModel_Puestas.delet_f.value == false)
                    {
                        inflater.inflate(R.menu.puestas_toolbar_c, menu)
                        viewModel_manual.setToolbarX(true)
                    }
                    else
                    {
                        inflater.inflate(R.menu.puestas_toolbar_c_b, menu)
                        viewModel_manual.setToolbarX(true)
                    }

                }
                else
                {
                    if (viewModel_Puestas.delet_f.value == false)
                    {
                        inflater.inflate(R.menu.puestas_toolbar_d, menu)
                        viewModel_manual.setToolbarX(false)
                    }
                    else
                    {
                        inflater.inflate(R.menu.puestas_toolbar_d_b, menu)
                        viewModel_manual.setToolbarX(false)
                    }
                }
            }
        })





        viewModel_Puestas.delet_f.observe(viewLifecycleOwner, Observer { result ->


            menu.clear()
            if (result == false) {

                if (viewModel_Conexion.getConectado())
                {
                    inflater.inflate(R.menu.puestas_toolbar_c, menu)
                    viewModel_manual.setToolbarX(true)
                }
                else
                {
                    inflater.inflate(R.menu.puestas_toolbar_d, menu)
                    viewModel_manual.setToolbarX(false)
                }

            }
            else
            {
                if (viewModel_Conexion.getConectado())
                {
                    inflater.inflate(R.menu.puestas_toolbar_c_b, menu)
                    viewModel_manual.setToolbarX(true)
                }
                else
                {
                    inflater.inflate(R.menu.puestas_toolbar_d_b, menu)
                    viewModel_manual.setToolbarX(false)
                }
            }
        })

        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = when(item.itemId) {

            R.id.bt_conectar -> v.findNavController().navigate(PuestasFragmentDirections.actionPuestasFragmentToConexionFragment())

            R.id.bt_borrar  -> v.findNavController().navigate(PuestasFragmentDirections.actionPuestasFragmentToBorrarFragment())

            R.id.bt_settings  -> v.findNavController().navigate(PuestasFragmentDirections.actionPuestasFragmentToSettingsActivity())

            R.id.bt_atras -> viewModel_Puestas.Vaciar_Delete()
            else -> ""
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    /////////////////////////////////////




    override fun onStart() {
        super.onStart()



        listEscenas = sEscena.getAllEscenas()

        if(listEscenas == null){
            listEscenas = ArrayList<Escena?>()
        }


        recEscena.setHasFixedSize(false)


        linearLayoutManager = LinearLayoutManager(context)
        recEscena.layoutManager = linearLayoutManager                                   //defino el layaut de la lista

        escenaListAdapter = EscenaListAdapter(listEscenas!!, { onItemClick(it) }) { onItemLongClick(it) }             //instancio el adaptador, le mando la lista, Y escucho el clic por un lamda ( puntero a funcion)
        recEscena.adapter = escenaListAdapter                                           // cargo el adaptador




        viewModel_Puestas.borre.observe(viewLifecycleOwner, Observer { result ->
            listEscenas!!.clear()
            listEscenas = sEscena.getAllEscenas()

            if(listEscenas == null){
                listEscenas = ArrayList<Escena?>()
            }
            linearLayoutManager = LinearLayoutManager(context)
            recEscena.layoutManager = linearLayoutManager                                   //defino el layaut de la lista

            escenaListAdapter = EscenaListAdapter(listEscenas!!,  { onItemClick(it) }) { onItemLongClick(it) }             //instancio el adaptador, le mando la lista, Y escucho el clic por un lamda ( puntero a funcion)
            recEscena.adapter = escenaListAdapter

            //recEscena.adapter!!.notifyDataSetChanged()

        })
    }
    var aux : MutableList<Int> = mutableListOf()
    public fun onItemClick (id : Int?){

        if (viewModel_Puestas.list_delet.value?.size!! > 0){
            aux.addAll(viewModel_Puestas.list_delet.value!!)
            viewModel_Puestas.list_delet.value!!.clear()
            for ( escenaActual in aux ){
                //Log.d("Click", notaActual.toString())
                if (escenaActual != id!!){
                    viewModel_Puestas.list_delet.value!!.add(escenaActual)
                }
            }
            aux.clear()

            if (viewModel_Puestas.list_delet.value?.size!! > 0) {
                viewModel_Puestas.delet_f.value = true
            }
            else
            {
                viewModel_Puestas.delet_f.value=false
            }
        }
        else {

            for(canal: Canal? in sEscena.getCanalesporEscena(id!!)!!)
            {
                viewModel_manual.setCanal(canal!!.canal, canal!!.valor)
            }
            viewModel_Puestas.delet_f.value=false



        }
    }



    public fun onItemLongClick (id : Int?):Boolean{
        if (id != null) {
            viewModel_Puestas.list_delet.value?.add(id.toInt())
        }
        viewModel_Puestas.delet_f.value=true
        //Log.d("LongClick", list_delet.size.toString())
        //v.findNavController().navigate(listaFragmentDirections.actionListaFragmentToMacetaFragment(id!!.toInt()))
        return true
    }




/*
    private fun nav_eliminar(){
        if (viewModel.list_delet.size > 0){
            var action = listaFragmentDirections.actionListaFragmentToBorrarFragment("Maceta")
            v.findNavController().navigate(action)}
    }

    private fun Vaciar_lDelete(){
        for (fotoActual in viewModel.list_delet) {
            //Log.d("Click", notaActual.toString())
            if (fotoActual == id!!) {
                viewModel.list_delet.remove(fotoActual)
            }
        }
    }*/






}
