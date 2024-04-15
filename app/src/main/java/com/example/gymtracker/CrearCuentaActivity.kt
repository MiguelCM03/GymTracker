package com.example.gymtracker

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
var txtUsuario: EditText? = null
var txtContrasena: EditText? = null
class CrearCuentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtUsuario = findViewById(R.id.txtUsuario)
        txtContrasena = findViewById(R.id.txtContrasena)


    }

    fun insertarDatos(view: View){
        var conn = SQLite(this, "Clientes", null, 1)
        var baseDatos = conn.writableDatabase
        var wsNombre = txtUsuario?.text.toString()
        var wsContrasena = txtContrasena?.text.toString()
        if(!wsNombre.isEmpty() && !wsContrasena.isEmpty()){
            var registro = ContentValues()
            registro.put("wsNombre", wsNombre)
            registro.put("wsContrasena", wsContrasena)
            baseDatos.insert("USERS", null, registro)
            txtContrasena?.setText("")
            txtUsuario?.setText("")
            Toast.makeText(this, "Registro añadido con exito", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Campos vacíos", Toast.LENGTH_LONG).show()
        }

    }

}