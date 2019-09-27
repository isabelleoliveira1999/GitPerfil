package com.isa.silva.teste.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.isa.silva.teste.Adapter.PerfisAdapter
import com.isa.silva.teste.Models.perfil
import com.isa.silva.teste.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*


class DetalhesActivity: AppCompatActivity() {

    var Perfil : ArrayList<perfil> = ArrayList<perfil>()
    lateinit var recycler : RecyclerView
    lateinit var adapter : PerfisAdapter
    lateinit var img_foto: ImageView
    lateinit var txt_name_dono: TextView

    override fun onCreate(savedInstanceState: Bundle??) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        recycler = findViewById(R.id.rec_perfil)
        img_foto = findViewById(R.id.img_foto)
        txt_name_dono = findViewById(R.id.txt_perfil)

        var extras = getIntent().getExtras()!!

        var data: String = extras["resultado"].toString()
        Adicionar(data)
        imageView2.setOnClickListener {

            this.finish()
        }
    }
    fun Adicionar(result : String){

        val ListTypeToken = object : TypeToken<List<JsonObject>>() {

        }.type

        val TypeToken = object : TypeToken<perfil>() {

        }.type

        var gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()


        val JsonArray = gson.fromJson<List<JsonObject>>(result, ListTypeToken)
        Perfil.clear()
        for (Json in JsonArray) {
            try {

                val Item = gson.fromJson<perfil>(Json, TypeToken)

                Perfil.add(Item)
            } catch (ex: Exception) {
                println(ex)
            }
        }
        var i = 0
        for(index in Perfil) {
            if (!Perfil[i].owner!!.avatar_url.isNullOrEmpty() && !Perfil[i].owner!!.login.isNullOrEmpty()) {
                Picasso.with(this@DetalhesActivity)
                    .load(Perfil[i].owner!!.avatar_url)
                    .resize(100, 100)
                    .into(img_foto)
                txt_name_dono.text = Perfil[i].owner!!.login!!.toUpperCase()
                break
            }else if(Perfil.size - 1 == i ){
                img_foto.setImageResource(R.drawable.ic_launcher_foreground)
            }else{
                i++
            }
        }
        adapter = PerfisAdapter()
        adapter.setDataSource(this@DetalhesActivity, Perfil)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this@DetalhesActivity,RecyclerView.VERTICAL, false)

    }

}
