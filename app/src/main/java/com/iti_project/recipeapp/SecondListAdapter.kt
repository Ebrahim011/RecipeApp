package com.example.recyclerview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.iti_project.recipeapp.R

class SecondListAdapter (val nameList:List<String>,val imageList:List<Int>): RecyclerView.Adapter<SecondListAdapter.myViewHolder>() {
    inner class myViewHolder(val itemView: View):ViewHolder(itemView){
        val tvName:TextView=itemView.findViewById(R.id.rv_name)
        val imageView:ImageView=itemView.findViewById(R.id.image)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater=LayoutInflater.from(parent.context).inflate(R.layout.second_list,parent,false)
        return  myViewHolder(inflater)
    }

    override fun getItemCount()= nameList.size


    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.tvName.text=nameList.get(position)
        holder.imageView.setImageResource(imageList.get(position))
    }
}