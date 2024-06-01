package com.example.gymtracker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConsultarDatosActivity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consultar_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val contextoConsulta = this.baseContext

        val spGruposConsulta = findViewById<Spinner>(R.id.spGruposConsultar)
        val spEjerciciosConsulta = findViewById<Spinner>(R.id.spEjerciciosConsultar)
        val spAnosConsulta = findViewById<Spinner>(R.id.spAnosConsultar)
        val spMesesConsulta = findViewById<Spinner>(R.id.spMesesConsultar)
        val btnConfirmarConsulta = findViewById<Button>(R.id.btnConfirmarAccionConsultar)
        var consultaPosible = false

        btnConfirmarConsulta.setOnClickListener {
            if (consultaPosible) {
                val intentDatosDeConsulta = Intent(this, DatosDeConsultaActivity::class.java)

                var valorAnoConsultado = spAnosConsulta.selectedItem.toString()
                var valorMesConsultado = spMesesConsulta.selectedItem.toString()
                var valorEjercicioConsultado = spEjerciciosConsulta.selectedItem.toString()


                var dbHelperConsulta = DatabaseHelper(this)
                var baseDeDatosConsulta = dbHelperConsulta.readableDatabase
                var usuarioConsulta = intent.getStringExtra("USUARIO")

                var consultaBuena = "SELECT PESO FROM REGISTROS WHERE upper(nombre_usuario) like upper(?) and upper(nombre_ejercicio) like upper(?) and upper(mes) like upper(?) and upper (ano) like upper(?)"
                var parametrosConsulta = arrayOf(usuarioConsulta, valorEjercicioConsultado, valorMesConsultado, valorAnoConsultado)
                val cursor = baseDeDatosConsulta.rawQuery(consultaBuena, parametrosConsulta)

                var pesos: MutableList<String> = mutableListOf()
                try {
                    while (cursor.moveToNext()) {
//                        pesoConsulta = cursor.getString(0)
                        pesos.add(cursor.getString(0))
                    }
                    cursor.close()
                }catch (e: Exception){}

                if(pesos.equals("")){
                    val alertaNoHayDatos = AlertDialog.Builder(this)
                    alertaNoHayDatos.setTitle("Aviso")
                    alertaNoHayDatos.setMessage("No hay datos para esa búsqueda")
                    alertaNoHayDatos.setPositiveButton("OK", null)
                    val dialogoNoHayDatos: AlertDialog = alertaNoHayDatos.create()
                    dialogoNoHayDatos.show()
                }else{
                    val pesosConsultados = pesos.toTypedArray()
                    val arrayPrueba = arrayOf("2", "3", "44", "55")
                    intentDatosDeConsulta.putExtra("ANO", valorAnoConsultado)
                    intentDatosDeConsulta.putExtra("MES", valorMesConsultado)
                    intentDatosDeConsulta.putExtra("EJERCICIO", valorEjercicioConsultado)

                    intentDatosDeConsulta.putExtra("PESO", pesosConsultados)

                    startActivity(intentDatosDeConsulta)
                }

            }else{
                val alertaEjercicioNoSeleccionado = AlertDialog.Builder(this)
                alertaEjercicioNoSeleccionado.setTitle("Error")
                alertaEjercicioNoSeleccionado.setMessage("Selecciona un grupo muscular y un ejercicio para continuar")
                alertaEjercicioNoSeleccionado.setPositiveButton("OK", null)
                val dialogoEjercicioNoSeleccionado: AlertDialog = alertaEjercicioNoSeleccionado.create()
                dialogoEjercicioNoSeleccionado.show()
            }
        }


        spGruposConsulta.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if(position==0) {//NADA
                    val adaptador = ArrayAdapter.createFromResource(contextoConsulta, R.array.emptyArray, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosConsulta.adapter = adaptador
                    consultaPosible = false
                }
                else if(position==1) {//BRAZO
                    val adaptador = ArrayAdapter.createFromResource(contextoConsulta, R.array.slEjsBrazo, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosConsulta.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==2) {//PIERNA
                    val adaptador = ArrayAdapter.createFromResource(contextoConsulta, R.array.slEjsPierna, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosConsulta.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==3) {//TORSO
                    val adaptador = ArrayAdapter.createFromResource(contextoConsulta, R.array.slEjsTorso, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosConsulta.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==4) {//CARDIO
                    val adaptador = ArrayAdapter.createFromResource(contextoConsulta, R.array.slEjsCardio, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosConsulta.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==5) {//ABDOMEN
                    val adaptador = ArrayAdapter.createFromResource(contextoConsulta, R.array.slEjsAbdomen, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosConsulta.adapter = adaptador
                    consultaPosible = true
                }
                else if(position==6) {//ESPALDA
                    val adaptador = ArrayAdapter.createFromResource(contextoConsulta, R.array.slEjsEspalda, android.R.layout.simple_spinner_item)
                    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEjerciciosConsulta.adapter = adaptador
                    consultaPosible = true
                }
            }//onItemSelected()

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Opcional: Puedes hacer algo si no se selecciona nada
            }
        }//onItemSelectedListener()

    }//onCreate


}