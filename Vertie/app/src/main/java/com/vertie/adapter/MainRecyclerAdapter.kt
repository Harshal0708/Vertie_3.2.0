package com.vertie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vertie.R
import com.vertie.model.AllCategory
import com.vertie.model.CategoryItem

class MainRecyclerAdapter(private val context: Context,private val allCategory: List<AllCategory>) :
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    lateinit var holderRecycler :MainViewHolder

    class MainViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){

        var cat_title :TextView
        var catItemRecycle :RecyclerView
        var next :ImageView
        init{
            cat_title = itemView.findViewById(R.id.cat_title)
            catItemRecycle = itemView.findViewById(R.id.catItemRecycle)
            next = itemView.findViewById(R.id.question_view_next)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return  MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycle_row_item,parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holderRecycler = holder

        holder.cat_title.text = allCategory[position].categoryTitle
        setCatItemRecycler(holder.catItemRecycle,allCategory[position].categoryItem)
        //holder.next.text=position.toString()

    }

    override fun getItemCount(): Int {
       return allCategory.size
    }

    private fun setCatItemRecycler(recyclerView: RecyclerView,categoryItem: List<CategoryItem>){
        val itemRecyclerAdapter = CategoryItemAdapter(context,categoryItem)
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = itemRecyclerAdapter
        recyclerView.isNestedScrollingEnabled=false
        itemRecyclerAdapter.setOnIemClickListener(object : CategoryItemAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(context,"Click on $position",Toast.LENGTH_SHORT).show()
               // holderRecycler.next.visibility =View.VISIBLE
                itemRecyclerAdapter.setOnButtonVisibility(position)
            }
        })
    }

}