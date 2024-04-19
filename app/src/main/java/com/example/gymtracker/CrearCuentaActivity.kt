package com.example.gymtracker

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

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
        val btnConfirmar = findViewById<Button>(R.id.btnCrearCuenta)
        btnConfirmar.setOnClickListener{crearCuenta()}

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
            baseDatos.execSQL("SELECT * from USERS where UPPER(nombre) like UPPER()")
            baseDatos.insert("USERS", null, registro)
            txtContrasena?.setText("")
            txtUsuario?.setText("")
            Toast.makeText(this, "Registro añadido con exito", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Campos vacíos", Toast.LENGTH_LONG).show()
        }

    }//insertarDatos()

    fun crearCuenta(){
        if(validarContrasena(txtContrasena?.text.toString())) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                txtUsuario?.text.toString(),
                txtContrasena?.text.toString()
            ).addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_LONG).show()
                }
            }//On complete listener para saber si lo hace correctamente
        }
    }

    fun validarContrasena(isContrasena: String):Boolean{
        var tieneMinus = false
        var tieneMayus = false
        var tieneNums = false
        var tieneSignos = false
        var contrasenaValida = false
        for(i in isContrasena){
            if(i > 64.toChar() && i < 91.toChar())        tieneMayus  = true //La contraseña tiene al menos una mayúscula
            else if(i > 96.toChar() && i < 123.toChar())  tieneMinus  = true //La contraseña tiene al menos una minúscula
            else if(i > 47.toChar() && i < 58.toChar())   tieneNums   = true //La contraseña tiene al menos un número
            else                                          tieneSignos = true //La contraseña tiene al menos un carácter especial
        }
        if(tieneMayus && tieneMinus && tieneNums && tieneSignos) contrasenaValida = true
        return contrasenaValida
    }

}//CrearCuentaActivity