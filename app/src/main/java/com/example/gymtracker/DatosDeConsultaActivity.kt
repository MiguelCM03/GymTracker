package com.example.gymtracker

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DatosDeConsultaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datos_de_consulta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvValorMesConsultado = findViewById<TextView>(R.id.tvValorMesConsultado)
        val tvValorEjercicioConsultado = findViewById<TextView>(R.id.tvValorEjercicioConsultado)
        val tvValorAnoConsultado = findViewById<TextView>(R.id.tvValorAnoConsultado)

        val anoConsultado = intent.getStringExtra("ANO")
        val mesConsultado = intent.getStringExtra("MES")
        val ejercicioConsultado = intent.getStringExtra("EJERCICIO")
        tvValorMesConsultado.setText(mesConsultado)
        tvValorEjercicioConsultado.setText(ejercicioConsultado)
        tvValorAnoConsultado.setText(anoConsultado)
    }
}