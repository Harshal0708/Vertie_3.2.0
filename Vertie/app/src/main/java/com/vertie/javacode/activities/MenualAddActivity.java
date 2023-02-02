package com.vertie.javacode.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.vertie.R;
import com.vertie.javacode.adapters.MoodAdapter;
import com.vertie.javacode.adapters.SpinnerAdapter;
import com.vertie.javacode.apiManager.APIManager;
import com.vertie.javacode.models.Mood;
import com.vertie.javacode.utility.Debugger;
import com.vertie.javacode.utility.Globals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenualAddActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    ArrayList<Mood> moodArray;
    MoodAdapter adapter;
    ConstraintLayout button_add_manual;

    Spinner moodSpinner;
    Spinner recoverySpinner;

    String moodSelect="",recoverySelect="";

    ArrayList<String> recovryAbilityArray = new ArrayList<String>(
            Arrays.asList("Low",
                    "Normal",
                    "Good",
                    "Excellant")
    );

    TextView count,count2;
    int tentationCount = 1;
    int pulseCount = 80;
    void setCount(int c){
        tentationCount = tentationCount + c;
        count.setText(""+tentationCount);
    }

    void setPulseCount(int c){
        pulseCount = pulseCount + c;
        count2.setText(""+pulseCount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_history_manual2);

        count = findViewById(R.id.tvCount);
        ImageView add = findViewById(R.id.imAdd);
        ImageView sub = findViewById(R.id.imSub);

        count2 = findViewById(R.id.tvCount2);
        ImageView add2 = findViewById(R.id.imAdd2);
        ImageView sub2 = findViewById(R.id.imSub2);

        findViewById(R.id.analyze1backbutton).setOnClickListener(view->{
            finish();
        });


        sub.setOnClickListener(v->{
            if(tentationCount==1){}else
            setCount(-1);
        });

        add.setOnClickListener(v->{
            if(tentationCount==100){}else
                setCount(1);
        });

        sub2.setOnClickListener(v->{
            if(pulseCount==1){}else
            setPulseCount(-1);
        });

        add2.setOnClickListener(v->{
            if(pulseCount==100){}else
            setPulseCount(1);
        });

        initList();

         moodSpinner = findViewById(R.id.moodSpinner);
         recoverySpinner = findViewById(R.id.recoverySpinner);

         button_add_manual = findViewById(R.id.button_add_manual);

        adapter = new MoodAdapter(this, moodArray);
        moodSpinner.setAdapter(adapter);

        SpinnerAdapter aa = new SpinnerAdapter(this,recovryAbilityArray);
        recoverySpinner.setAdapter(aa);
        moodSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {
                        Mood clickedItem = (Mood) parent.getItemAtPosition(position);
                        moodSelect = clickedItem.getType();
//                        Toast.makeText(MenualHistoryActivity.this, clickedItem.getType() + " selected", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        recoverySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {
                        recoverySelect = parent.getItemAtPosition(position).toString();
//                        Toast.makeText(MenualHistoryActivity.this, parent.getItemAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        button_add_manual.setOnClickListener(view1 -> {
            LocalDateTime ldt = LocalDateTime.now();
            String dateString=DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date= null;
            try {
                date = df.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayStr = date.toString().substring(0,3);
            df=new SimpleDateFormat("dd-MMMM-yyyy");
//            Debugger.debugE("TAG", "button clicked ..!!"+
//                    tentationSelect+"  "+
//                    recoverySelect+"  "+
//                    pulesSelect+"  "+
//                    moodSelect+"  "+dayStr+"DAY,"+df.format(date).replace("-"," ")
//            );
            AddMenualRecord(dayStr+"DAY,"+df.format(date).replace("-"," "));
        });
    }

    private void initList() {
        moodArray = new ArrayList<>();
        moodArray.add(new Mood("Happy",R.drawable.happy_mood));
        moodArray.add(new Mood("Good",R.drawable.good_mood));
        moodArray.add(new Mood("Okay",R.drawable.okay_mood));
        moodArray.add(new Mood("Sad",R.drawable.bad_mood));
    }

    private void AddMenualRecord(String todayDate){
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
        String user_email = getApplicationContext().getSharedPreferences("user", 0).getString("user_email",null);
        mapFields.put("tensionIndex", maptensionIndex);
        mapFields.put("recoveryAbility", maprecoveryAbility);
        mapFields.put("heartRate", mapheartRate);
        mapFields.put("mood", mapmood);
        mapFields.put("email", user_email);
        mapFields.put("date", todayDate.toUpperCase());
        mapFields.put("notes", "notes");


        Call<Object> call
                = APIManager
                .getUserManagerService(this, GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addMedicalRecords(mapFields);
        Globals.showProgressDialog(this);
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
                finish();
//                Toast.makeText(MenualHistoryActivity.this, body.get("resultMessage"), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Globals.hideProgressDialog();
                Debugger.debugE(TAG, "API Failed: " + t.getLocalizedMessage());
            }
        });
    }


}
