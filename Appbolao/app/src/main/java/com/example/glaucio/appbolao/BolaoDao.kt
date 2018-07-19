package com.example.glaucio.appbolao

import android.content.ContentValues
import android.content.Context

class BolaoDao(var context: Context)  {
    private lateinit var banco: BancoHelper

    init {
        this.banco = BancoHelper(context)
    }

    fun insert(bolao: Bolao){
        val cv = ContentValues()
        cv.put("timedecasa", bolao.timeCasa)
        cv.put("timedefora", bolao.timeFora)
        cv.put("valoraposta", bolao.valorAposta)
        cv.put("golstimedecasa", bolao.golsTimeCasa)
        cv.put("golstimedefora", bolao.golsTimeFora)
        this.banco.writableDatabase.insert("bolao", null, cv)
    }
    fun select() : ArrayList<Bolao>{
        val lista = ArrayList<Bolao>()
        val colunas = arrayOf("id", "timedecasa", "timedefora","valoraposta","golstimedecasa","golstimedefora")
        val cursor1 = this.banco.readableDatabase.query("bolao", colunas, null, null, null, null, null)

        cursor1.moveToFirst()

        if (cursor1.count > 0){
            do {
                val id = cursor1.getInt(cursor1.getColumnIndex("id"))
                val timedecasa = cursor1.getString(cursor1.getColumnIndex("timedecasa"))
                val timedefora = cursor1.getString(cursor1.getColumnIndex("timedefora"))
                val valoraposta = cursor1.getString(cursor1.getColumnIndex("valoraposta"))
                val golstimedecasa = cursor1.getString(cursor1.getColumnIndex("golstimedecasa"))
                val golstimedefora = cursor1.getString(cursor1.getColumnIndex("golstimedefora"))
                val bolao = Bolao(timedecasa,timedefora,valoraposta.toDouble(),golstimedecasa.toInt(),golstimedefora.toInt(), id)

                val colunas2 = arrayOf("id","idBolao","golstimedecasa","golstimedefora")
                val cursor2 = this.banco.readableDatabase.query("apostador", colunas2, null, null, null, null, null)
                cursor2.moveToFirst()

                if(cursor2.count > 0){
                    do{
                        val idBolao = cursor2.getInt(cursor1.getColumnIndex("idBolao"))
                        if(idBolao == id){
                            val golstimedecasa1 = cursor2.getInt(cursor2.getColumnIndex("golstimedecasa"))
                            val golstimedefora1 = cursor2.getInt(cursor2.getColumnIndex("golstimedefora"))
                            val nome = cursor2.getString(cursor2.getColumnIndex("nome"))
                            val apostador = ApostadorModel(nome,golstimedecasa1,golstimedefora1,idBolao,id)
                            bolao.insertApostador(apostador)
                        }
                    }while (cursor2.moveToNext())

                }

                lista.add(bolao)
            }while(cursor1.moveToNext())
        }

        return lista
    }

    fun delete(b: Bolao){
        this.banco.writableDatabase.delete("bolao", "id = ?", arrayOf(b.id.toString()))
    }
}