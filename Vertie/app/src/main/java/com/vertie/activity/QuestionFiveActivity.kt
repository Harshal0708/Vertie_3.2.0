package com.vertie.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.vertie.Constants
import com.vertie.R
import com.vertie.data.user.source.local.UserPersistanceContract
import com.vertie.javacode.ListItem
import com.vertie.javacode.ListItemSelectionAdapter
import com.vertie.javacode.apiManager.APIManager
import com.vertie.javacode.models.QuestionsObj
import com.vertie.javacode.singleton.SingletonClass
import com.vertie.javacode.utility.Globals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class QuestionFiveActivity : AppCompatActivity(), ListItemSelectionAdapter.OnClickListner {

    private lateinit var question_view: View

    private lateinit var question_view_2: View
    private lateinit var question_view_text: TextView
    private lateinit var question_view_progressBar: ProgressBar
    private lateinit var question_view_next: Button
    private lateinit var question_view_finish: Button

    private var currentProgressBar: Int = 0

    private var recyclerView: RecyclerView? = null
    var questionsArrList = arrayListOf<QuestionsObj>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_five)

        questionsArrList = SingletonClass.getInstance()!!.questionsArrListData
        InIt()

        recyclerView = findViewById(R.id.rvSetp5)

        val stressAndCopingList = arrayOf(
            ListItem("1", "Back Pain", "", false),
            ListItem("2", "Pain in your arms, legs, or joints (knees, hips, etc.)", "", false),
            ListItem("3", "Feeling tired or having little energy", "", false),
            ListItem("4", "Trouble falling or staying asleep, or sleeping too much", "", false),
            ListItem("5", "Little interest or pleasure in doing things", "", false),
            ListItem("6", "Anxiety attack", "", false),
            ListItem("7", "Headaches", "", false),
            ListItem("8", "Chest pain", "", false),
            ListItem("9", "Dizzriness", "", false),
            ListItem("10", "Fainting spells", "", false),
            ListItem("11", "Feeling your heart pound or race", "", false),
            ListItem("12", "Shortness of breath", "", false),
            ListItem("13", "Constipation, loose bowels, or diarrhea", "", false),
            ListItem("14", "Nausea, gas, or indigestion", "", false)
        )
        responceBodyforWorkRadiusList(stressAndCopingList)
    }

    private fun responceBodyforWorkRadiusList(listWorkRadius: Array<ListItem>) {
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        val adapter =
            ListItemSelectionAdapter(
                this,
                listWorkRadius
            )
        recyclerView!!.adapter = adapter
        adapter.setOnClickListner(this)
    }

    private fun InIt() {
        question_view = findViewById(R.id.question_view)
        question_view.findViewById<ImageView>(R.id.question_view_img).setOnClickListener { finish() }
        question_view_2 = findViewById(R.id.question_view_2)

        question_view_text = question_view.findViewById(R.id.question_view_text)
        question_view_progressBar = question_view.findViewById(R.id.question_view_progressBar)

        question_view_next = question_view_2.findViewById(R.id.question_view_next)
        question_view_finish = question_view_2.findViewById(R.id.question_view_finish)
        question_view_text.text = Constants.step5

        question_view_progressBar.visibility = View.VISIBLE
        currentProgressBar = 50
        question_view_progressBar.setProgress(currentProgressBar)
        question_view_progressBar.max = 70

        question_view_next.setOnClickListener {
            if (setData()) {
                resultLauncher.launch(
                    Intent(
                        this@QuestionFiveActivity,
                        QuestionSixActivity::class.java
                    )
                )
            }
        }

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

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK, Intent())
            finish()
        }
    }

    private fun skipBTN() {
        resultLauncher.launch(Intent(this@QuestionFiveActivity, QuestionSixActivity::class.java))
    }

    private fun setData(): Boolean {
        if (SingletonClass.getInstance().selectedStep5.isEmpty()) {
            Log.d("Click :: "," Selected ::  null ")
        } else {
            Log.d("Click :: "," Selected ::  "+ SingletonClass.getInstance().selectedStep5)
            questionsArrList.add(
                QuestionsObj(
                    Constants.id51,
                    "checkboxlist",
                    SingletonClass.getInstance().selectedStep5,
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
            val intent = Intent()
            setResult(2, intent)
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
//                        val intent = Intent()
//                        setResult(2, intent)
//                        finish()
//                        SingletonClass.getInstance()!!.questionsArrListData=null
                        Globals.AddMenualRecord(this@QuestionFiveActivity)
                    }
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Globals.hideProgressDialog()
                }
            })
        }
    }
    override fun onClickEvent(view: View?, position: Int, item: ListItem?) {
        SingletonClass.getInstance().selectedStep5 = item?.name
    }

}