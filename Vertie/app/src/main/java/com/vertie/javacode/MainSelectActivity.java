package com.vertie.javacode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vertie.R;
public class MainSelectActivity extends AppCompatActivity {

    private RecyclerView rvWorkRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select);

        rvWorkRadius = findViewById(R.id.rvWorkRadius);

        ListItem[] listListItems = {
                new ListItem("1","1wr name","wr desc",false),
                new ListItem("2","2wr name","wr desc",false),
                new ListItem("3","3wr name","wr desc",false),
                new ListItem("4","4wr name","wr desc",false)
        };
        responceBodyforWorkRadiusList(listListItems);
    }

    private void responceBodyforWorkRadiusList(ListItem[] listListItems) {
//        rvWorkRadius.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvWorkRadius.setHasFixedSize(true);
        rvWorkRadius.setLayoutManager(new LinearLayoutManager(this));
        ListItemSelectionAdapter listItemSelectionAdapter = new ListItemSelectionAdapter(this, listListItems);
        rvWorkRadius.setAdapter(listItemSelectionAdapter);
    }

}