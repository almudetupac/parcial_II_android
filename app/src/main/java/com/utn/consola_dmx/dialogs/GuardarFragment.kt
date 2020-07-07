package com.utn.consola_dmx.dialogs



import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.github.nikartm.button.FitButton
import com.google.android.material.snackbar.Snackbar



import androidx.fragment.app.Fragment

import com.utn.consola_dmx.Entities.Escena
import com.utn.consola_dmx.Fragments.ConexionViewModel
import com.utn.consola_dmx.Fragments.ManualViewModel






import com.utn.consola_dmx.R
import com.utn.consola_dmx.Services.serviceEscena

class GuardarFragment :  DialogFragment() {


    lateinit var v: View

    lateinit var edt_nota: EditText
    lateinit var btnAccept: FitButton
    lateinit var btnCancel: FitButton
    lateinit var sEscena: serviceEscena
    private lateinit var viewModel_manual: ManualViewModel

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       v = inflater.inflate(R.layout.fragment_guardar, container, false)

        edt_nota = v.findViewById(R.id.edt_nota_dialog)
        btnAccept = v.findViewById(R.id.btn_acept_dialog)
        btnCancel = v.findViewById(R.id.btn_cancel_dialog)
        sEscena = serviceEscena(v)
        return v
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("nota","3")
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel_manual = ViewModelProvider(requireActivity()).get(ManualViewModel::class.java)
    }

        var aux : Int = 0
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStart() {
        super.onStart()



        Log.d("nota","5")
        btnCancel.setOnClickListener() {
            dismiss()
        }





        btnAccept.setOnClickListener {

            if (edt_nota.length() > 0) {

                sEscena.newEscena(Escena(edt_nota.text.toString()), viewModel_manual.getCanales())

                dismiss()
            } else {
                Snackbar.make(
                    v, "campo vacio", Snackbar.LENGTH_SHORT
                ).show()
            }
        }






    }






    companion object {
        fun newInstance() = GuardarFragment()
    }



}
