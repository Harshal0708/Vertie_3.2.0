package com.vertie.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
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

class QuestionFourActivity : AppCompatActivity(), ListItemSelectionAdapter.OnClickListner {
    private lateinit var question_view: View

    private lateinit var question_view_2: View
    private lateinit var question_view_text: TextView
    private lateinit var question_view_progressBar: ProgressBar
    private lateinit var question_view_next: Button
    private lateinit var question_view_finish: Button

    private lateinit var type1Select: String

    private var currentProgressBar: Int = 0
    private var recyclerView: RecyclerView? = null
    var questionsArrList = arrayListOf<QuestionsObj>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_four)

        try {
            questionsArrList = SingletonClass.getInstance()!!.questionsArrListData
        }catch (e:NullPointerException){
        }
        InIt()
        recyclerView = findViewById(R.id.rvStep4)

        val stressAndCopingList = arrayOf(
            ListItem("1", "Burning", "", false),
            ListItem("2", "Going to the bathroom mor", "", false),
            ListItem("3", "Blood in urine", "", false),
            ListItem("4", "Difficulty urinating", "", false),
        )

        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        val adapter =
            ListItemSelectionAdapter(
                this,
                stressAndCopingList
            )
        recyclerView!!.adapter = adapter
        adapter.setOnClickListner(this)
    }

    private fun InIt() {
        question_view = findViewById(R.id.question_view)
        question_view.findViewById<ImageView>(R.id.question_view_img)
            .setOnClickListener { finish() }
        question_view_2 = findViewById(R.id.question_view_2)
        type1Select = ""
        question_view_text = question_view.findViewById(R.id.question_view_text)
        question_view_progressBar = question_view.findViewById(R.id.question_view_progressBar)

        question_view_next = question_view_2.findViewById(R.id.question_view_next)
        question_view_finish = question_view_2.findViewById(R.id.question_view_finish)
        question_view_text.text = Constants.step4

        question_view_progressBar.visibility = View.VISIBLE
        currentProgressBar = 40
        question_view_progressBar.setProgress(currentProgressBar)
        question_view_progressBar.max = 70

        question_view_next.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                nextBTN()
            }

        })

        question_view_finish.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                resultLauncher.launch(Intent(this@QuestionFourActivity, QuestionFiveActivity::class.java))
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
        resultLauncher.launch(Intent(this@QuestionFourActivity, QuestionFiveActivity::class.java))
    }
    private fun nextBTN() {
        if (setData()) {
            resultLauncher.launch(Intent(this@QuestionFourActivity, QuestionFiveActivity::class.java))
        }
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK, Intent())
            finish()
        }
    }
    private fun setData(): Boolean {
        if (type1Select.isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id41,
                    "checkboxlist",
                    type1Select,
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
                        Globals.AddMenualRecord(this@QuestionFourActivity)
                    }
                }
                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Globals.hideProgressDialog()
                }
            })
        }
    }
    override fun onClickEvent(view: View?, position: Int, item: ListItem?) {
        type1Select = item?.name.toString()
        SingletonClass.getInstance().selectedStep4 = item?.name
    }

}