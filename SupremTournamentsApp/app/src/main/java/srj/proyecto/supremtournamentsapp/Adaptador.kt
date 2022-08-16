package com.example.iphotoraterest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import srj.proyecto.supremtournamentsapp.R
import srj.proyecto.supremtournamentsapp.clases.TorneoIndividual
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Adaptador(datos: ArrayList<TorneoIndividual>) : RecyclerView.Adapter<Holder>(),View.OnClickListener {
    lateinit  var datos: ArrayList<TorneoIndividual>
    lateinit  var holder:Holder
    lateinit var listenerClick:View.OnClickListener;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view: View=LayoutInflater.from(parent.context).inflate(R.layout.cadview_layout,parent,false)
        holder = Holder(view)
        view.setOnClickListener(this)

        return holder
    }



    override fun onBindViewHolder(holder: Holder, position: Int) {
        Picasso.get()
            .load(datos[position].fotoUrlTorneoIndividual)
            .error(R.mipmap.ic_launcher)
            .into(holder.imagenTorneo)
        holder.dato=datos[position]
        holder.posicion=position

        holder.nombreTorneo.text = datos[position].nombreTorneoIndividual
        holder.descripcionCorta.text = datos[position].descripcionCorta
        holder.nivel.text = holder.nivel.text.toString() + ": " + datos[position].nivel.toString()

        var formato = SimpleDateFormat("dd/MM/yy")

        holder.fecha.text = formato.format(Date(datos[position].fechaInicio)) + "-" + formato.format(Date(datos[position].fechaFinalizacion))
        holder.gestor.text = holder.gestor.text.toString() + " " + datos[position].idGestor.nombre

        holder.bind(datos[position], position)
    }

    override fun getItemCount(): Int {
        return datos!!.size
    }

    init {
        this.datos = datos
    }

    fun onClick(listener:View.OnClickListener)
    {
        this.listenerClick=listener
    }
    override fun onClick(p0: View?) {
        listenerClick?.onClick(p0)
    }
}
