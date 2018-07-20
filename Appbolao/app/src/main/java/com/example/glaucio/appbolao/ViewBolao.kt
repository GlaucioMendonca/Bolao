package com.example.glaucio.appbolao

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class ViewBolao : AppCompatActivity() {
    private lateinit var btPalpite: Button
    val CADASTRO = 1

    private lateinit var bolao: Bolao
    private lateinit var lv: ListView
    private lateinit var apostadordao: ApostadorDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_bolao)

        this.btPalpite = findViewById(R.id.btPalpite)
        this.btPalpite.setOnClickListener({addApostador(it)})
    }

    fun adapter (){
        var dados = ArrayList<Bolao>()

        var b1 = Bolao("Brasil", "Mexico", 5.00, null,null)
        var b2 = Bolao("China", "Africa",5.00, null,null)

        dados.add(b1)
        dados.add(b2)

        Log.e("bolao", "entrei no adapter")
        Log.e("bolao", dados[0].toString())

        this.lv.adapter = ArrayAdapter<Bolao>(this, android.R.layout.simple_list_item_1, dados)
    }

    fun addApostador (viw:View){
        val it = Intent(this, CadastroActivity::class.java)
        startActivityForResult(it, CADASTRO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CADASTRO){
                Log.i("bolao", "Estou no result")
                val pessoa = data?.getSerializableExtra("PESSOA") as ApostadorModel
                Log.i("bolao", pessoa.toString())
                this.apostadordao.insert(pessoa)

                this.adapter()
                //Log.i("APP_PESSOA", this.dao.select().toString())
            }
        }
    }
}
