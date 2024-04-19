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
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

var txtCorreo: EditText? = null
var txtContrasena: TextInputEditText? = null
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

        txtCorreo = findViewById(R.id.txtCorreo)
        txtContrasena = findViewById(R.id.txtContrasena)
        var btnConfirmar = findViewById<Button>(R.id.btnCrearCuenta)
        btnConfirmar.setOnClickListener{crearCuenta()}
    }

    //Funcion actualmente inutil
    fun insertarDatos(view: View){
        var conn = SQLite(this, "Clientes", null, 1)
        var baseDatos = conn.writableDatabase
        var wsNombre = txtCorreo?.text.toString()
        var wsContrasena = txtContrasena?.text.toString()
        if(!wsNombre.isEmpty() && !wsContrasena.isEmpty()){
            var registro = ContentValues()
            registro.put("wsNombre", wsNombre)
            registro.put("wsContrasena", wsContrasena)
            baseDatos.execSQL("SELECT * from USERS where UPPER(nombre) like UPPER()")
            baseDatos.insert("USERS", null, registro)
            txtContrasena?.setText("")
            txtCorreo?.setText("")
            Toast.makeText(this, "Registro añadido con exito", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Campos vacíos", Toast.LENGTH_LONG).show()
        }

    }//insertarDatos()

    fun crearCuenta(){
        if(validarContrasena(txtContrasena?.text.toString())) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                txtCorreo?.text.toString(),
                txtContrasena?.text.toString()
            ).addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_LONG).show()
                }
            }//On complete listener para saber si lo hace correctamente
        }
    }//crearCuenta()

    fun validarContrasena(isContrasena: String):Boolean{
        var tieneMinus = false
        var tieneMayus = false
        var tieneNums = false
        var tieneSignos = false
        var contrasenaValida = false
        var longitudCorrecta = true
        if(txtContrasena?.text.toString().length < 6) { longitudCorrecta = false } //La contraseña es demasiado corta(FireBase necesita al menos 6 caracteres)
        for(i in isContrasena){
            if(i > 64.toChar() && i < 91.toChar())        tieneMayus  = true //La contraseña tiene al menos una mayúscula
            else if(i > 96.toChar() && i < 123.toChar())  tieneMinus  = true //La contraseña tiene al menos una minúscula
            else if(i > 47.toChar() && i < 58.toChar())   tieneNums   = true //La contraseña tiene al menos un número
            else                                          tieneSignos = true //La contraseña tiene al menos un carácter especial
        }
        if(!tieneMinus)       { Toast.makeText(this, "La contraseña debe tener al menos 1 minúscula",         Toast.LENGTH_LONG).show() }
        else if(!tieneMayus)       { Toast.makeText(this, "La contraseña debe tener al menos 1 mayúscula",         Toast.LENGTH_LONG).show() }
        else if(!tieneNums)        { Toast.makeText(this, "La contraseña debe tener al menos 1 número",            Toast.LENGTH_LONG).show() }
        else if(!tieneSignos)      { Toast.makeText(this, "La contraseña debe tener al menos 1 carácter especial", Toast.LENGTH_LONG).show() }
        else if(!longitudCorrecta) { Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres",        Toast.LENGTH_LONG).show() }




        if(tieneMayus && tieneMinus && tieneNums && tieneSignos && longitudCorrecta) contrasenaValida = true
        return contrasenaValida
    }//validarContrasena



}//CrearCuentaActivity