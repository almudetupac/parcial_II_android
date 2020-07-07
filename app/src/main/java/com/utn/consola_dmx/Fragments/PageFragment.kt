package com.utn.consola_dmx.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.ToggleButton
import androidx.lifecycle.ViewModelProvider

import com.utn.consola_dmx.R

class PageFragment : Fragment() {

    lateinit var v: View

    lateinit var tbtPageA : ToggleButton
    lateinit var tbtPageB : ToggleButton
    lateinit var tbtPage1 : ToggleButton
    lateinit var tbtPage2 : ToggleButton
    lateinit var tbtPage3 : ToggleButton
    lateinit var tbtPage4 : ToggleButton
    lateinit var tbtPage5 : ToggleButton
    lateinit var tbtPage6 : ToggleButton
    lateinit var tbtPage7 : ToggleButton
    lateinit var tbtPage8 : ToggleButton
    lateinit var tbtPage9 : ToggleButton
    lateinit var tbtPage10 : ToggleButton
    lateinit var tbtPage11 : ToggleButton
    lateinit var tbtPage12 : ToggleButton

    companion object {
        fun newInstance() = PageFragment()
    }

    private lateinit var viewModel_Page: PageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v  = inflater.inflate(R.layout.page_fragment, container, false)

        tbtPageA = v.findViewById(R.id.tbt_pageA)
        tbtPageB = v.findViewById(R.id.tbt_pageB)
        tbtPage1 = v.findViewById(R.id.tbt_page1)
        tbtPage2 = v.findViewById(R.id.tbt_page2)
        tbtPage3 = v.findViewById(R.id.tbt_page3)
        tbtPage4 = v.findViewById(R.id.tbt_page4)
        tbtPage5 = v.findViewById(R.id.tbt_page5)
        tbtPage6 = v.findViewById(R.id.tbt_page6)
        tbtPage7= v.findViewById(R.id.tbt_page7)
        tbtPage8 = v.findViewById(R.id.tbt_page8)
        tbtPage9 = v.findViewById(R.id.tbt_page9)
        tbtPage10 = v.findViewById(R.id.tbt_page10)
        tbtPage11 = v.findViewById(R.id.tbt_page11)
        tbtPage12 = v.findViewById(R.id.tbt_page12)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel_Page = ViewModelProvider(requireActivity()).get(PageViewModel::class.java)
        // TODO: Use the ViewModel



        viewModel_Page.initPage()



    }

    override fun onStart() {
        super.onStart()

        tbtPageA.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage("A", isChecked) }
        tbtPageB.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage("B", isChecked) }
        tbtPage1.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(0, isChecked) }
        tbtPage2.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(1, isChecked) }
        tbtPage3.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(2, isChecked) }
        tbtPage4.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(3, isChecked) }
        tbtPage5.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(4, isChecked) }
        tbtPage6.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(5, isChecked) }
        tbtPage7.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(6, isChecked) }
        tbtPage8.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(7, isChecked) }
        tbtPage9.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(8, isChecked) }
        tbtPage10.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(9, isChecked) }
        tbtPage11.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(10, isChecked) }
        tbtPage12.setOnCheckedChangeListener { buttonView, isChecked -> viewModel_Page.setPage(11, isChecked) }



    }

}
