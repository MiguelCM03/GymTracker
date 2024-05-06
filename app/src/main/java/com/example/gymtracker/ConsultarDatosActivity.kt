package com.example.gymtracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConsultarDatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val intentThis = intent
        val textoBoton = intentThis.getStringExtra("accion")
//        val btnConfirmarAccion = findViewById<Button>(R.id.btnConfirmarAccion)
//        btnConfirmarAccion.setText(textoBoton)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seleccionar_fecha_grupo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}