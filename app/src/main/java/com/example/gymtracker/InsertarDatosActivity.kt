package com.example.gymtracker

import android.content.ContentValues
import android.database.SQLException
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InsertarDatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insertar_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var npPesoInsertar = findViewById<NumberPicker>(R.id.npPesoInsertar)
        npPesoInsertar.minValue = 0
        npPesoInsertar.maxValue = 400


        val btnConfirmarInsercion = findViewById<Button>(R.id.btnConfirmarAccionInsertar)

        val contextoInsercion = this.baseContext
        var insercionPosible = false
        var spGruposInsertar = findViewById<Spinner>(R.id.spGruposInsertar)
        var spAnosInsertar = findViewById<Spinner>(R.id.spAnosInsertar)
        var spMesesInsertar = findViewById<Spinner>(R.id.spMesesInsertar)
        var spEjerciciosInsertar = findViewById<Spinner>(R.id.spEjerciciosInsertar)


        btnConfirmarInsercion.setOnClickListener {
//            insertarDatos(spEjerciciosInsertar.selectedItem.toString(), spAnosInsertar.selectedItem.toString(), spMesesInsertar.selectedItem.toString(), npPesoInsertar.value.toFloat())
            if(insercionPosible)
                insertarDatos(spEjerciciosInsertar.selectedItem.toString(), spAnosInsertar.selectedItem.toString(), spMesesInsertar.selectedItem.toString(), npPesoInsertar.value.toFloat())
            else
                mostrarErrorInsercion()
        }

        spGruposInsertar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position == 0) {//NADA
                    val adaptador = ArrayAdapter.createFromResource(
                        contextoInsercion,
                        R.array.emptyArray,
                        android.R.layout.simple_spinner_item
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosInsertar.adapter = adaptador
                    insercionPosible = false
                } else if (position == 1) {//BRAZO
                    val adaptador = ArrayAdapter.createFromResource(
                        contextoInsercion,
                        R.array.slEjsBrazo,
                        android.R.layout.simple_spinner_item
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosInsertar.adapter = adaptador
                    insercionPosible = true
                } else if (position == 2) {//PIERNA
                    val adaptador = ArrayAdapter.createFromResource(
                        contextoInsercion,
                        R.array.slEjsPierna,
                        android.R.layout.simple_spinner_item
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosInsertar.adapter = adaptador
                    insercionPosible = true
                } else if (position == 3) {//TORSO
                    val adaptador = ArrayAdapter.createFromResource(
                        contextoInsercion,
                        R.array.slEjsTorso,
                        android.R.layout.simple_spinner_item
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosInsertar.adapter = adaptador
                    insercionPosible = true
                } else if (position == 4) {//CARDIO
                    val adaptador = ArrayAdapter.createFromResource(
                        contextoInsercion,
                        R.array.slEjsCardio,
                        android.R.layout.simple_spinner_item
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosInsertar.adapter = adaptador
                    insercionPosible = true
                } else if (position == 5) {//ABDOMEN
                    val adaptador = ArrayAdapter.createFromResource(
                        contextoInsercion,
                        R.array.slEjsAbdomen,
                        android.R.layout.simple_spinner_item
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosInsertar.adapter = adaptador
                    insercionPosible = true
                } else if (position == 6) {//ESPALDA
                    val adaptador = ArrayAdapter.createFromResource(
                        contextoInsercion,
                        R.array.slEjsEspalda,
                        android.R.layout.simple_spinner_item
                    )
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosInsertar.adapter = adaptador
                    insercionPosible = true
                }//else if
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }//onItemSelectedListener

    }//onCreate()
    fun insertarDatos(nombreEjercicioInsertar: String, anoInsertar: String, mesInsertar: String, pesoInsertar: Float) {

        var nombreUsuario = intent.getStringExtra("USUARIO")
        var woDb = DatabaseHelper(this)
        var dbInsertador = woDb.writableDatabase
        var valoresInsertar = ContentValues().apply {
            put("nombre_usuario", nombreUsuario)
            put("nombre_ejercicio", nombreEjercicioInsertar.uppercase())
            put("mes", mesInsertar)
            put("ano", anoInsertar)
            put("peso", pesoInsertar)
        }
        if(dbInsertador.insert("REGISTROS", null, valoresInsertar)==(-1).toLong()){
            mostrarErrorInsercion()
        }else{
            mostrarMensajeCorrecto()
        }
    }//insertarDatos()

    fun mostrarErrorInsercion(){
        val alertaInsercionIncorrecta = AlertDialog.Builder(this)
        alertaInsercionIncorrecta.setTitle("Error")
        alertaInsercionIncorrecta.setMessage("Selecciona un grupo muscular y un ejercicio para continuar")
        alertaInsercionIncorrecta.setPositiveButton("OK", null)
        val dialogoInsercionIncorrecta: AlertDialog = alertaInsercionIncorrecta.create()
        dialogoInsercionIncorrecta.show()
    }//mostrarErrorInsercion()

    fun mostrarMensajeCorrecto(){
        val alertaInsercionCorrecta = AlertDialog.Builder(this)
        alertaInsercionCorrecta.setTitle("Insercion correcta")
        alertaInsercionCorrecta.setMessage("Se han insertado los datos de manera correcta.")
        alertaInsercionCorrecta.setPositiveButton("OK", null)
        val dialogoInsercionCorrecta: AlertDialog = alertaInsercionCorrecta.create()
        dialogoInsercionCorrecta.show()
    }
}
