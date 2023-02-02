package com.vertie.question

import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.util.remove
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import com.vertie.R
import com.vertie.model.CategoryItem

class QuestionAdapter(private val context: Context, private val categoryItem: List<CategoryItem>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionItemViewHolder>() {

    private lateinit var mListener: onItemClickListener
    var checkBoxStatsArray = SparseBooleanArray()
    var value: Int = -1

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnIemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    class QuestionItemViewHolder(
        itemView: View,
        listener: onItemClickListener,
        context: Context,
        checkBoxStatsArray: SparseBooleanArray,
        categoryItem: List<CategoryItem>,
        value: Int
    ) :
        RecyclerView.ViewHolder(itemView) {
        var cat_item_img: ImageView
        var cat_item_right: ImageView
        var cat_item_name: TextView
//        var tvnext : TextView
        var lin_cat_item: LinearLayout

        init {
            cat_item_img = itemView.findViewById(R.id.cat_item_img)
//            tvnext = itemView.findViewById(R.id.tvnext)
            cat_item_name = itemView.findViewById(R.id.cat_item_name)
            lin_cat_item = itemView.findViewById(R.id.lin_cat_item)
            cat_item_right = itemView.findViewById(R.id.cat_item_right)

//
//            itemView.setOnClickListener {
//                // listener.onItemClick(adapterPosition)
//
//
//                if (!checkBoxStatsArray.get(adapterPosition, false)) {
//                    cat_item_right.visibility = View.VISIBLE
//                    checkBoxStatsArray.put(adapterPosition, true)
//                } else {
//                    cat_item_right.visibility = View.GONE
//                    checkBoxStatsArray.put(adapterPosition, false)
//                }
//
//                checkBoxStatsArray.clear()
//                (context as AnswerActivity).nextVisibility(adapterPosition)
//
//            }


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemViewHolder {
        return QuestionItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.cat_question_item, parent, false),
            mListener,
            context,
            checkBoxStatsArray,
            categoryItem,
            value
        )
    }

    override fun onBindViewHolder(holder: QuestionItemViewHolder, position: Int) {
        holder.cat_item_img.setImageResource(categoryItem[position].imageUri)
        holder.cat_item_name!!.text = categoryItem[position].itemName


//        if (!checkBoxStatsArray.get(position, false)) {
//            holder.cat_item_right.visibility = View.GONE
//        } else {
//            holder.cat_item_right.visibility = View.VISIBLE
//        }

//        Log.d("test", value.toString())
//
//        Log.d("test", position.toString())
        if (value == position) {
            holder.cat_item_right.visibility = View.VISIBLE
            holder.lin_cat_item.setBackgroundResource(R.drawable.ic_shape_dark)
        } else {
            holder.cat_item_right.visibility = View.GONE
            holder.lin_cat_item.setBackgroundResource(R.drawable.ic_shape)
        }
        holder.lin_cat_item.setOnClickListener(View.OnClickListener {
            setSingleSelectionPosition(holder.adapterPosition)
//            (context as AnswerActivity).nextVisibility(position)
        })
//        holder.tvnext.setOnClickListener {
//            (context as AnswerTwoActivity).nextVisibility(position)
//        }

    }

    fun setSingleSelectionPosition(pos :Int){
        if (pos == RecyclerView.NO_POSITION) return
        notifyItemChanged(value)
        value = pos
        notifyItemChanged(value)
    }
    override fun getItemCount(): Int {
        return categoryItem.size
    }
}