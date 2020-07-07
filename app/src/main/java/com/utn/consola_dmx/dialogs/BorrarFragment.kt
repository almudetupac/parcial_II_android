package com.utn.consola_dmx.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.github.nikartm.button.FitButton
import com.utn.consola_dmx.Fragments.PuestasViewModel

import com.utn.consola_dmx.R
import com.utn.consola_dmx.Services.serviceEscena


class BorrarFragment :  DialogFragment() {

    lateinit var v: View

    lateinit var btnAccept: FitButton
    lateinit var btnCancel: FitButton
    lateinit var sEscena: serviceEscena

    private lateinit var viewModel_Puestas: PuestasViewModel


    companion object {
        fun newInstance() = BorrarFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_borrar, container, false)

        btnAccept = v.findViewById(R.id.btn_acept_dialog)
        btnCancel = v.findViewById(R.id.btn_cancel_dialog)
        sEscena = serviceEscena(v)

        return v

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel_Puestas = ViewModelProvider(requireActivity()).get(PuestasViewModel::class.java)
    }




    override fun onStart() {
        super.onStart()


        btnCancel.setOnClickListener() {

            dismiss()
        }

        btnAccept.setOnClickListener {
            Eliminar()

            dismiss()
        }
    }


    fun Eliminar(){

        for (Escena in viewModel_Puestas.list_delet.value!!){
            sEscena.deletEscena(Escena)
        }
        viewModel_Puestas.Vaciar_Delete()
    }
}
