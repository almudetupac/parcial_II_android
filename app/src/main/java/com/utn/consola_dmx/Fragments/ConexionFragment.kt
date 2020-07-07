package com.utn.consola_dmx.Fragments

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import app.akexorcist.bluetotohspp.library.BluetoothSPP.BluetoothConnectionListener
import app.akexorcist.bluetotohspp.library.DeviceList

import com.utn.consola_dmx.R

class ConexionFragment : Fragment() {

    lateinit var bt: BluetoothSPP
    lateinit var v: View
    companion object {
        fun newInstance() = ConexionFragment()
    }

    private lateinit var viewModel_Conexion: ConexionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.conexion_fragment, container, false)



        if (bt.serviceState == BluetoothState.STATE_CONNECTED) {
            bt.disconnect()
        } else {
            val intent = Intent(context, DeviceList::class.java)
            startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE)
        }

        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel_Conexion = ViewModelProvider(requireActivity()).get(ConexionViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bt = BluetoothSPP(this.context)

        //bt.send("aaa", true);

        bt.setOnDataReceivedListener { data, message ->

            Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
        }

        if (!bt.isBluetoothEnabled) {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT)
        } else {
            if (bt.isServiceAvailable) {
                bt.setupService()
                bt.startService(BluetoothState.DEVICE_OTHER)
                // setup();
            }
        }



        bt.setBluetoothConnectionListener(object : BluetoothConnectionListener {
            override fun onDeviceConnected(name: String, address: String) {
                Toast.makeText(context
                    , "Connected to $name\n$address"
                    , Toast.LENGTH_SHORT).show()
                viewModel_Conexion.setConectad(true)
                //v.findNavController().navigate(ManualFragmentDirections.actionManualFragmentSelf())
            }

            override fun onDeviceDisconnected() {
                Toast.makeText(context
                    , "Connection lost", Toast.LENGTH_SHORT).show()
               viewModel_Conexion.setConectad(false)
               // v.findNavController().navigate(ManualFragmentDirections.actionManualFragmentSelf())
            }

            override fun onDeviceConnectionFailed() {
                Toast.makeText(context
                    , "Unable to connect", Toast.LENGTH_SHORT).show()
                viewModel_Conexion.setConectad(false)
               // v.findNavController().navigate(ManualFragmentDirections.actionManualFragmentSelf())
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {

            //    if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService()
                bt.startService(BluetoothState.DEVICE_OTHER)
                //setup();
                //} else {
                // Do something if user doesn't choose any device (Pressed back)
                // }
            }

            if (resultCode == Activity.RESULT_OK) {
                bt.connect(data)
                viewModel_Conexion.setBt(bt)
                v.findNavController().navigate(ConexionFragmentDirections.actionConexionFragmentToManualFragment())
            }
        }


    }
}
