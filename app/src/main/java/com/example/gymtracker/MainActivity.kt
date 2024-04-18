package com.example.gymtracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val txtCrearCuenta = findViewById<TextView>(R.id.txtCrearCuenta)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        btnCrearCuenta.setOnClickListener{actCrearCuenta()}
        txtCrearCuenta.setOnClickListener{actCrearCuenta()}
        btnIniciarSesion.setOnClickListener{clickIniciarSesion()}
    }

    fun actCrearCuenta(){
        val intent = Intent(this, CrearCuentaActivity::class.java)
        startActivity(intent)
    }
    fun clickIniciarSesion(){
        if(txtContrasena?.inputType == android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD){txtContrasena?.inputType = android.text.InputType.TYPE_CLASS_TEXT}
        else{txtContrasena?.inputType = android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD}

    }
}