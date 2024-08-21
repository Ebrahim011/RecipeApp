package com.iti_project.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NamesAdapter (val nameList:List<String>): RecyclerView.Adapter<NamesAdapter.myViewHolder>() {
    inner class myViewHolder(val itemView: View): ViewHolder(itemView){
        val tvName: TextView =itemView.findViewById(R.id.rv_name)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater= LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return  myViewHolder(inflater)
    }

    override fun getItemCount()= nameList.size
    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.tvName.text=nameList.get(position)
    }
}