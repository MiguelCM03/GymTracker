package com.example.gymtracker

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

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
    }//onCreate()
    fun fInsertarDatos(){
        var nombreUsuario = FirebaseAuth.getInstance().currentUser.toString()

    }

    //Funcion actualmente inutil
    fun insertarDatos(view: View){
        var conn = SQLite(this, "Clientes", null, 1)
        var baseDatos = conn.writableDatabase
        var wsNombre = txtCorreoNuevo?.text.toString()
        var wsContrasena = txtContrasenaNueva?.text.toString()
        if(!wsNombre.isEmpty() && !wsContrasena.isEmpty()){
            var registro = ContentValues()
            registro.put("wsNombre", wsNombre)
            registro.put("wsContrasena", wsContrasena)
            baseDatos.execSQL("SELECT * from USERS where UPPER(nombre) like UPPER()")
            baseDatos.insert("USERS", null, registro)
            txtContrasenaNueva?.setText("")
            txtCorreoNuevo?.setText("")
            Toast.makeText(this, "Registro añadido con exito", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Campos vacíos", Toast.LENGTH_LONG).show()
        }

    }//insertarDatos()
}