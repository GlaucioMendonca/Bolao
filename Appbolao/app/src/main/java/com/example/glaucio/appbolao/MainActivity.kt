package com.example.glaucio.appbolao

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    val CADASTRO = 1

    private lateinit var lv: ListView
    private lateinit var bolaodao: BolaoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val it = Intent(this, CadastroBolao::class.java)
            startActivityForResult(it, CADASTRO)
        }
        this.bolaodao = BolaoDao(this)
        this.lv = findViewById(R.id.lvMain)
        this.adapter()

    }

    fun adapter (){

        this.lv.adapter = ArrayAdapter<Bolao>(this, android.R.layout.simple_list_item_1, bolaodao.select())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CADASTRO){
                val bolao = data?.getSerializableExtra("BOLAO") as Bolao
                this.bolaodao.insert(bolao)
                this.adapter()
                //Log.i("APP_PESSOA", this.dao.select().toString())
            }
        }
    }
}