package com.vertie.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import java.io.ByteArrayOutputStream

class QuestionSevenActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 200

    private lateinit var icon1: ImageView
    private lateinit var icon2: ImageView

    private var pickCamera: Int = 2
    private var pickCamera2: Int = 3

    private lateinit var question_view: View
    private lateinit var question_view_text: TextView

    private lateinit var question_two_cons_1: ConstraintLayout
    private lateinit var question_two_cons_2: ConstraintLayout
    private lateinit var question_two_cons_3: ConstraintLayout
    private lateinit var question_two_cons_4: ConstraintLayout
    private lateinit var question_two_cons_5: ConstraintLayout

    private lateinit var question_view_img_1: ImageView
    private lateinit var question_view_img_2: ImageView
    private lateinit var question_view_img_3: ImageView
    private lateinit var question_view_img_4: ImageView
    private lateinit var question_view_img_5: ImageView

    private lateinit var question_two_date_1: TextView
    private lateinit var question_two_date_2: TextView
    private lateinit var question_two_date_3: TextView
    private lateinit var question_two_date_4: TextView
    private lateinit var question_two_date_5: TextView

    var questionsArrList = arrayListOf<QuestionsObj>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_seven)

        questionsArrList = SingletonClass.getInstance()!!.questionsArrListData

        Init()

        question_view = findViewById(R.id.question_view)
        question_view_text = question_view.findViewById(R.id.question_view_text)
        question_view_text.text = Constants.step7

        findViewById<Button>(R.id.question_view_finish2).setOnClickListener {
            if (setData()) {
                apiCall()
            }
        }

        findViewById<ImageView>(R.id.question_view_img).setOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.question_two_txt_name3).setOnClickListener {
            startActivity(Intent(this@QuestionSevenActivity,QuestionSevenIntroActivity::class.java))
        }


        icon1.setOnClickListener {
            val gallery = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(gallery, pickCamera)
        }



        icon2.setOnClickListener {
            val gallery = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(gallery, pickCamera2)
        }

        if (checkPermission()) {

        } else {
            requestPermission();
        }
    }

//    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//
//        }else if (result.resultCode == Activity.RESULT_OK) {
//
//        }
//
////        if (requestCode == pickCamera) {
////            try {
////
////                photo = data?.extras?.get("data") as Bitmap
////                encodeBitmapImage(photo,1)
////                icon1.setImageBitmap(photo)
////            } catch (ex: Exception) {
////            }
////        } else if (requestCode == pickCamera2) {
////            try {
////                photo2 = data?.extras?.get("data") as Bitmap
////                encodeBitmapImage(photo2,2)
////                icon2.setImageBitmap(photo2)
////            } catch (ex: Exception) {
////            }
////        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickCamera) {
            try {

                photo = data?.extras?.get("data") as Bitmap
                encodeBitmapImage(photo,1)
                icon1.setImageBitmap(photo)
            } catch (ex: Exception) {
            }
        } else if (requestCode == pickCamera2) {
            try {
                photo2 = data?.extras?.get("data") as Bitmap
                encodeBitmapImage(photo2,2)
                icon2.setImageBitmap(photo2)
            } catch (ex: Exception) {
            }
        }

    }

    private fun checkPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            false
        } else true
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                    ) {
                        showMessageOKCancel(
                            "You need to allow access permissions"
                        ) { dialog, which ->
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermission()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@QuestionSevenActivity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private lateinit var type1Select: String

    private fun Init() {

        type1Select = ""

        question_two_cons_1 = findViewById(R.id.question_cons_1)
        question_two_cons_2 = findViewById(R.id.question_cons_2)
        question_two_cons_3 = findViewById(R.id.question_cons_3)
        question_two_cons_4 = findViewById(R.id.question_cons_4)
        question_two_cons_5 = findViewById(R.id.question_cons_5)

        question_view_img_1 = findViewById(R.id.question_view_img_1)
        question_view_img_2 = findViewById(R.id.question_view_img_2)
        question_view_img_3 = findViewById(R.id.question_view_img_3)
        question_view_img_4 = findViewById(R.id.question_view_img_4)
        question_view_img_5 = findViewById(R.id.question_view_img_5)

        question_two_date_1 = findViewById(R.id.question_1)
        question_two_date_2 = findViewById(R.id.question_2)
        question_two_date_3 = findViewById(R.id.question_3)
        question_two_date_4 = findViewById(R.id.question_4)
        question_two_date_5 = findViewById(R.id.question_5)

        icon1 = findViewById<ImageView>(R.id.icon1)
        icon2 = findViewById<ImageView>(R.id.icon2)

        question_two_cons_1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_1.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_1.visibility = View.VISIBLE
                question_two_date_1.setTextColor(getColor(R.color.textColor))

                question_two_cons_2.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_2.visibility = View.GONE
                question_two_date_2.setTextColor(getColor(R.color.color231F20))

                question_two_cons_3.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_3.visibility = View.GONE
                question_two_date_3.setTextColor(getColor(R.color.color231F20))

                question_two_cons_4.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_4.visibility = View.GONE
                question_two_date_4.setTextColor(getColor(R.color.color231F20))

                question_two_cons_5.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_5.visibility = View.GONE
                question_two_date_5.setTextColor(getColor(R.color.color231F20))

                type1Select = "Hot to touch"
            }
        })

        question_two_cons_2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_2.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_2.visibility = View.VISIBLE
                question_two_date_2.setTextColor(getColor(R.color.textColor))

                question_two_cons_1.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_1.visibility = View.GONE
                question_two_date_1.setTextColor(getColor(R.color.color231F20))

                question_two_cons_3.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_3.visibility = View.GONE
                question_two_date_3.setTextColor(getColor(R.color.color231F20))

                question_two_cons_4.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_4.visibility = View.GONE
                question_two_date_4.setTextColor(getColor(R.color.color231F20))

                question_two_cons_5.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_5.visibility = View.GONE
                question_two_date_5.setTextColor(getColor(R.color.color231F20))

                type1Select = "Painful and hurts"
            }

        })

        question_two_cons_3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_3.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_3.visibility = View.VISIBLE
                question_two_date_3.setTextColor(getColor(R.color.textColor))

                question_two_cons_1.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_1.visibility = View.GONE
                question_two_date_1.setTextColor(getColor(R.color.color231F20))

                question_two_cons_2.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_2.visibility = View.GONE
                question_two_date_2.setTextColor(getColor(R.color.color231F20))

                question_two_cons_4.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_4.visibility = View.GONE
                question_two_date_4.setTextColor(getColor(R.color.color231F20))

                question_two_cons_5.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_5.visibility = View.GONE
                question_two_date_5.setTextColor(getColor(R.color.color231F20))

                type1Select = "Swollen"

            }

        })

        question_two_cons_4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_4.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_4.visibility = View.VISIBLE
                question_two_date_4.setTextColor(getColor(R.color.textColor))

                question_two_cons_1.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_1.visibility = View.GONE
                question_two_date_1.setTextColor(getColor(R.color.color231F20))

                question_two_cons_2.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_2.visibility = View.GONE
                question_two_date_2.setTextColor(getColor(R.color.color231F20))

                question_two_cons_3.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_3.visibility = View.GONE
                question_two_date_3.setTextColor(getColor(R.color.color231F20))

                question_two_cons_5.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_5.visibility = View.GONE
                question_two_date_5.setTextColor(getColor(R.color.color231F20))

                type1Select = "Bleeding"
            }
        })

        question_two_cons_5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                question_two_cons_5.setBackgroundResource(R.drawable.question_background_select_2)
                question_view_img_5.visibility = View.VISIBLE
                question_two_date_5.setTextColor(getColor(R.color.textColor))

                question_two_cons_1.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_1.visibility = View.GONE
                question_two_date_1.setTextColor(getColor(R.color.color231F20))

                question_two_cons_2.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_2.visibility = View.GONE
                question_two_date_2.setTextColor(getColor(R.color.color231F20))

                question_two_cons_3.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_3.visibility = View.GONE
                question_two_date_3.setTextColor(getColor(R.color.color231F20))

                question_two_cons_4.setBackgroundResource(R.drawable.question_background_1)
                question_view_img_4.visibility = View.GONE
                question_two_date_4.setTextColor(getColor(R.color.color231F20))

                type1Select = "Draining pus or fluids"
            }
        })

    }

    private var imageUri: Uri? = null
    lateinit var photo: Bitmap
    lateinit var photo2: Bitmap
    lateinit var bytesofimage: ByteArray
    lateinit var bytesofimage2: ByteArray
    var encodeImageString: String = ""
    var encodeImageString2: String = ""

    private fun encodeBitmapImage(bitmap: Bitmap,i:Int) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        if(i==1){
            bytesofimage = byteArrayOutputStream.toByteArray()
            encodeImageString = Base64.encodeToString(bytesofimage, Base64.DEFAULT)
        }else if(i==2){
            bytesofimage2 = byteArrayOutputStream.toByteArray()
            encodeImageString2 = Base64.encodeToString(bytesofimage2, Base64.DEFAULT)
        }
        //   showLog(encodeImageString.toString())
    }

    private fun setData(): Boolean {
        if (encodeImageString.isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id71,
                    "file",
                    "string",
                    "string",
                    encodeImageString
                )
            )
        }
        if (encodeImageString2.isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id72,
                    "file",
                    "string",
                    "string",
                    encodeImageString2
                )
            )
        }
        if (type1Select.isEmpty()) {
        } else {
            questionsArrList.add(
                QuestionsObj(
                    Constants.id73,
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
            setResult(Activity.RESULT_OK, Intent())
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
//                        setResult(Activity.RESULT_OK, Intent())
//                        finish()
//                        SingletonClass.getInstance()!!.questionsArrListData=null
                        Globals.AddMenualRecord(this@QuestionSevenActivity)
                    }
                }
                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Globals.hideProgressDialog()
                }
            })
        }
    }

}