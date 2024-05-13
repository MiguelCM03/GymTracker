package com.example.gymtracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    var txtCorreo: TextInputEditText? = null
    var txtContrasena: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val fbAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        fbAnalytics.logEvent("Pantalla_de_inicio", bundle)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtContrasena = findViewById(R.id.txtContrasena)
        txtCorreo = findViewById(R.id.txtCorreo)
        val txtCrearCuenta = findViewById<TextView>(R.id.txtCrearCuenta)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        txtCrearCuenta.setOnClickListener{clickCrearCuenta()}
        btnIniciarSesion.setOnClickListener{clickIniciarSesion()}
    }

    fun clickCrearCuenta(){
        val intentCrearCuenta = Intent(this, CrearCuentaActivity::class.java)
        startActivity(intentCrearCuenta)
    }
    fun clickIniciarSesion(){
        validarCuenta()
    }

    fun validarCuenta(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            txtCorreo?.text.toString(),
            txtContrasena?.text.toString()
        ).addOnCompleteListener { it ->
            if (it.isSuccessful) {
                val intentValidar = Intent(this, SeleccionAccionActivity::class.java)
                startActivity(intentValidar)
            } else {
                Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG).show()
            }
        }//On complete listener para saber si lo hace correctamente
    }//validarCuenta()
}