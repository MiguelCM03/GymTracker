package com.example.gymtracker
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context): SQLiteOpenHelper(context, NOMBRE_DB, null, VERSION) {
    private val wicContext: Context = context
        companion object{
                private const val NOMBRE_DB = "PRUEBA_GYM3"
                private const val VERSION = 2
                const val NOMBRE_TABLA = "USUARIOS"
        }
        override fun onConfigure(db: SQLiteDatabase?) {
                super.onConfigure(db)
                db?.setForeignKeyConstraintsEnabled(true)
        }
        override fun onCreate(db: SQLiteDatabase?) {
            //CREACION TABLAS
            // USUARIOS
            var crearTablaUsuarios = "CREATE TABLE USUARIOS(nombre text primary key)"
            db?.execSQL(crearTablaUsuarios)

            //GRUPOS
            var crearTablaGrupos = """
                    create table GRUPOS(
                    nombre text primary key
                    );
                    """.trimIndent()
            db?.execSQL(crearTablaGrupos)

            //EJERCICIOS
            var crearTablaEjercicios = """
                    create table EJERCICIOS(
                    nombre text primary key,
                    nombre_grupo text,
                    FOREIGN KEY(nombre_grupo) REFERENCES GRUPOS(nombre) ON DELETE CASCADE
                    )""".trimIndent()
            db?.execSQL(crearTablaEjercicios)

            //REGISTROS
            var crearTablaRegistros = """
                    create table REGISTROS(
                    nombre_usuario int,
                    nombre_ejercicio int,
                    mes text,
                    ano integer,
                    peso int, --real
                    FOREIGN KEY(nombre_usuario) REFERENCES USUARIOS(nombre) ON DELETE CASCADE,
                    FOREIGN KEY(nombre_ejercicio) REFERENCES EJERCICIOS(nombre) ON DELETE CASCADE)
                    """.trimIndent()
            db?.execSQL(crearTablaRegistros)



            //INSERCIONES DE DATOS

            //GRUPOS
            val gruposInsertar = wicContext.resources?.getStringArray(R.array.spGruposInsertar)
            if (gruposInsertar != null) {
                for(grupo in gruposInsertar){
                    db?.execSQL("INSERT INTO GRUPOS VALUES('" + grupo + "')")
                }
            }



            //EJERCICIOS

            //CARDIO
            val ejerciciosCardioInsertar = wicContext.resources?.getStringArray(R.array.slEjsCardio)
            if (ejerciciosCardioInsertar != null) {
                for(ejercicio in ejerciciosCardioInsertar){
                    db?.execSQL("INSERT INTO EJERCICIOS VALUES('" + ejercicio + "', 'CARDIO')")
                }
            }

            //BRAZO
            val ejerciciosBrazoInsertar = wicContext.resources?.getStringArray(R.array.slEjsBrazo)
            if (ejerciciosBrazoInsertar != null) {
                for(ejercicio in ejerciciosBrazoInsertar){
                    db?.execSQL("INSERT INTO EJERCICIOS VALUES('" + ejercicio + "', 'BRAZO')")
                }
            }

            //PIERNA
            val ejerciciosPiernaInsertar = wicContext.resources?.getStringArray(R.array.slEjsPierna)
            if (ejerciciosPiernaInsertar != null) {
                for(ejercicio in ejerciciosPiernaInsertar){
                    db?.execSQL("INSERT INTO EJERCICIOS VALUES('" + ejercicio + "', 'PIERNA')")
                }
            }

            //TORSO
            val ejerciciosTorsoInsertar = wicContext.resources?.getStringArray(R.array.slEjsTorso)
            if (ejerciciosTorsoInsertar != null) {
                for(ejercicio in ejerciciosTorsoInsertar){
                    db?.execSQL("INSERT INTO EJERCICIOS VALUES('" + ejercicio + "', 'TORSO')")
                }
            }

            //ESPALDA
            val ejerciciosEspaldaInsertar = wicContext.resources?.getStringArray(R.array.slEjsEspalda)
            if (ejerciciosEspaldaInsertar != null) {
                for(ejercicio in ejerciciosEspaldaInsertar){
                    db?.execSQL("INSERT INTO EJERCICIOS VALUES('" + ejercicio + "', 'ESPALDA')")
                }
            }

            //ABDOMEN
            val ejerciciosAbdomenInsertar = wicContext.resources?.getStringArray(R.array.slEjsAbdomen)
            if (ejerciciosAbdomenInsertar != null) {
                for(ejercicio in ejerciciosAbdomenInsertar){
                    db?.execSQL("INSERT INTO EJERCICIOS VALUES('" + ejercicio + "', 'ABDOMEN')")
                }
            }

            try {
                db?.execSQL("INSERT INTO USUARIOS VALUES('USUARIO_PRUEBA')")
                db?.execSQL("INSERT INTO REGISTROS VALUES('USUARIO_PRUEBA', 'PRENSA', 'ABRIL', 2022, 100)")
            }catch(e: Exception){
                e.printStackTrace()
            }

            //FIN INSERTS

        }//onCreate
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            //REGISTROS
            db?.execSQL("DROP TABLE IF EXISTS REGISTROS")
            var crearTablaRegistros = """
                    create table REGISTROS(
                    nombre_usuario int,
                    nombre_ejercicio int,
                    mes text,
                    ano text,
                    peso text,
                    FOREIGN KEY(nombre_usuario) REFERENCES USUARIOS(nombre) ON DELETE CASCADE,
                    FOREIGN KEY(nombre_ejercicio) REFERENCES EJERCICIOS(nombre) ON DELETE CASCADE)
                    """.trimIndent()
            db?.execSQL(crearTablaRegistros)
        }//onUpgrade()

        fun insertarRegistro(db: SQLiteDatabase, nombreUsuario: String, nombreEjercicio: String, peso: Float, ano: Int, mes: String){
                val valoresGrupo = ContentValues()
                valoresGrupo.put("NOMBRE", "Grupo de prueba")

                val valoresEjercicio = ContentValues()
                valoresEjercicio.put("NOMBRE", "Ejercicio de prueba")
                valoresEjercicio.put("NOMBRE_GRUPO", "Grupo de prueba")

                val valores = ContentValues()
                valores.put("nombre_usuario", nombreUsuario)
                valores.put("nombre_ejercicio", nombreEjercicio)
                valores.put("peso", peso)
                valores.put("ano", ano)
                valores.put("mes", mes)
                db.insert("REGISTROS", null, valores)
        }


}