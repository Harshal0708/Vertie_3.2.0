package com.vertie.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.google.gson.GsonBuilder
import com.vertie.Constants
import com.vertie.R
import com.vertie.data.user.source.local.UserPersistanceContract
import com.vertie.javacode.apiManager.APIManager
import com.vertie.javacode.models.QuestionsObj
import com.vertie.javacode.singleton.SingletonClass
import com.vertie.javacode.utility.Globals
//import com.warkiz.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class QuestionSixActivity : AppCompatActivity() {

    private lateinit var question_view: View

    private lateinit var question_view_2: View
    private lateinit var question_view_text: TextView
    private lateinit var question_view_progressBar: ProgressBar
    private lateinit var question_view_next: Button
    private lateinit var question_view_finish: Button

    private lateinit var continuousSlider: Slider

    private var currentProgressBar: Int = 0

    //    lateinit var seekBarDiscrete: IndicatorSeekBar
    var questionsArrList = arrayListOf<QuestionsObj>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_six)

//        41aa8ff3-a10a-4698-8362-e94092b2342a	numberscale
        try {
            questionsArrList = SingletonClass.getInstance()!!.questionsArrListData
        } catch (e: NullPointerException) {
        }
        Init()


        continuousSlider.setLabelFormatter { value: Float ->
            return@setLabelFormatter "$${value.roundToInt()}"
        }
        continuousSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started
                Log.d("onStartTrackingTouch", slider.value.toString())
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
                Log.d("onStopTrackingTouch", slider.value.toString())
            }
        })
        continuousSlider.addOnChangeListener(object : Slider.OnChangeListener {
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                Log.d("addOnChangeListener", slider.value.toString())
            }
        })
    }

    private fun Init() {
        question_view = findViewById(R.id.question_view)
        question_view.findViewById<ImageView>(R.id.question_view_img)
            .setOnClickListener { finish() }
        question_view_2 = findViewById(R.id.question_view_2)

        continuousSlider = findViewById(R.id.continuousSlider)

        question_view_text = question_view.findViewById(R.id.question_view_text)
        question_view_progressBar = question_view.findViewById(R.id.question_view_progressBar)
//        seekBarDiscrete = findViewById(R.id.seekbar_Discrete)

        question_view_next = question_view_2.findViewById(R.id.question_view_next)
        question_view_finish = question_view_2.findViewById(R.id.question_view_finish)
        question_view_text.text = Constants.step6

        question_view_progressBar.visibility = View.VISIBLE
        currentProgressBar = 60
        question_view_progressBar.progress = currentProgressBar
        question_view_progressBar.max = 70

//        seekBarDiscrete?.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                Toast.makeText(applicationContext, "discrete seekbar progress: $progress", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//             //   Toast.makeText(applicationContext, "discrete seekbar touch started!", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//               // Toast.makeText(applicationContext, "discrete seekbar touch stopped", Toast.LENGTH_SHORT).show()
//            }
//        })

//        seekBarDiscrete = IndicatorSeekBar
//            .with(this)
//            .max(10f)
//            .min(0f)
//            .progress(1f)
//            .tickCount(1)
//            .showTickMarksType(TickMarkType.OVAL)
//            .tickMarksColor(resources.getColor(R.color.colorPrimary, null))
//            .tickMarksSize(13) //dp
//            .showTickTexts(true)
//            .tickTextsColor(resources.getColor(R.color.colorPrimary))
//            .tickTextsSize(13) //sp
//            .tickTextsTypeFace(Typeface.MONOSPACE)
//            .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
//            .indicatorColor(resources.getColor(R.color.colorPrimary))
//            .indicatorTextColor(resources.getColor(R.color.colorPrimary))
//            .indicatorTextSize(13) //sp
//            .thumbColor(resources.getColor(R.color.colorAccent, null))
//            .thumbSize(14)
//            .trackProgressColor(resources.getColor(R.color.colorAccent, null))
//            .trackProgressSize(4)
//            .trackBackgroundColor(resources.getColor(R.color.colorPrimary))
//            .trackBackgroundSize(2)
//            .onlyThumbDraggable(false)
//            .build()


//        seekBarDiscrete.setOnSeekChangeListener(object : OnSeekChangeListener {
//            override fun onSeeking(seekParams: SeekParams) {
//                Log.d("test", seekParams.seekBar.toString())
//                Log.d("test", seekParams.progress.toString())
//                Log.d("test", seekParams.progressFloat.toString())
//                Log.d("test", seekParams.fromUser.toString())
//                //when tick count > 0
//                Log.d("test", seekParams.thumbPosition.toString())
//                Log.d("test", seekParams.tickText)
//                Toast.makeText(this@QuestionSixActivity,seekParams.tickText,Toast.LENGTH_SHORT).show()
//            }
//            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar) {
//                Log.d("test", seekBar.toString())
//            }
//            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar) {
//                Log.d("test", seekBar.toString())
//            }
//        })

        question_view_next.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (setData()) {
                    resultLauncher.launch(
                        Intent(
                            this@QuestionSixActivity,
                            QuestionSevenActivity::class.java
                        )
                    )
                }
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

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                setResult(Activity.RESULT_OK, Intent())
                finish()
            }
        }

    private fun skipBTN() {
        resultLauncher.launch(Intent(this@QuestionSixActivity, QuestionSevenActivity::class.java))
    }

    private fun nextBTN() {
        SingletonClass.getInstance().questionsArrListData =
            SingletonClass.getInstance().questionsArrListData
        resultLauncher.launch(Intent(this@QuestionSixActivity, QuestionSevenActivity::class.java))
    }

    private fun setData(): Boolean {
//        if (type1Select.isEmpty()) {
//        } else {
//            arrList.add(
//                QuestionsObj(
//                    "41aa8ff3-a10a-4698-8362-e94092b2342a",
//                    "numberscale",
//                    type1Select,
//                    "string",
//                    "string"
//                )
//            )
//        }
        SingletonClass.getInstance()!!.questionsArrListData = questionsArrList
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == 2) {
            setResult(2, Intent())
            finish()
        }
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
                        Globals.AddMenualRecord(this@QuestionSixActivity)
                    }
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Globals.hideProgressDialog()
                }
            })
        }
    }

}