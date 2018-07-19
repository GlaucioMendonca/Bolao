package com.example.glaucio.appbolao


import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Glaucio on 18/06/2018.
 */
class Bolao (var timeCasa:String, var timeFora:String, var data:Date, var valorAposta:Double, var golsTimeCasa:Int?, var golsTimeFora:Int?):Serializable{
    private lateinit var apostadores: ArrayList<>




    override fun toString(): String {

        return "${this.timeCasa} X ${this.timeFora} - [${this.data}]"
    }
}