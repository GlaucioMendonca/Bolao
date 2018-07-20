package com.example.glaucio.appbolao

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_cadastro_bolao.*
import kotlinx.android.synthetic.main.activity_view_bolao.*

class ViewBolao : AppCompatActivity() {
    private lateinit var ettimecasa : TextView
    private lateinit var ettimefora : TextView
    private lateinit var etvalor : TextView
    private lateinit var apostadordao : ApostadorDao
    private lateinit var btnovopalpite : Button
    private lateinit var btvoltarbolao: Button
    private lateinit var lvApostas: ListView
    var bolaoMaster:Int = 0
    val CADASTRO = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_bolao)

        this.apostadordao = ApostadorDao(this)
        this.lvApostas = findViewById(R.id.lvApostas)
        this.ettimecasa = findViewById(R.id.etTimeCasa)
        this.ettimefora = findViewById(R.id.etTimeFora)
        this.etvalor = findViewById(R.id.etValor)
        this.btnovopalpite = findViewById(R.id.btNovoPalpite)
        this.btvoltarbolao = findViewById(R.id.btCancelarBolao)
        bolaoMaster = intent.getIntExtra("BOLAO",-1)
        this.adapter()
        this.btnovopalpite.setOnClickListener({addApostador(it)})
        this.btvoltarbolao.setOnClickListener({finish()})

    }
    fun adapter (){
        var dados = ArrayList<ApostadorModel>()
        apostadordao.select().forEach({
            if(it.idBolao == bolaoMaster)
                dados.add(it)
        })
        this.lvApostas.adapter = ArrayAdapter<ApostadorModel>(this, android.R.layout.simple_list_item_1, dados)
    }

    fun addApostador (viw:View){
        val it = Intent(this, CadastroActivity::class.java)
        it.putExtra("BOLAOID",bolaoMaster)
        startActivityForResult(it, CADASTRO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CADASTRO){

                val pessoa = data?.getSerializableExtra("PESSOA") as ApostadorModel
                this.apostadordao.insert(pessoa)
                this.adapter()
                Log.i("APP_PESSOA", this.apostadordao.select().toString())
            }
        }
    }

}
