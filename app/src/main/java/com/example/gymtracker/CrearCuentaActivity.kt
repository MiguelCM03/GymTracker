package com.example.gymtracker

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

var txtCorreoNuevo: TextInputEditText? = null
var txtContrasenaNueva: TextInputEditText? = null
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

        txtContrasenaNueva = findViewById(R.id.txtContrasenaCrearCuenta)
        txtCorreoNuevo = findViewById(R.id.txtCorreoCrearCuenta)
        var btnConfirmar = findViewById<Button>(R.id.btnConfirmarAccion)
        btnConfirmar.setOnClickListener{
            /*if(*/
            validarCorreo()
            crearCuenta()}

    }//onCreate()

    fun crearCuenta(){
        if(validarContrasena(txtContrasenaNueva?.text.toString())) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                txtCorreoNuevo?.text.toString(),
                txtContrasenaNueva?.text.toString()
            ).addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_LONG).show()
                    var intentIniciarSesionCuentaCreada = Intent(this, SeleccionAccionActivity::class.java)
                    startActivity(intentIniciarSesionCuentaCreada)
                } else {
                    Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_LONG).show()
                }
            }//On complete listener para saber si lo hace correctamente
        }
        insertarEnBBDD()
    }//crearCuenta()

    fun insertarEnBBDD(){
        var txtNombre = txtCorreoNuevo?.text.toString()
        var registro = ContentValues()
        registro.put("nombre",txtNombre) //registro.put(nombre del campo en bbdd, valor)
        var con = DatabaseHelper(this,"gimnasio", null, 1)
        var baseDatos = con.writableDatabase
        baseDatos.insert("clientes", null, registro)
    }

    fun validarContrasena(isContrasena: String):Boolean{
        var tieneMinus = false
        var tieneMayus = false
        var tieneNums = false
        var tieneSignos = false
        var contrasenaValida = false
        var longitudCorrecta = true
        if(txtContrasenaNueva?.text.toString().length < 6) { longitudCorrecta = false } //La contraseña es demasiado corta(FireBase necesita al menos 6 caracteres)
        for(i in isContrasena){
            if(i > 64.toChar() && i < 91.toChar())        tieneMayus  = true //La contraseña tiene al menos una mayúscula
            else if(i > 96.toChar() && i < 123.toChar())  tieneMinus  = true //La contraseña tiene al menos una minúscula
            else if(i > 47.toChar() && i < 58.toChar())   tieneNums   = true //La contraseña tiene al menos un número
            else                                          tieneSignos = true //La contraseña tiene al menos un carácter especial
        }


        if(!tieneMinus)            { mostrarError("La contraseña debe tener al menos 1 minúscula") }
        else if(!tieneMayus)       { mostrarError("La contraseña debe tener al menos 1 mayúscula") }
        else if(!tieneNums)        { mostrarError("La contraseña debe tener al menos 1 número") }
        else if(!tieneSignos)      { mostrarError("La contraseña debe tener al menos 1 carácter especial") }
        else if(!longitudCorrecta) { mostrarError("La contraseña debe tener al menos 6 caracteres") }

        if(tieneMayus && tieneMinus && tieneNums && tieneSignos && longitudCorrecta) contrasenaValida = true
        return contrasenaValida
    }//validarContrasena

    fun mostrarError(isMensaje: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(isMensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }//mostrarError()

    fun validarCorreo()/*:Boolean*/{
        if(txtCorreoNuevo?.text.toString().isEmpty()){
            val alertaCorreoVacio = AlertDialog.Builder(this)
            alertaCorreoVacio.setTitle("Error")
            alertaCorreoVacio.setMessage("El correo no puede estar vacío")
            alertaCorreoVacio.setPositiveButton("Aceptar", null)
            val dialogoCorreoVacio: AlertDialog = alertaCorreoVacio.create()
            dialogoCorreoVacio.show()
        }
        //return correoCorrecto
    }//validarCorreo


}//CrearCuentaActivity