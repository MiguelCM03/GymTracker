package com.example.gymtracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeleccionAccionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seleccion_accion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Creo las variables con las que accedo a los botones
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)
        val btnInsertar  = findViewById<Button>(R.id.btnInsertar)

        //Creo los on click listener de los dos botones
        btnConsultar.setOnClickListener(){
            seleccionarAccion(btnConsultar.text.toString().trim())
        }

        btnInsertar.setOnClickListener(){
            seleccionarAccion(btnInsertar.text.toString().trim())
        }

        //var btnConfirmar = findViewById<Button>(R.id.btnConfirmar)


    }//onCreate



    fun seleccionarAccion(isAccion: String){
        val nombreUsuarioActual = intent.getStringExtra("USUARIO")
        if(isAccion.equals(getString(R.string.strInsertarDatos))){
            val intentInsertar = Intent(this, InsertarDatosActivity::class.java)
            intentInsertar.putExtra("accion", isAccion)
            intentInsertar.putExtra("USUARIO", nombreUsuarioActual)
            startActivity(intentInsertar)
        }
        else if(isAccion.equals(getString(R.string.strConsultarDatos))){
            val intentConsultar = Intent(this, ConsultarDatosActivity::class.java)
            intentConsultar.putExtra("accion", isAccion)
            intentConsultar.putExtra("USUARIO", nombreUsuarioActual)
            startActivity(intentConsultar)
        }
        else{
            val alertaSeleccionAccion = AlertDialog.Builder(this)
            alertaSeleccionAccion.setTitle("Error")
            alertaSeleccionAccion.setMessage("Ha habido un error al pulsar el botón.")
            alertaSeleccionAccion.setPositiveButton("Aceptar", null)
            val dialogoSeleccionAccion: AlertDialog = alertaSeleccionAccion.create()
            dialogoSeleccionAccion.show()
        }
    }

}