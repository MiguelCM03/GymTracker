package com.example.gymtracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConsultarDatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consultar_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val contexto = this.baseContext

        val spGrupo = findViewById<Spinner>(R.id.spGruposConsultar)
        val spEjercicios = findViewById<Spinner>(R.id.spEjerciciosConsultar)
        val btnConfirmarConsulta = findViewById<Button>(R.id.btnConfirmarAccionConsultar)
        var consultaPosible = false

        btnConfirmarConsulta.setOnClickListener{
            if(consultaPosible){
                val intentDatosDeConsulta = Intent(this, DatosDeConsultaActivity::class.java)
                startActivity(intentDatosDeConsulta)
            }
        }


        spGrupo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //lateinit var adaptador: ArrayAdapter<CharSequence>
                if(position==0) {//NADA
                    val adaptador = ArrayAdapter.createFromResource(contexto, R.array.emptyArray, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjercicios.adapter = adaptador
                    consultaPosible = false
                }
                else if(position==1) {//BRAZO
                    val adaptador = ArrayAdapter.createFromResource(contexto, R.array.slEjsBrazo, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjercicios.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==2) {//PIERNA
                    val adaptador = ArrayAdapter.createFromResource(contexto, R.array.slEjsPierna, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjercicios.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==3) {//TORSO
                    val adaptador = ArrayAdapter.createFromResource(contexto, R.array.slEjsTorso, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjercicios.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==4) {//CARDIO
                    val adaptador = ArrayAdapter.createFromResource(contexto, R.array.slEjsCardio, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjercicios.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==5) {//ABDOMEN
                    val adaptador = ArrayAdapter.createFromResource(contexto, R.array.slEjsAbdomen, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjercicios.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==6) {//ESPALDA
                    val adaptador = ArrayAdapter.createFromResource(contexto, R.array.slEjsEspalda, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjercicios.adapter = adaptador
                    consultaPosible = true
                }
            }//onItemSelected()

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Opcional: Puedes hacer algo si no se selecciona nada
            }
        }//onItemSelectedListener()

    }//onCreate


}