package com.vertie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vertie.R
import com.vertie.model.CategoryItem

class CategoryItemAdapter(private val context: Context,private val categoryItem: List<CategoryItem>) : RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnIemClickListener(listener: onItemClickListener){
            mListener = listener
    }


 class CategoryItemViewHolder(itemView : View,listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
    var cat_item_img : ImageView
    var cat_item_name : TextView
    var lin_cat_item : LinearLayout
    init {
        cat_item_img = itemView.findViewById(R.id.cat_item_img)
        cat_item_name = itemView.findViewById(R.id.cat_item_name)
        lin_cat_item = itemView.findViewById(R.id.lin_cat_item)
        itemView.setOnClickListener{
            listener.onItemClick(adapterPosition)
        }
    }
 }

    fun setOnButtonVisibility(position: Int){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_row_item,parent,false),mListener)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.cat_item_img.setImageResource(categoryItem[position].imageUri)
        holder.cat_item_name!!.text = categoryItem[position].itemName
      //  holder.cat_item_img.setBackgroundResource(R.drawable.ic_shape)
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }
}