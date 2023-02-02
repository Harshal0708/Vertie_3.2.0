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

class QuestionFourActivity : AppCompatActivity() {
    private lateinit var question_view: View

    private lateinit var question_view_2: View
    private lateinit var question_view_text: TextView
    private lateinit var question_view_progressBar: ProgressBar
    private lateinit var question_view_next: Button
    private lateinit var question_view_finish: Button

    private lateinit var question_two_cons_1: ConstraintLayout
    private lateinit var question_two_cons_2: ConstraintLayout
    private lateinit var question_two_cons_3: ConstraintLayout
    private lateinit var question_two_cons_4: ConstraintLayout

    private lateinit var question_view_img_1: ImageView
    private lateinit var question_view_img_2: ImageView
    private lateinit var question_view_img_3: ImageView
    private lateinit var question_view_img_4: ImageView

    private lateinit var question_two_date: TextView
    private lateinit var question_two_date_2: TextView
    private lateinit var question_two_date_3: TextView
    private lateinit var question_two_date_4: TextView

    private lateinit var type1Select: String

    private var currentProgressBar: Int = 0

    var questionsArrList = arrayListOf<QuestionsObj>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_four)

        questionsArrList = SingletonClass.getInstance()!!.questionsArrListData
        InIt()
    }

    private fun InIt() {
        question_view = findViewById(R.id.question_view)
        question_view.findViewById<ImageView>(R.id.question_view_img)
            .setOnClickListener { finish() }
        question_view_2 = findViewById(R.id.question_view_2)
        type1Select = ""
        question_view_text = question_view.findViewById(R.id.question_view_text)
        question_view_progressBar = question_view.findViewById(R.id.question_view_progressBar)

        question_two_cons_1 = findViewById(R.id.question_two_cons_1)
        question_two_cons_2 = findViewById(R.id.question_two_cons_2)
        question_two_cons_3 = findViewById(R.id.question_two_cons_3)
        question_two_cons_4 = findViewById(R.id.question_two_cons_4)

        question_view_img_1 = findViewById(R.id.question_view_img_1)
        question_view_img_2 = findViewById(R.id.question_view_img_2)
        question_view_img_3 = findViewById(R.id.question_view_img_3)
        question_view_img_4 = findViewById(R.id.question_view_img_4)

        question_two_date = findViewById(R.id.question_two_date)
        question_two_date_2 = findViewById(R.id.question_two_date_2)
        question_two_date_3 = findViewById(R.id.question_two_date_3)
        question_two_date_4 = findViewById(R.id.question_two_date_4)

        question_view_next = question_view_2.findViewById(R.id.question_view_next)
        question_view_finish = question_view_2.findViewById(R.id.question_view_finish)
        question_view_text.text = Constants.step4

        question_view_progressBar.visibility = View.VISIBLE
        currentProgressBar = 40
        question_view_progressBar.setProgress(currentProgressBar)
        question_view_progressBar.max = 70

        question_two_cons_1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_1.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_1.visibility = View.VISIBLE
                question_two_date.setTextColor(getColor(R.color.textColor))

                question_two_cons_2.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_2.visibility = View.GONE
                question_two_date_2.setTextColor(getColor(R.color.color231F20))

                question_two_cons_3.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_3.visibility = View.GONE
                question_two_date_3.setTextColor(getColor(R.color.color231F20))

                question_two_cons_4.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_4.visibility = View.GONE
                question_two_date_4.setTextColor(getColor(R.color.color231F20))

                type1Select = "Burning"
            }

        })

        question_two_cons_2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_2.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_2.visibility = View.VISIBLE
                question_two_date_2.setTextColor(getColor(R.color.textColor))

                question_two_cons_1.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_1.visibility = View.GONE
                question_two_date.setTextColor(getColor(R.color.color231F20))

                question_two_cons_3.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_3.visibility = View.GONE
                question_two_date_3.setTextColor(getColor(R.color.color231F20))

                question_two_cons_4.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_4.visibility = View.GONE
                question_two_date_4.setTextColor(getColor(R.color.color231F20))
                type1Select = "Going to the bathroom more"
            }

        })

        question_two_cons_3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_3.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_3.visibility = View.VISIBLE
                question_two_date_3.setTextColor(getColor(R.color.textColor))

                question_two_cons_1.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_1.visibility = View.GONE
                question_two_date.setTextColor(getColor(R.color.color231F20))

                question_two_cons_2.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_2.visibility = View.GONE
                question_two_date_2.setTextColor(getColor(R.color.color231F20))

                question_two_cons_4.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_4.visibility = View.GONE
                question_two_date_4.setTextColor(getColor(R.color.color231F20))
                type1Select = "Blood in urine"
            }

        })

        question_two_cons_4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_4.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_4.visibility = View.VISIBLE
                question_two_date_4.setTextColor(getColor(R.color.textColor))

                question_two_cons_1.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_1.visibility = View.GONE
                question_two_date.setTextColor(getColor(R.color.color231F20))

                question_two_cons_2.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_2.visibility = View.GONE
                question_two_date_2.setTextColor(getColor(R.color.color231F20))

                question_two_cons_3.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_3.visibility = View.GONE
                question_two_date_3.setTextColor(getColor(R.color.color231F20))
                type1Select = "Difficulty urinating"
            }
        })

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

}