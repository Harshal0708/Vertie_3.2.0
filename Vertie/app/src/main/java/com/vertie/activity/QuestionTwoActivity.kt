package com.vertie.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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
import java.text.SimpleDateFormat
import java.util.*

class QuestionTwoActivity : AppCompatActivity() {

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
    private lateinit var question_two_cons_5: ConstraintLayout
    private lateinit var question_two_img_true: ImageView
    private lateinit var question_two__true: ImageView

    private lateinit var question_two_date: TextView
    private lateinit var question_two_work: TextView
    private lateinit var question_two_school: TextView
    private lateinit var question_two_caring: TextView
    private lateinit var question_two_my_own_condition: TextView

    private lateinit var type1Select: String
    private lateinit var type2Select: String
    var cal = Calendar.getInstance()

    lateinit var datePicker: DatePickerDialog


    private lateinit var question_two_caring_true: ImageView
    private lateinit var question_two__own: ImageView
    private var currentProgressBar: Int = 0

    var questionsArrList = arrayListOf<QuestionsObj>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_two)
        questionsArrList = SingletonClass.getInstance()!!.questionsArrListData
        Init()
        updateDateInView()
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        question_two_date!!.text = sdf.format(cal.getTime())
    }

    private fun Init() {
        question_view = findViewById(R.id.question_view)
        question_view.findViewById<ImageView>(R.id.question_view_img).setOnClickListener {
            finish()
        }
        question_view_2 = findViewById(R.id.question_view_2)

        type1Select = ""
        type2Select = ""

        question_view_text = question_view.findViewById(R.id.question_view_text)
        question_view_progressBar = question_view.findViewById(R.id.question_view_progressBar)

        question_two_cons_1 = findViewById(R.id.question_two_cons_1)
        question_two_date = findViewById(R.id.question_two_date)
        question_two_cons_2 = findViewById(R.id.question_two_cons_2)
        question_two_cons_3 = findViewById(R.id.question_two_cons_3)
        question_two_cons_4 = findViewById(R.id.question_two_cons_4)
        question_two_cons_5 = findViewById(R.id.question_two_cons_5)

        question_two_work = findViewById(R.id.question_two_work)
        question_two_school = findViewById(R.id.question_two_school)
        question_two_caring = findViewById(R.id.question_two_caring)
        question_two_my_own_condition = findViewById(R.id.question_two_my_own_condition)

        question_two_img_true = findViewById(R.id.question_two_img_true)
        question_two__true = findViewById(R.id.question_two__true)
        question_two_caring_true = findViewById(R.id.question_two_caring_true)
        question_two__own = findViewById(R.id.question_two__own)
        question_view_next = question_view_2.findViewById(R.id.question_view_next)
        question_view_finish = question_view_2.findViewById(R.id.question_view_finish)
        question_view_text.text = Constants.step2

        question_view_progressBar.visibility = View.VISIBLE
        currentProgressBar = 20
        question_view_progressBar.setProgress(currentProgressBar)
        question_view_progressBar.max = 70


        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                view.setMinDate(System.currentTimeMillis() - 1000);
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                updateDateInView()
            }
        }

        question_two_cons_1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                datePicker = DatePickerDialog(
                    this@QuestionTwoActivity,
                    R.style.CustomHeaderLayout,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                )

                datePicker.datePicker.minDate = System.currentTimeMillis()
                datePicker.datePicker.calendarViewShown = false
                datePicker.show()
                datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                datePicker.getButton(DatePickerDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);
            }

        })

        question_two_cons_2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_2.setBackgroundResource(R.drawable.question_background_select)
                question_two_img_true.visibility = View.VISIBLE
                question_two_work.setTextColor(getColor(R.color.textColor))
                type1Select = "Work"
                question_two_cons_3.setBackgroundResource(R.drawable.question_background)
                question_two__true.visibility = View.GONE
                question_two_school.setTextColor(getColor(R.color.color231F20))
            }

        })

        question_two_cons_3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_3.setBackgroundResource(R.drawable.question_background_select)
                question_two__true.visibility = View.VISIBLE
                question_two_school.setTextColor(getColor(R.color.textColor))
                type1Select = "School"
                question_two_cons_2.setBackgroundResource(R.drawable.question_background)
                question_two_img_true.visibility = View.GONE
                question_two_work.setTextColor(getColor(R.color.color231F20))
            }

        })

        question_two_cons_4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_4.setBackgroundResource(R.drawable.question_background_select)
                question_two_caring_true.visibility = View.VISIBLE
                question_two_caring.setTextColor(getColor(R.color.textColor))
                type2Select = "Caring for someone"
                question_two_cons_5.setBackgroundResource(R.drawable.question_background)
                question_two__own.visibility = View.GONE
                question_two_my_own_condition.setTextColor(getColor(R.color.color231F20))
            }
        })

        question_two_cons_5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_5.setBackgroundResource(R.drawable.question_background_select)
                question_two__own.visibility = View.VISIBLE
                question_two_my_own_condition.setTextColor(getColor(R.color.textColor))
                type2Select = "My own condition"
                question_two_cons_4.setBackgroundResource(R.drawable.question_background)
                question_two_caring_true.visibility = View.GONE
                question_two_caring.setTextColor(getColor(R.color.color231F20))
            }
        })

        question_view_next.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                nextBTN()
            }
        })

        question_view_finish.setOnClickListener {
            if (setData()) {
                apiCall()
                Globals.AddMenualRecord(this)
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
        resultLauncher.launch(Intent(this, QuestionThreeActivity::class.java))
    }

    private fun nextBTN() {
        if (setData()) {
            resultLauncher.launch(Intent(this, QuestionThreeActivity::class.java))
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
            if(type1Select == "Work"){
                if(type2Select == "Caring for someone"){
                    questionsArrList.add(
                        QuestionsObj(
                            Constants.id21,
                            "calendar",
                            type1Select+" "+type2Select,
                            question_two_date!!.text as String?,
                            "string"
                        )
                    )
                }else if(type2Select == "My own condition"){
                    questionsArrList.add(
                        QuestionsObj(
                            Constants.id22,
                            "calendar",
                            type1Select+" "+type2Select,
                            question_two_date!!.text as String?,
                            "string"
                        )
                    )
                }
            }else if(type1Select == "School"){
                if(type2Select == "Caring for someone"){
                    questionsArrList.add(
                        QuestionsObj(
                            Constants.id23,
                            "calendar",
                            type1Select+" "+type2Select,
                            question_two_date!!.text as String?,
                            "string"
                        )
                    )
                }else if(type2Select == "My own condition"){
                    questionsArrList.add(
                        QuestionsObj(
                            Constants.id24,
                            "calendar",
                            type1Select+" "+type2Select,
                            question_two_date!!.text as String?,
                            "string"
                        )
                    )
                }
            }
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
            setResult(Activity.RESULT_OK, intent)
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
                        Globals.AddMenualRecord(this@QuestionTwoActivity)
                    }
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Globals.hideProgressDialog()
                }
            })
        }
    }

}