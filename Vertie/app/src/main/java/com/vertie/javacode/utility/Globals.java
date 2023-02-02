package com.vertie.javacode.utility;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.core.content.ContextCompat;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.vertie.javacode.apiManager.APIManager;
import com.vertie.javacode.singleton.SingletonClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Globals extends Application {

    private static ProgressDialog progressDialog;

    /* Show progress dialog. */
    public static void showProgressDialog(Activity context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    /* Hide progress dialog. */
    public static void hideProgressDialog() {
        // Close it.
        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    void setRBSelectedBGcolour(RadioButton rb){
        if (rb.isChecked()) {
//            rb.setBackgroundColor(getResources().getColor(R.color.select_bg));
        }
    }

    void setCBSelectedBGcolour(CheckBox cb){
        if (cb.isChecked()) {
//            cb.setBackgroundColor(getResources().getColor(R.color.select_bg));
        }
        else {
//            cb.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        }
    }

    public static Dialog dialog;
    public static final void showDialog(Context context, int dilogeResourceLayout, View.OnClickListener clickListener, int[] buttonList){

        dialog = new Dialog(context);
        dialog.setContentView(dilogeResourceLayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        for(int i=0; i<buttonList.length ; i++) {

            if(clickListener != null)
                dialog.findViewById(buttonList[i]).setOnClickListener(clickListener);
        }
    }

    public static final void closeDiloge(){
        dialog.dismiss();
    }

    //SHAREDPREFRENCES DATA
    public static final String[] MONTHS1 = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    public static String converteddate;

    //convert date formet yyyy-MM-dd To dd-MMM-yyyy
    public static String DateFormet(String date) throws ParseException {
        if(date.equals("0000-00-00"))
            return "Invalid Date";
        else if(date.length()!=10)
            return "Invalid Date";
        else
        {
            String d=""+date.charAt(8)+date.charAt(9);
            String m=""+date.charAt(5)+date.charAt(6);
            String y=""+date.charAt(0)+date.charAt(1)+date.charAt(2)+date.charAt(3);

            String mo = MONTHS1[Integer.parseInt(m)-1];

            String input_date= d+"/"+m+"/"+y;
            SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
            converteddate = d +" "+mo+" "+y;
        }
        return converteddate;
    }

    public static ArrayList<String> convertStringIntoStringArray(String string)
    {
        int start = 0,end;
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(int i=0; i<string.length(); i++)
        {
            char c = string.charAt(i);
            if(c == ',')
            {
                end = i;
                String subString = string.substring(start,end);
                start = end+1;
                stringArrayList.add(subString);
            }
        }
        stringArrayList.add(string.substring(start));

        return stringArrayList;
    }

    public static boolean read_EXTERNAL_STORAGE(Context context){
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else return false;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static boolean stringIsEmpty(String str){
        if(str==null || str.isEmpty() || str.equals("null"))
            return true;
        else
            return false;
    }

    public static String dateInToDDMMYYYY(String date){
        String string = "2018-04-10T04:00:00.000Z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        LocalDate date1 = LocalDate.parse(date, formatter);
//        System.out.println(date1);
        SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = sm.format(date1);
        return strDate;
    }

    public static String getDayBy(int d){
        String day = "Monday";
        switch (d){
            case 1:
                day= "Sunday";
                break;
            case 2:
                day= "Monday";
                break;
            case 3:
                day= "Tuesday";
                break;
            case 4:
                day= "Wednesday";
                break;
            case 5:
                day= "Thursday";
                break;
            case 6:
                day= "Friday";
                break;
            case 7:
                day= "Saturday";
                break;
        }
        return day;
    }

    public static void AddMenualRecord(Context context){
        LocalDateTime ldt = LocalDateTime.now();
        String dateString = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayStr = getDayBy(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));//date.toString().substring(0,3);
        df=new SimpleDateFormat("dd-MM-yyyy");
        String todayDate = dayStr.toUpperCase()+", "+df.format(date).replace("-"," ").toUpperCase();

        String TAG = "add record :: ";
        int tentationCount = 1;
        int pulseCount = 80;
        String moodSelect = "Happy";
        String recoverySelect = "Low";

        HashMap<String, String> maptensionIndex = new HashMap<>();
        maptensionIndex.put("value", ""+tentationCount); //1
        maptensionIndex.put("status", ""+tentationCount);
        maptensionIndex.put("description", ""+tentationCount);
        HashMap<String, String> maprecoveryAbility = new HashMap<>();
        maprecoveryAbility.put("status", recoverySelect);
        maprecoveryAbility.put("description", recoverySelect); //Low
        HashMap<String, String> mapheartRate = new HashMap<>();
        String pulseStatus;
        if(pulseCount>=1 && pulseCount<20){ //80
            pulseStatus = "Low";
        }else if(pulseCount>=20 && pulseCount<60){
            pulseStatus = "Medium";
        }else if(pulseCount>=60 && pulseCount<100){
            pulseStatus = "High";
        }else {
            pulseStatus = "Non";
        }
        mapheartRate.put("status", ""+pulseCount);
        mapheartRate.put("description", pulseStatus);
        HashMap<String, String> mapmood = new HashMap<>();
        mapmood.put("status", moodSelect); //Happy

        HashMap<String, Object> mapFields = new HashMap<>();
        String user_email = context.getApplicationContext().getSharedPreferences("user", 0).getString("user_email",null);
        mapFields.put("tensionIndex", maptensionIndex);
        mapFields.put("recoveryAbility", maprecoveryAbility);
        mapFields.put("heartRate", mapheartRate);
        mapFields.put("mood", mapmood);
        mapFields.put("email", user_email);
        mapFields.put("date", todayDate.toUpperCase());
        mapFields.put("notes", "notes");

        Call<Object> call
                = APIManager
                .getUserManagerService(context, GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addMedicalRecords(mapFields);
        Globals.showProgressDialog((Activity) context);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                LinkedTreeMap<String,String> body = (LinkedTreeMap<String, String>) response.body();
                Debugger.debugD(TAG, response.toString());
                Globals.hideProgressDialog();
                if (!response.isSuccessful()) {
                    Debugger.debugE(TAG, "API status code : " + response.code());
                  return;
                }
                Intent intent = new Intent();
                ((Activity) context).setResult(Activity.RESULT_OK, intent);
                ((Activity) context).finish();
                SingletonClass.getInstance().questionsArrListData=null;
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Globals.hideProgressDialog();
                Debugger.debugE(TAG, "API Failed: " + t.getLocalizedMessage());
//                return false;
            }
        });
    }


}