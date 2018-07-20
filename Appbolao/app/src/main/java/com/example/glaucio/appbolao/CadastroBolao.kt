package com.example.glaucio.appbolao

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_cadastro_bolao.*
import java.util.*

class CadastroBolao : AppCompatActivity() {

    private lateinit var timeCasa: EditText
    private lateinit var timeFora: EditText
    private lateinit var etValor: EditText
    private lateinit var btCancelar: Button
    private lateinit var btSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_bolao)
        setSupportActionBar(toolbar)

        this.timeCasa = findViewById(R.id.etTimeCasa)
        this.timeFora = findViewById(R.id.etTimeFora)
        this.etValor = findViewById(R.id.etValorAposta)
        this.btCancelar = findViewById(R.id.btCancelar)
        this.btSalvar = findViewById(R.id.btSalvar)

        this.btSalvar.setOnClickListener ({ salvar(it)})
        this.btCancelar.setOnClickListener ({ cancelar(it)})
    }

    fun salvar (view:View){
        val casa = this.timeCasa.text.toString()
        val fora = this.timeFora.text.toString()
        val valor = this.etValor.text.toString().toDouble()
        val bolao = Bolao(casa,fora,valor, null, null)


    }

    fun cancelar (view:View){

    }

}
