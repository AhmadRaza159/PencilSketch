package com.example.pencilsketch

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class AdapterFile (var context: Context, var widgetList: Array<File>, private val itemsListInterface: MyInterface, private val itemsListInterfaceDelete: MyInterface) : RecyclerView.Adapter<AdapterFile.myVieholder>() {
   class myVieholder(itemView:View): RecyclerView.ViewHolder(itemView) {

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myVieholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return myVieholder(v)
    }

    override fun onBindViewHolder(holder: myVieholder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.text_name).text=widgetList[position].name
        holder.itemView.findViewById<ImageView>(R.id.btn_delete).setOnClickListener {
            itemsListInterfaceDelete.onclick(widgetList[position])
        }
        holder.itemView.setOnClickListener{
            itemsListInterface.onclick(widgetList[position])

        }
    }

    override fun getItemCount(): Int {
        return widgetList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getNew(widgetList: Array<File>){
        this.widgetList=widgetList
        this.notifyDataSetChanged()
    }
}