package com.vertie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.vertie.R

class QuestionSevenIntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_seven_intro)

        findViewById<ImageView>(R.id.question_view_img).setOnClickListener {
            finish()
        }

    }
}