package com.example.iphotoraterest

import android.graphics.Color
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView

import androidx.recyclerview.widget.RecyclerView
import srj.proyecto.supremtournamentsapp.R
import srj.proyecto.supremtournamentsapp.clases.TorneoIndividual

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var dato: TorneoIndividual? =null
    var posicion:Int=0

    //Elementos Layout
    var cardView: CardView? = null

    var imagenTorneo:ImageView
    var nombreTorneo:TextView
    var descripcionCorta:TextView
    var nivel:TextView
    var fecha:TextView
    var gestor:TextView


    init {
        cardView = itemView.findViewById<CardView>(R.id.cardviewLayout)

        imagenTorneo = itemView.findViewById<ImageView>(R.id.imagenTorneo)
        nombreTorneo = itemView.findViewById<TextView>(R.id.nombreTorneo)
        descripcionCorta = itemView.findViewById<TextView>(R.id.descripcionCorta)
        nivel = itemView.findViewById<TextView>(R.id.nivel)
        fecha = itemView.findViewById<TextView>(R.id.fechas)
        gestor = itemView.findViewById<TextView>(R.id.gestor)

    }
    fun bind(d: TorneoIndividual,pos: Int){
        this.dato=d
        this.posicion=pos

        if(d.nivel!! in 0..5){
            nivel.setTextColor(Color.GREEN)
        }
        if(d.nivel!! in 5..7){
            nivel.setTextColor(Color.YELLOW)

        }
        if(d.nivel!! > 7){
            nivel.setTextColor(Color.RED)
        }
    }
}