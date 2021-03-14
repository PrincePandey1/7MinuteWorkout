package com.example.android.a7minuteworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_list.view.*
import java.util.zip.Inflater

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>,val context: Context): RecyclerView.Adapter<ExerciseStatusAdapter.MyViewHolder>(){


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvItem = view.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater :LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_exercise_list,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()


    }

    override fun getItemCount(): Int {
       return items.size
    }
}