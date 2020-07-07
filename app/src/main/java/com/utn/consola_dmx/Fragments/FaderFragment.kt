package com.utn.consola_dmx.Fragments

import  androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider

import com.utn.consola_dmx.R

class FaderFragment : Fragment() {

    lateinit var v: View
    lateinit var fader_1 : SeekBar
    lateinit var fader_2 : SeekBar
    lateinit var fader_3 : SeekBar
    lateinit var fader_4 : SeekBar
    lateinit var fader_5 : SeekBar
    lateinit var fader_6 : SeekBar
    lateinit var fader_7 : SeekBar
    lateinit var fader_8 : SeekBar

    companion object {
        fun newInstance() = FaderFragment()
    }

    private lateinit var viewModel: FaderViewModel
    private lateinit var viewModel_Page: PageViewModel
    private lateinit var viewModel_manual: ManualViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fader_fragment, container, false)

        fader_1 = v.findViewById(R.id.sb_Feder1)
        fader_2 = v.findViewById(R.id.sb_Feder2)
        fader_3 = v.findViewById(R.id.sb_Feder3)
        fader_4 = v.findViewById(R.id.sb_Feder4)
        fader_5 = v.findViewById(R.id.sb_Feder5)
        fader_6 = v.findViewById(R.id.sb_Feder6)
        fader_7 = v.findViewById(R.id.sb_Feder7)
        fader_8 = v.findViewById(R.id.sb_Feder8)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FaderViewModel::class.java)
        viewModel_Page = ViewModelProvider(requireActivity()).get(PageViewModel::class.java)
        viewModel_manual = ViewModelProvider(requireActivity()).get(ManualViewModel::class.java)

        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        fader_1.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
               // Log.d("Fader1",  seek.progress.toString())
                setCanal(1,seek.progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
              //  Log.d("Fader", "Progress is: " + seek.progress+"fin")
            }
        })

        fader_2.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                //Log.d("Fader2",  seek.progress.toString())
                setCanal(2,seek.progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
             //   Log.d("Fader", "Progress is: " + seek.progress+"fin")
            }
        })

        fader_3.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                //Log.d("Fader3",  seek.progress.toString())
                setCanal(3,seek.progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
               // Log.d("Fader", "Progress is: " + seek.progress+"fin")
            }
        })


        fader_4.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                //Log.d("Fader4",  seek.progress.toString())
                setCanal(4,seek.progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            //    Log.d("Fader", "Progress is: " + seek.progress+"fin")
            }
        })


        fader_5.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                //Log.d("Fader5",  seek.progress.toString())
                setCanal(5,seek.progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
              //  Log.d("Fader", "Progress is: " + seek.progress+"fin")
            }
        })


        fader_6.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                //Log.d("Fader6",  seek.progress.toString())
                setCanal(6,seek.progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            //    Log.d("Fader", "Progress is: " + seek.progress+"fin")
            }
        })


        fader_7.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                //Log.d("Fader7",  seek.progress.toString())
                setCanal(7,seek.progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
               // Log.d("Fader", "Progress is: " + seek.progress+"fin")
            }
        })




        fader_8.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                //Log.d("Fader8",  seek.progress.toString())
                setCanal(8,seek.progress.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
               // Log.d("Fader", "Progress is: " + seek.progress+"fin")
            }
        })


    }

    private fun setCanal (fader:Int, valor:String)
    {

        if (viewModel_Page.getPage("A"))
        {
            for ((page , estado) in viewModel_Page.getPages().withIndex())
            {
                if (estado)
                {
                    viewModel_manual.setCanal(((fader-1) + (16 * page)) , valor)
                }
            }

        }

        if (viewModel_Page.getPage("B"))
        {
            for ((page , estado) in viewModel_Page.getPages().withIndex())
            {
                if (estado)
                {
                    viewModel_manual.setCanal(((fader-1) + 8 +(16*page)) , valor)
                }
            }

        }


    }

}


