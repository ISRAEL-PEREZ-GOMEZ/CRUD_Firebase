package com.example.crud_firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(
    var liataTareas: List<Tarea>,
    val onBorrarClick: (String) -> Unit,
    val onActualizarClick: (Tarea) -> Unit
): RecyclerView.Adapter<TareaAdapter.ViewHolder>(){

    class ViewHolder(itenview: View): RecyclerView.ViewHolder(itenview){
        val cvTarea: CardView = itenview.findViewById(R.id.cvTarea)
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val idtnBorrar: ImageButton = itemView.findViewById(R.id.ibtnBorrar)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TareaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_tarea, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaAdapter.ViewHolder, position: Int) {
        val tarea = liataTareas[position]

        holder.tvTitulo.text = tarea.titulo
        holder.tvDescripcion.text = tarea.descripcion

        holder.idtnBorrar.setOnClickListener {
            onBorrarClick(tarea.id)

        }

        holder.cvTarea.setOnClickListener {
            onActualizarClick(tarea)
        }
    }

    override fun getItemCount(): Int {
        return liataTareas.size
    }
}