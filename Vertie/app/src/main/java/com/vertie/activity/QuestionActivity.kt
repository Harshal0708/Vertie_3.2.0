package com.vertie.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.vertie.Constants
import com.vertie.R
import com.vertie.data.user.source.local.UserPersistanceContract
import com.vertie.javacode.apiManager.APIManager
import com.vertie.javacode.models.QuestionsObj
import com.vertie.javacode.singleton.SingletonClass
import com.vertie.javacode.utility.Globals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class QuestionActivity : AppCompatActivity() {

    private lateinit var question_view: View
    private lateinit var question_view_2: View
    private lateinit var question_view_text: TextView
    private lateinit var question_view_progressBar: ProgressBar
    private lateinit var question_view_next: Button
    private lateinit var question_view_finish: Button
    private var currentProgressBar: Int = 0

    private lateinit var question_breath_count: EditText
    private lateinit var question_weight_count: EditText
    private lateinit var question_systolic: EditText
    private lateinit var question_diastolic: EditText
    private lateinit var question_breath_labs: EditText

    var questionsArrList = arrayListOf<QuestionsObj>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_one)
        Init()
    }

    private fun Init() {
        question_view = findViewById(R.id.question_view)
        question_view.findViewById<ImageView>(R.id.question_view_img).setOnClickListener { finish() }
        question_view_2 = findViewById(R.id.question_view_2)
        question_view_text = question_view.findViewById(R.id.question_view_text)
        question_view_progressBar = question_view.findViewById(R.id.question_view_progressBar)
        question_view_next = question_view_2.findViewById(R.id.question_view_next)
        question_view_finish = question_view_2.findViewById(R.id.question_view_finish)
        question_view_text.text = Constants.step1

        question_breath_count = findViewById<EditText>(R.id.question_breath_count)
        question_weight_count = findViewById<EditText>(R.id.question_weight_count)
        question_systolic = findViewById<EditText>(R.id.question_systolic)
        question_diastolic = findViewById<EditText>(R.id.question_diastolic)
        question_breath_labs = findViewById<EditText>(R.id.question_breath_labs)

        question_view_progressBar.visibility = View.VISIBLE
        currentProgressBar = 10
        question_view_progressBar.setProgress(currentProgressBar)
        question_view_progressBar.max = 70

        question_view_next.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                nextBTN()
            }
        })

        question_view_finish.setOnClickListener {
            if (setData()) {
                apiCall()
            }
        }

        question_view_2.findViewById<TextView>(R.id.question_txt_name2)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    skipBTN()
                }
            })
    }

    private fun skipBTN() {
        SingletonClass.getInstance()!!.questionsArrListData = questionsArrList
//        startActivityForResult(Intent(this@QuestionActivity, QuestionTwoActivity::class.java), 2)
        resultLauncher.launch(Intent(this, QuestionTwoActivity::class.java))
    }

    var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            finish()
        }
    }

    private fun nextBTN() {
        if (setData()) {
            startActivityForResult(Intent(this@QuestionActivity, QuestionTwoActivity::class.java),2)
        }
    }

    private fun setData(): Boolean {
        if (question_breath_count.text.toString().isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id11,
                    "textbox",
                    question_breath_count.text.toString(),
                    "string",
                    "string"
                )
            )
        }
        if (question_weight_count.text.toString().isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id12,
                    "textbox",
                    question_weight_count.text.toString(),
                    "string",
                    "string"
                )
            )
        }
        if (question_systolic.text.toString().isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id13,
                    "textbox",
                    question_systolic.text.toString(),
                    "string",
                    "string"
                )
            )
        }
        if (question_diastolic.text.toString().isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id13,
                    "textbox",
                    question_diastolic.text.toString(),
                    "string",
                    "string"
                )
            )
        }
        if (question_breath_labs.text.toString().isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id14,
                    "textbox",
                    question_breath_labs.text.toString(),
                    "string",
                    "string"
                )
            )
        }
        SingletonClass.getInstance()!!.questionsArrListData = questionsArrList
        return true
    }

    private fun apiCall() {
        var preferences: SharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val uId: String? = preferences.getString(UserPersistanceContract.UserEntry.USER_ID, null)
        val mapFields = java.util.HashMap<String, Any?>()
        mapFields["questions"] = SingletonClass.getInstance()!!.questionsArrListData
        mapFields["userId"] = uId
        if (SingletonClass.getInstance()!!.questionsArrListData.size == 0) {
            finish()
        } else {
            val call = APIManager
                .getUserManagerService(
                    this,
                    GsonConverterFactory.create(GsonBuilder().setLenient().create())
                )
                .saveNumber(mapFields)
            Globals.showProgressDialog(this)
            call.enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    Globals.hideProgressDialog()
                    if (response.body() as Boolean) {
//                        finish()
//                        SingletonClass.getInstance()!!.questionsArrListData=null
                        Globals.AddMenualRecord(this@QuestionActivity)
                    }
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Globals.hideProgressDialog()
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == 2) {
            finish()
        }
    }

}