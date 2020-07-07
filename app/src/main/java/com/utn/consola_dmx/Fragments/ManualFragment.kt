package com.utn.consola_dmx.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

import app.akexorcist.bluetotohspp.library.BluetoothSPP
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import com.utn.consola_dmx.R
import kotlinx.coroutines.*

class ManualFragment : Fragment() {


    lateinit var v: View
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

    var canal : Int = 0

    private val titles = arrayOf("Page", "Faders")






    companion object {
        fun newInstance() = ManualFragment()
    }

    private lateinit var viewModel_manual: ManualViewModel
    private lateinit var viewModel_Conexion: ConexionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.manual_fragment, container, false)

        tabLayout = v.findViewById(R.id.tab_layout)

        viewPager = v.findViewById(R.id.view_pager)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel_manual = ViewModelProvider(requireActivity()).get(ManualViewModel::class.java)
        viewModel_Conexion = ViewModelProvider(requireActivity()).get(ConexionViewModel::class.java)
        viewModel_manual.initCanal()




      /*  viewModel_Conexion.conectado.observe(viewLifecycleOwner, Observer { result ->
            if (result != viewModel_manual.getToolbarX())
            {
                v.findNavController().navigate(ManualFragmentDirections.actionManualFragmentSelf())
            }
        })*/



        // TODO: Use the ViewModel

    }







    ///////////Tolbar ////////////////

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {



            menu.clear()
            if (viewModel_Conexion.getConectado()) {
                inflater.inflate(R.menu.manual_toolbar2, menu)
                viewModel_manual.setToolbarX(true)
            } else {
                inflater.inflate(R.menu.manual_toolbar1, menu)
                viewModel_manual.setToolbarX(false)
            }


        viewModel_Conexion.conectado.observe(viewLifecycleOwner, Observer { result ->
            if (result != viewModel_manual.getToolbarX()) {
                menu.clear()
                if (viewModel_Conexion.getConectado()) {
                    inflater.inflate(R.menu.manual_toolbar2, menu)
                    viewModel_manual.setToolbarX(true)
                } else {
                    inflater.inflate(R.menu.manual_toolbar1, menu)
                    viewModel_manual.setToolbarX(false)
                }

            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = when(item.itemId) {


            R.id.bt_conectar ->v.findNavController().navigate(ManualFragmentDirections.actionManualFragmentToConexionFragment())

            R.id.bt_guardar ->v.findNavController().navigate(ManualFragmentDirections.actionManualFragmentToGuardarFragment())

            R.id.bt_settings  -> v.findNavController().navigate(ManualFragmentDirections.actionManualFragmentToSettingsActivity())

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





        // viewModel.id = macetaFragmentArgs.fromBundle(arguments!!).plantaId.toString()
         try {
             viewPager.setAdapter(createCardAdapter())
         }catch (e:Exception){}
        // viewPager.isUserInputEnabled = false
        //viewModel.context_maceta.value = context
        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.text = "Page"
                1 -> tab.text = "Faders"
                // 2 -> tab.text = "Tab3"
                else -> tab.text = "undefined"
            }
        }).attach()


        val parentJob = Job()
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("demo", "handler: $throwable") // Prints "handler: java.io.IOException"
        }
        val scope = CoroutineScope(Dispatchers.Default + parentJob) //+ handler)

        scope.launch {

            val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

            while (true)
            {
               /* if (prefs.getString("reply","200") != null)
                {*/
                    //var aux = prefs.getString("reply","200")!!.toLong()
                    break_time(prefs.getString("t_Sinc","200")!!.toLong())
                /*}
                else
                {
                    break_time(200)
                }*/

                //bit_start(viewModel_Conexion.getBt())
                enviar_canal(viewModel_manual.getCanales(), viewModel_Conexion.getBt(),prefs.getBoolean("sync",true))

            }

        }

    }

    private fun createCardAdapter(): ViewPagerAdapter? {
        return ViewPagerAdapter(requireActivity())
    }


    class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {

            return when (position) {
                0 -> PageFragment()
                1 -> FaderFragment()
                // 2 -> fragment3()

                else -> FaderFragment()
            }
        }

        override fun getItemCount(): Int {
            return TAB_COUNT
        }


        companion object {

            private const val TAB_COUNT = 2
        }

    }
}




suspend fun break_time(retardo: Long)
{
    delay(retardo)
    //Log.d("Test","break_time")
}



suspend fun bit_start( bt: BluetoothSPP?)
{
    if (bt != null) {

        try {
            bt.send("DMX", true)
        } catch (e: Exception) {
            //Log.d("Test", " no conectado")
        }
    }
    delay(1)

    //Log.d("Test","bit_start")
}



suspend fun enviar_canal(canales: MutableList<String>, bt: BluetoothSPP?, Sinc: Boolean) {




    var pepe: ByteArray = canales.toString().toByteArray()


    if ((bt != null) && Sinc) {
        try {

            bt.send(pepe, false)

        } catch (e: Exception) {

            Log.d("test", "Fallo")
        }
    }

}







