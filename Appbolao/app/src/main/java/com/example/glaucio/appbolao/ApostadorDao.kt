package com.example.glaucio.appbolao

import android.content.ContentValues
import android.content.Context

class ApostadorDao(var context: Context) {
    private lateinit var banco: BancoHelper

    init {
        this.banco = BancoHelper(context)
    }

    fun insert(pessoa: ApostadorModel){
        val cv = ContentValues()
        cv.put("nome", pessoa.Nome)
        cv.put("golstimedecasa", pessoa.golsTimeCasa)
        cv.put("golstimedefora", pessoa.golsTimeFora)
        cv.put("idBolao", pessoa.idBolao)
        this.banco.writableDatabase.insert("apostador", null, cv)
    }
    fun select() : ArrayList<ApostadorModel>{
        val lista = ArrayList<ApostadorModel>()
        val colunas = arrayOf("id","idBolao","nome","golstimedecasa","golstimedefora")
        val cursor = this.banco.readableDatabase.query("apostador", colunas, null, null, null, null, null)

        cursor.moveToFirst()

        if (cursor.count > 0){
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val nome = cursor.getString(cursor.getColumnIndex("nome"))
                val idBolao = cursor.getInt(cursor.getColumnIndex("idBolao"))
                val golstimedecasa = cursor.getInt(cursor.getColumnIndex("golstimedecasa"))
                val golstimedefora = cursor.getInt(cursor.getColumnIndex("golstimedefora"))

                lista.add(ApostadorModel(nome,golstimedecasa,golstimedefora, idBolao, id))
            }while(cursor.moveToNext())
        }

        return lista
    }
    fun count(): Int{
        val colunas = arrayOf("id")
        val cursor = this.banco.readableDatabase.query("apostador", colunas, null, null, null, null, null)

        return cursor.count
    }
    fun delete(p: ApostadorModel){
        this.banco.writableDatabase.delete("apostador", "id = ?", arrayOf(p.id.toString()))
    }
}