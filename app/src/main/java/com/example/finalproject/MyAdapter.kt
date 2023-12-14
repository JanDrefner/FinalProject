
package com.example.finalproject

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.models.Card


/*class MyAdapter(private val myList: ArrayList<Card>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    //onCreateViewHolder() inflates a layout file and returns a new instance of MyViewHolder class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }


    //onBindViewHolder() binds data to the views in the MyViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])
        Log.i("VIEWHOLDER", myList.toString())
    }

    //getItemCount() returns the size of the data set
    override fun getItemCount() = myList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtFront : TextView = itemView.findViewById(R.id.txtFront)
        val txtBack : TextView = itemView.findViewById(R.id.txtBack)
        fun bind(item: Card) {
            txtFront.text = item.front
            txtBack.text = item.back
        }
    }
}*/
