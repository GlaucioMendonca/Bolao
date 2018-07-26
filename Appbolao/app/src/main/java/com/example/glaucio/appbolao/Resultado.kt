package com.example.glaucio.appbolao

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class Resultado : AppCompatActivity() {
    private lateinit var etGolCasa: EditText
    private lateinit var etGolFora: EditText
    private lateinit var btSalvar: Button
    private lateinit var btCancel: Button

    private lateinit var dao: BolaoDao

    private var IdBolao = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        Log.i("APPBOLAO", "RESULTADO")
        this.btCancel = findViewById(R.id.btResultCancel)
        this.btSalvar = findViewById(R.id.btResultSave)
        this.etGolCasa = findViewById(R.id.etResultGolCasa)
        this.etGolFora = findViewById(R.id.etResultGolFora)
        this.dao = BolaoDao(this)


        this.btSalvar.setOnClickListener { salvar(it) }
        this.btCancel.setOnClickListener { finish() }
    }

    fun salvar (view:View){

        this.IdBolao = intent.getIntExtra("BOLAOID",-1)

        var bolao = dao.select().get(IdBolao)

        bolao.golsTimeCasa = this.etGolCasa.text.toString().toInt()
        bolao.golsTimeFora = this.etGolFora.text.toString().toInt()

        dao.update(bolao)

        var it = Intent()
        setResult(Activity.RESULT_OK, it)
        finish()
    }
}
