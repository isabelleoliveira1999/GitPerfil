package com.isa.silva.teste.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.isa.silva.teste.Models.perfil
import com.isa.silva.teste.R
import com.squareup.picasso.Picasso


class PerfisAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var Perfis: ArrayList<perfil> = ArrayList<perfil>()
    lateinit var mContext: Context
    lateinit var txt_name: TextView
    lateinit var img_foto: ImageView
    lateinit var txt_name_dono: TextView
    lateinit var txt_linguagem: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        @LayoutRes var layout: Int
        val viewHolder: RecyclerView.ViewHolder
       layout = R.layout.recycler_item_perfis

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        viewHolder = Holder(view)


        txt_name = view.findViewById(R.id.txt_name)
        img_foto = view.findViewById(R.id.img_foto)
        txt_linguagem = view.findViewById(R.id.txt_linguagem)
        txt_name_dono = view.findViewById(R.id.txt_name_dono)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return Perfis.size
    }

    fun setDataSource(context: Context, data: ArrayList<perfil>) {
        Perfis = data
        notifyDataSetChanged()
        mContext = context

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        txt_name.text = "Respo: " + Perfis[position].name

        txt_name_dono.text = "User: " + Perfis[position].owner!!.login

        if (Perfis[position].owner!!.avatar_url.isNullOrEmpty()){
            img_foto.setImageResource(R.drawable.ic_launcher_foreground)
        }else {
            Picasso.with(mContext)
                .load(Perfis[position].owner!!.avatar_url)
                .resize(100, 100)
                .into(img_foto)
        }
        if (Perfis[position].language.isNullOrEmpty() ||Perfis[position].language == " "){
            txt_linguagem.text = "Language: not informed"
        }else {
            txt_linguagem.text = "Language: " + Perfis[position].language
        }
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}