package com.example.glaucio.appbolao

import java.io.Serializable
import java.util.*

class ApostadorModel( var Nome:String,var golsTimeCasa:Int?, var golsTimeFora:Int?, var idBolao: Int = -1, var id: Int = -1): Serializable {
//    private lateinit var bolao: ArrayList<Bolao>

    override fun toString(): String {
        return "${this.Nome}: ${golsTimeCasa} x ${golsTimeFora}"
    }

//    fun insertBolao(aposta: Bolao){
//        this.bolao.add(aposta)
//    }


}