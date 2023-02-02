package com.vertie.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vertie.R
import com.vertie.adapter.MainRecyclerAdapter
import com.vertie.model.CategoryItem

class QuestionActivity : AppCompatActivity() {
    private var mainRecycle: RecyclerView? = null
    private var next: ImageView? = null
    private var mainRecyclerAdapter: MainRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        viewId()
        val categoryItemList1: MutableList<CategoryItem> = ArrayList()
        categoryItemList1.add(CategoryItem(1, "Happy", R.drawable.ic_happy_mood,false))
        categoryItemList1.add(CategoryItem(1, "Good", R.drawable.ic_good,false))
        categoryItemList1.add(CategoryItem(1, "Okay", R.drawable.ic_okay_mood,false))
        categoryItemList1.add(CategoryItem(1, "Bad", R.drawable.ic_bad,false))


        setMainCategoryRecycler(categoryItemList1)
    }

    private fun viewId(){
        mainRecycle = findViewById(R.id.mainRecycle)
        next = findViewById(R.id.question_view_next)
    }

    fun nextVisibility(position:Int){
        Toast.makeText(getApplicationContext(), "Click on $position", Toast.LENGTH_SHORT).show()

        next?.visibility = View.VISIBLE
        next?.setBackgroundResource(R.drawable.ic_next_dark)
    }
    fun setMainCategoryRecycler(categoryItemList1: List<CategoryItem>) {

        val itemRecyclerAdapter = QuestionAdapter(this, categoryItemList1)
        mainRecycle?.layoutManager = GridLayoutManager(this, 2)
        mainRecycle?.adapter = itemRecyclerAdapter
        itemRecyclerAdapter.setOnIemClickListener(object : QuestionAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(getApplicationContext(), "Click on $position", Toast.LENGTH_SHORT).show()


            }

        })

    }
}