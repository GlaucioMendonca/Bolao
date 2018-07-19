package com.example.glaucio.appbolao

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    private lateinit var etNome : EditText
    private lateinit var btCadastrar : Button
    private lateinit var btCancelar : Button
    private lateinit var btGolsTimeDeCasa : EditText
    private lateinit var btGolsTimeDeFora : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        setSupportActionBar(toolbar)

        this.etNome = findViewById(R.id.etNome)
        this.btCadastrar = findViewById(R.id.btCadastrar)
        this.btCancelar = findViewById(R.id.btCancelar)
        this.btGolsTimeDeCasa = findViewById(R.id.btGolsTimeDeCasa)
        this.btGolsTimeDeFora = findViewById(R.id.btGolsTimeDeFora)

        this.btCadastrar.setOnClickListener({ salvar(it) })
        this.btCancelar.setOnClickListener({ cancelar(it) })
    }
    fun salvar(view: View){
        val nome = this.etNome.text.toString()
        val golstimedecasa = this.btGolsTimeDeCasa.text.toString()
        val golstimedefora = this.btGolsTimeDeFora.text.toString()
        val pessoa = ApostadorModel(nome,golstimedecasa.toInt(),golstimedefora.toInt())

        var it = Intent()
        it.putExtra("PESSOA", pessoa)
        setResult(Activity.RESULT_OK, it)
        finish()
    }

    fun cancelar(view: View){
        finish()
    }

}
