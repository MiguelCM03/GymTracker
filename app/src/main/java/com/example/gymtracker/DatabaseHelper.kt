package com.example.gymtracker
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context?): SQLiteOpenHelper(context, NOMBRE_DB, null, VERSION) {
        companion object{
                private const val NOMBRE_DB = "GIMNASIO"
                private const val VERSION = 1
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
                        nombre_grupo,
                        FOREIGN KEY(nombre_grupo) REFERENCES GRUPOS(nombre) ON DELETE CASCADE)
                        """.trimIndent()
                db?.execSQL(crearTablaEjercicios)

                //REGISTROS
                var crearTablaRegistros = """
                        create table REGISTROS(
                        id int primary key autoincrement,
                        nombre_usuario int,
                        nombre_ejercicio int,
                        mes text,
                        ano integer,
                        peso float,
                        FOREIGN KEY(nombre_usuario) REFERENCES USUARIOS(nombre) ON DELETE CASCADE,
                        FOREIGN KEY(nombre_ejercicio) REFERENCES EJERCICIOS(nombre) ON DELETE CASCADE)
                        """.trimIndent()
                db?.execSQL(crearTablaRegistros)


                //INSERCIONES DE DATOS

                //GRUPOS
                val gruposMusculares = arrayOf(R.array.spGrupos)
                for(grupo in gruposMusculares){
                        db?.execSQL("insert into GRUPOS values("+ grupo +");")
                }

                //EJERCICIOS

                //CARDIO
                val ejerciciosCardio = arrayOf(R.array.spGrupos)
                for(ejercicio in ejerciciosCardio){
                        db?.execSQL("insert into GRUPOS values("+ ejercicio +", \"CARDIO\");")
                }

                //BRAZO
                val ejerciciosBrazo = arrayOf(R.array.spGrupos)
                for(ejercicio in ejerciciosBrazo){
                        db?.execSQL("insert into GRUPOS values("+ ejercicio +", \"BRAZO\");")
                }

                //PIERNA
                val ejerciciosPierna = arrayOf(R.array.spGrupos)
                for(ejercicio in ejerciciosPierna){
                        db?.execSQL("insert into GRUPOS values("+ ejercicio +", \"PIERNA\");")
                }

                //TORSO
                val ejerciciosTorso = arrayOf(R.array.spGrupos)
                for(ejercicio in ejerciciosTorso){
                        db?.execSQL("insert into GRUPOS values("+ ejercicio +", \"TORSO\");")
                }

                //ESPALDA
                val ejerciciosEspalda = arrayOf(R.array.spGrupos)
                for(ejercicio in ejerciciosEspalda){
                        db?.execSQL("insert into GRUPOS values("+ ejercicio +", \"ESPALDA\");")
                }

                //ABDOMEN
                val ejerciciosAbdomen = arrayOf(R.array.spGrupos)
                for(ejercicio in ejerciciosAbdomen){
                        db?.execSQL("insert into GRUPOS values("+ ejercicio +", \"ABDOMEN\");")
                }
        }//onCreate
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }

        fun insertarUsuario(db: SQLiteDatabase, nombre: String):Long{
                val valores = ContentValues()
                valores.put("NOMBRE", nombre)
                return db.insert(NOMBRE_TABLA, null, valores)
        }//insertarUsuario

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