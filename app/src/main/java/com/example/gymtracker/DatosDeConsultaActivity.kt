package com.example.gymtracker

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

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
        val tvValorPesoConsultado = findViewById<TextView>(R.id.tvValorPesoConsultado)
        val tvPesoConsultado = findViewById<TextView>(R.id.tvPesoConsultado)


        if(intent.getStringExtra("MEDIDA").equals("MINUTOS")){
            tvPesoConsultado.setText(getString(R.string.strMinutos))
        }

        val anoConsultado = intent.getStringExtra("ANO")
        val mesConsultado = intent.getStringExtra("MES")
        val ejercicioConsultado = intent.getStringExtra("EJERCICIO")
        val pesoConsultado =  intent.getStringArrayExtra("PESO")


        tvValorMesConsultado.setText(mesConsultado)
        tvValorEjercicioConsultado.setText(ejercicioConsultado)
        tvValorAnoConsultado.setText(anoConsultado)

        var textoPesos = ""
        if (pesoConsultado != null) {
            var contador = 0
            while(contador<pesoConsultado.size){
                textoPesos += pesoConsultado[contador]
                textoPesos += ", "
                contador ++
            }
        }
        if(textoPesos.length>0) {
            var textoPesosMostrado = textoPesos.substring(0, textoPesos.length - 2)
            tvValorPesoConsultado.setText(textoPesosMostrado)
        }
    }
}