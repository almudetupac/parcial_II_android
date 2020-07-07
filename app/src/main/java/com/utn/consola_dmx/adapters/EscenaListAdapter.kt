package com.utn.consola_dmx.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.utn.consola_dmx.Entities.Escena
import com.utn.consola_dmx.R

class EscenaListAdapter (private var escenaList: MutableList<Escena?>
                         ,val adapterOnClick : (id1 : Int?) -> Unit
                         ,val adapterOnLongClick: (id : Int?) -> Boolean) : RecyclerView.Adapter<EscenaListAdapter.EscenaHolder>() {


    companion object {

        private val TAG = "EscenaListAdapter"
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): EscenaHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_puesta,parent,false)
        return (EscenaHolder(
            view
        ))
    }

    override fun getItemCount(): Int {
        return escenaList.size
    }

    override fun onBindViewHolder(holder: EscenaHolder, position: Int) {

        escenaList[position]?.escena?.let { holder.setName(it) }
        holder.getCardLayout().setOnClickListener {
            holder.setCardElevation("30")
            adapterOnClick(escenaList[position]?.id?.toInt())

        }
        holder.getCardLayout().setOnLongClickListener {
            holder.setCardElevation("0")
            var id = escenaList[position]?.id
            adapterOnLongClick(id)
            true // <- set to true
        }
    }

    class EscenaHolder (v: View) : RecyclerView.ViewHolder(v){

        private var view: View

        init {
            this.view = v
        }

        fun setName(name : String) {
            val txt : TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }

        fun getCardLayout (): CardView {

            return view.findViewById(R.id.card_package_item)
        }

        fun setCardElevation ( elevation: String){

            val CardView_Nota : CardView = view.findViewById(R.id.card_package_item)

            CardView_Nota.cardElevation = elevation.toFloat()

        }

    }


}