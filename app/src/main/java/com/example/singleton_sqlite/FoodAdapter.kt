package com.example.singleton_sqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private var context:MainActivity,private var listFood:ArrayList<Food>): RecyclerView.Adapter<FoodAdapter.ViewHolder>(){
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var adapter_txtName :TextView= view.findViewById(R.id.adapter_txtName)
        var adapter_txtDescribe:TextView=view.findViewById(R.id.adapter_txtDescribe)
        var adapter_txtPrice:TextView=view.findViewById(R.id.adapter_Price)
        var adapter_imgDeelte:ImageView=view.findViewById(R.id.adapter_imgDelete)
        var adapter_imgEdit :ImageView =view.findViewById(R.id.adapter_imgEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =LayoutInflater.from(context).inflate(R.layout.adapter_food,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var food :Food = listFood.get(position)
        holder.adapter_txtName.setText(food.name)
        holder.adapter_txtDescribe.setText(food.describe)
        holder.adapter_txtPrice.setText(food.price)
        holder.adapter_imgDeelte.setOnClickListener(){
            context.DELETE(food.id)
        }
        holder.adapter_imgEdit.setOnClickListener(){
            context.EDIT(food.id,food.name,food.describe,food.price)
        }
    }

    override fun getItemCount(): Int {
        return listFood.size
    }
}