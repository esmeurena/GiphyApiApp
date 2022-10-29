package com.example.giphyapiapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DataAdapter(var ctx: Context, var modelList: ArrayList<DataModel>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    //interface for handling clicks
    interface OnItemClickListener {
        fun onItemClick(pos: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemized, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelList[position]
        Glide.with(ctx).load(data.imageUrl)
            .into(holder.imageView)
        //Glide.with(ctx).load(data.imageTitle)
            //.into(holder.textView)
        holder.textView.text = data.imageTitle
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView

        init {
            imageView = itemView.findViewById(R.id.imgView)
            itemView.setOnClickListener {
                if (onItemClickListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener!!.onItemClick(position)
                    }
                }
            }
            textView = itemView.findViewById(R.id.title)
            itemView.setOnClickListener {
                if (onItemClickListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener!!.onItemClick(position)
                    }
                }
            }
        }
    }
}