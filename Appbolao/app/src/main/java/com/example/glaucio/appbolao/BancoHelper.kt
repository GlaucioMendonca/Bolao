package com.example.glaucio.appbolao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by ifpb on 18/05/18.
 */

val VERSAO = 1

class BancoHelper(context: Context?) :
        SQLiteOpenHelper(context, "pessoa.sqlite", null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql1 = "create table apostador (" +
                "id integer primary key autoincrement, " +
                "idBolao integer , " +
                "nome string, " +
                "golstimedecasa integer " +
                "golstimedefora integer " +
                ")"
        val sql2 = "create table bolao (" +
                "id integer primary key autoincrement, " +
                "timedecasa string, " +
                "timedefora string, " +
                "valoraposta integer " +
                "golstimedecasa integer " +
                "golstimedefora integer " +
                ")"
        db?.execSQL(sql1)
        db?.execSQL(sql2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, antes: Int, nova: Int) {

    }

}