package com.vertie.javacode.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vertie.R;

public class MeasurementIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        findViewById(R.id.goBack).setOnClickListener(view->{
            finish();
        });
    }
}