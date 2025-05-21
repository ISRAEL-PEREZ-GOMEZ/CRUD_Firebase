package com.example.crud_firebase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.crud_firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bingdin: ActivityMainBinding
    private lateinit var adapter: TareaAdapter
    private lateinit var viewModel: TareaViewModel

    var tareaEdit = Tarea()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bingdin  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bingdin.root)

        viewModel = ViewModelProvider(this)[TareaViewModel::class.java]


        viewModel.listaTareas.observe (this){tareas ->
            setpRecyclerView(tareas)
        }

        bingdin.btnAgregarTarea.setOnClickListener{
            val tarea = Tarea(
                titulo = bingdin.etTitulo.text.toString(),
                descripcion = bingdin.etDescripcion.text.toString()
            )
            viewModel.agregarTarea(tarea)

            bingdin.etTitulo.setText("")
            bingdin.etDescripcion.setText("")
        }

        bingdin.btnActualizarTarea.setOnClickListener {
            tareaEdit.titulo = ""
            tareaEdit.descripcion= ""

            tareaEdit.titulo = bingdin.etTitulo.text.toString()
            tareaEdit.descripcion = bingdin.etDescripcion.text.toString()

            viewModel.actualizarTarea(tareaEdit)

            adapter.notifyDataSetChanged()

            bingdin.etTitulo.setText("")
            bingdin.etDescripcion.setText("")

        }

    }

    fun setpRecyclerView(listaTareas: List<Tarea>){
        adapter  = TareaAdapter(listaTareas, :: borrarTarea, ::actualizarTarea)
        bingdin.rvTareas.adapter = adapter


    }
    fun borrarTarea(id: String){
        viewModel.borrarTarea(id)

    }
    fun actualizarTarea(tarea: Tarea){
        tareaEdit = tarea

        bingdin.etTitulo.setText(tareaEdit.titulo)
        bingdin.etDescripcion.setText(tareaEdit.descripcion)
    }
}