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
    private lateinit var etGolsCasa: EditText
    private lateinit var etGolsFora: EditText
    private lateinit var btResultado: Button
    private lateinit var dao: BolaoDao
    private lateinit var btVencedor: Button

    var bolaoMaster:Int = 0
    val CADASTRO = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_bolao)
        this.bolaoMaster = intent.getIntExtra("BOLAO",-1)
        this.bolaoMaster = this.bolaoMaster -1

        this.btVencedor = findViewById(R.id.btVencedor)
        this.apostadordao = ApostadorDao(this)
        this.lvApostas = findViewById(R.id.lvApostas)
        this.ettimecasa = findViewById(R.id.etTimeCasa)
        this.ettimefora = findViewById(R.id.etTimeFora)
        this.etvalor = findViewById(R.id.etValor)
        this.btnovopalpite = findViewById(R.id.btNovoPalpite)
        this.btvoltarbolao = findViewById(R.id.btCancelarBolao)
        this.etGolsCasa = findViewById(R.id.etBolaoGolsCasa)
        this.etGolsFora = findViewById(R.id.etBolaoGolsFora)
        this.btResultado = findViewById(R.id.btResultado)
        this.dao = BolaoDao(this)

        var bolao1 = dao.select().get(bolaoMaster)

        this.ettimecasa.text = "${bolao1.timeCasa} ${bolao1.golsTimeCasa}"
        this.ettimefora.text = "${bolao1.golsTimeFora} ${bolao1.timeFora}"
        this.etvalor.text = bolao1.valorAposta.toString()



        this.btResultado.setOnClickListener({addResult()})


        this.adapter()
        this.btnovopalpite.setOnClickListener({addApostador(it)})
        this.btvoltarbolao.setOnClickListener({finish()})
        this.lvApostas.setOnItemLongClickListener(OnLongClick())
        this.btVencedor.setOnClickListener({ vencedor() })

    }

    fun vencedor (){
        var bolao3 : Bolao
        val shareIntent = Intent()
        bolao3 = dao.select().get(bolaoMaster)
        var golcasa : Int
        var golfora : Int
        golcasa = bolao3.golsTimeCasa as Int
        golfora = bolao3.golsTimeFora as Int
        if(golcasa > golfora){
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, bolao3.timeCasa)
            shareIntent.type = "text/plain"
        }else if(golcasa < golfora){
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, bolao3.timeFora)
            shareIntent.type = "text/plain"
        }else{
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Empate")
            shareIntent.type = "text/plain"
        }



        startActivity(Intent.createChooser(shareIntent,"send to"))
    }

    fun addResult() {
        var dados = ArrayList<ApostadorModel>()
        var bolao2 : Bolao
        bolao2 = dao.select().get(bolaoMaster)
        val iterator = dao.select().iterator()
        val iteratorAposta = apostadordao.select().iterator()
        bolao2.golsTimeCasa = this.etGolsCasa.text.toString().toInt()
        bolao2.golsTimeFora = this.etGolsFora.text.toString().toInt()
        this.ettimecasa.text = "${bolao2.timeCasa}    ${bolao2.golsTimeCasa}"
        this.ettimefora.text = "${bolao2.golsTimeFora}     ${bolao2.timeFora}"

        dao.update(bolao2)

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

    inner class OnLongClick : AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long): Boolean {
            if (p0 != null) {
                var p = p0.adapter.getItem(p2) as ApostadorModel
                apostadordao.delete(p)
                adapter()
            }
            return true
        }
    }

}
