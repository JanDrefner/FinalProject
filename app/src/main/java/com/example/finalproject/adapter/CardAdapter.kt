package com.example.finalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Cards
import com.example.finalproject.R

class CardAdapter (private val cardList: ArrayList<Cards>) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCard = cardList[position]
        holder.cardTitle.text = currentCard.title
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardTitle : TextView = itemView.findViewById(R.id.tvCardTitle)
    }
}