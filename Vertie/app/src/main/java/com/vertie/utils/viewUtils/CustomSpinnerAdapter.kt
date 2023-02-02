package com.vertie.utils.viewUtils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.annotation.SuppressLint
import com.vertie.databinding.ListItemSpinnerBinding


class CustomSpinnerAdapter(
        context: Context,
        resourceLayout: Int,
        resourceTextView: Int
) : ArrayAdapter<String>(context, resourceLayout, resourceTextView) {
    private var itemsList: List<String> = arrayListOf()

    override fun getCount(): Int {
        return itemsList.size
    }

    override fun getItem(position: Int): String? {
        return itemsList[position]
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemSpinnerBinding.inflate(inflater, null, false)
        binding.text = itemsList[position]
        binding.index = position
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemSpinnerBinding.inflate(inflater, null, false)
        binding.text = itemsList[position]
        binding.index = position
        return binding.root
    }

    fun setItemsList(items: List<String>) {
        this.itemsList = items
        notifyDataSetChanged()
    }
}