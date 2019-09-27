package com.isa.silva.teste.Activity

import android.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.isa.silva.teste.Adapter.PerfisAdapter
import com.isa.silva.teste.Models.perfil
import com.isa.silva.teste.R
import com.isa.silva.teste.Task.BuscaTask
import com.isa.silva.teste.Utils.CustomRecyclerView
import com.isa.silva.teste.Utils.utils
import com.isa.silva.teste.Utils.utils.hideKeyboard


class MainActivity : AppCompatActivity() {

    private lateinit var btn_pesquisa: Button
    private lateinit var edt_nome: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        btn_pesquisa = findViewById(R.id.btn_pesquisa)
        edt_nome = findViewById(R.id.edt_nome)



        btn_pesquisa.setOnClickListener {
            hideKeyboard(this@MainActivity)
            if(edt_nome.text.toString().isNotEmpty()) {

               // perfilList.clear()
                val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val info = manager.activeNetworkInfo
                var cm: ConnectivityManager =  this@MainActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                var activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        addDataBusca(edt_nome.getText().toString().toLowerCase())
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        addDataBusca(edt_nome.getText().toString().toLowerCase())
                    }
                } else {
                    utils.showMessage(this@MainActivity, "A network error has occurred!", " Check your Internet connection and try again later.")

                }
            }
        }

    }
    fun addDataBusca(id: String) {

        try{
        object : BuscaTask(id) {
            lateinit var pd: ProgressDialog
             override fun onPreExecute() {
                super.onPreExecute()
                 pd = utils.createAndShowProgressDialog(this@MainActivity)
            }

             override fun onPostExecute(result: String?) {
                super.onPostExecute(result)

                utils.dismissProgressDialog(pd)

                 if (!result!!.equals("[]")) {

                     var intent = Intent(this@MainActivity, DetalhesActivity::class.java)
                     intent.putExtra("resultado", result)
                     startActivity(intent);

                 }else{
                     utils.showMessage(this@MainActivity, "User not found!", "Please enter another name")
                 }


             }

        }.execute()
    }catch (ex : Exception){
            utils.showMessage(this@MainActivity, "A network error has occurred!", " Check your Internet connection and try again later.")

        }
    }
}
