package com.example.gymtracker
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
        companion object{
                private const val NOMBRE_DB = "GIMNASIO"
                private const val VERSION = 1
        }
    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        //db?.setForeignKeyConstraintsEnabled(true)
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("""
                        create table USUARIOS(
                        nombre text primary key autoincrement
                        );
                        """.trimIndent())
        db?.execSQL("""
                        create table GRUPOS(
                        nombre text primary key autoincrement
                        );
                        """.trimIndent())
        db?.execSQL("""
                        create table EJERCICIOS(
                        nombre text primary key autoincrement,
                        nombre_grupo,
                        FOREIGN KEY(nombre_grupo) REFERENCES GRUPOS(nombre) ON DELETE CASCADE);
                        """.trimIndent())
        db?.execSQL("""
                        create table REGISTROS(
                        id int primary key autoincrement,
                        nombre_usuario int,
                        nombre_grupo int,
                        nombre_ejercicio int,
                        mes text,
                        ano text,
                        peso float,
                        FOREIGN KEY(nombre_usuario) REFERENCES USUARIOS(nombre) ON DELETE CASCADE,
                        FOREIGN KEY(nombre_grupo) REFERENCES GRUPOS(nombre) ON DELETE CASCADE,
                        FOREIGN KEY(nombre_ejercicio) REFERENCES EJERCICIOS(nombre) ON DELETE CASCADE);
                        """.trimIndent())

        //INSERTS


        //GRUPOS
        val gruposMusculares = arrayOf(R.array.spGrupos)
        for(grupo in gruposMusculares){
            db?.execSQL("insert into GRUPOS values("+ grupo +");")
        }

        //EJERCICIOS

        //CARDIO
        db?.execSQL("insert into GRUPOS values(\"CINTA\", \"CARDIO\");")
        db?.execSQL("insert into GRUPOS values(\"BICI\", \"CARDIO\");")
        db?.execSQL("insert into GRUPOS values(\"ELIPTICA\", \"CARDIO\");")

        //BRAZO
        db?.execSQL("insert into GRUPOS values(\"CURL EN POLEA\", \"BRAZO\");")
        db?.execSQL("insert into GRUPOS values(\"CURL MARTILLO\", \"BRAZO\");")
        db?.execSQL("insert into GRUPOS values(\"BARRA Z\", \"BRAZO\");")
        db?.execSQL("insert into GRUPOS values(\"CURL EN BANCO INCLINADO\", \"BRAZO\");")
        db?.execSQL("insert into GRUPOS values(\"FONDOS DE TRICEPS\", \"BRAZO\");")
        db?.execSQL("insert into GRUPOS values(\"ELEVACION TRAS NUCA CON MANCUERNAS\", \"BRAZO\");")
        db?.execSQL("insert into GRUPOS values(\"EXTENSION TRAS NUCA EN POLEA\", \"BRAZO\");")
        db?.execSQL("insert into GRUPOS values(\"EXTENSION BAJA EN POLEA\", \"BRAZO\");")
        db?.execSQL("insert into GRUPOS values(\"ELEVACION TRAS NUCA EN POLEA\", \"BRAZO\");")

        //PIERNA
        db?.execSQL("insert into GRUPOS values(\"ADUCTOR\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"ABDUCTOR\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"EXTENSION CUADRICEPS\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"FLEXION FEMORAL SENTADO\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"FLEXION FEMORAL TUMBADO\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"SENTADILLA\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"SENTADILLA BULGARA\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"SENTADILLA HACK\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"GEMELO\", \"PIERNA\");")
        db?.execSQL("insert into GRUPOS values(\"PRENSA\", \"PIERNA\");")

        //TORSO
        db?.execSQL("insert into GRUPOS values(\"PRESS BANCO PLANO\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"PRESS BANCO INCLINADO\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"PRESS BANCO INCLINADO CON MANCUERNAS\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"PRESS EN MAQUINA\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"MARIPOSA\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"POLEA ASCENDENTE\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"POLEA DESCENDENTE\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"POLEA NEUTRA\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"PRESS MILITAR MANCUERNAS\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"PRESS MILITAR BARRA\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"PRESS ARNOLD\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"ELEVACIONES FRONTALES\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"ELEVACIONES LATERALES MANCUERNA\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"ELEVACIONES LATERALES POLEA\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"ELEVACIONES POSTERIORES\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"ENCOGIMIENTO DE TRAPECIO\", \"TORSO\");")
        db?.execSQL("insert into GRUPOS values(\"FACE PULL\", \"TORSO\");")

        //ESPALDA
        db?.execSQL("insert into GRUPOS values(\"REMO GIRONDA\", \"ESPALDA\");")
        db?.execSQL("insert into GRUPOS values(\"REMO MAQUINA\", \"ESPALDA\");")
        db?.execSQL("insert into GRUPOS values(\"REMO MANCUERNAS\", \"ESPALDA\");")
        db?.execSQL("insert into GRUPOS values(\"REMO BARRA\", \"ESPALDA\");")
        db?.execSQL("insert into GRUPOS values(\"REMO BARRA T\", \"ESPALDA\");")
        db?.execSQL("insert into GRUPOS values(\"JALON POLEA\", \"ESPALDA\");")
        db?.execSQL("insert into GRUPOS values(\"JALON MAQUINA\", \"ESPALDA\");")

        //ABDOMEN
        db?.execSQL("insert into GRUPOS values(\"CRUNCH\", \"ABDOMEN\");")
        db?.execSQL("insert into GRUPOS values(\"CRUNCH PIERNAS FLEXIONADAS\", \"ABDOMEN\");")
        db?.execSQL("insert into GRUPOS values(\"ELEVACION DE PIERNAS\", \"ABDOMEN\");")
        db?.execSQL("insert into GRUPOS values(\"PLANCHA\", \"ABDOMEN\");")

    }//onCreate


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}