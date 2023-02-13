package com.vertie.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.vertie.R;
import com.vertie.javacode.ListItem;
import com.vertie.javacode.ListItemSelectionAdapter;

import java.util.ArrayList;

public class BottomSheetDialog extends BottomSheetDialogFragment
        implements ListItemSelectionAdapter.OnClickListner
{

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
    ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);

        Button algo_button = v.findViewById(R.id.algo_button);
        recyclerView = v.findViewById(R.id.rvStep7);

        ListItem[] a = {
                new ListItem("1", "Burning", "", false),
                new ListItem("2", "Going to the bathroom mor", "", false),
                new ListItem("3", "Blood in urine", "", false),
                new ListItem("4", "Difficulty urinating", "", false)
        };

        ListItemSelectionAdapter adapter = new ListItemSelectionAdapter(getActivity(),a);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListner((ListItemSelectionAdapter.OnClickListner) getActivity());

        algo_button.setOnClickListener(v1 -> {
            Toast.makeText(getActivity(),
                            "Algorithm Shared", Toast.LENGTH_SHORT)
                    .show();
            dismiss();
        });

        return v;
    }

    @Override
    public void onClickEvent(View view, int position, ListItem item) {
        
    }

//    @Override
//    public void onClickEvent(View view, int position, ListItem item) {
//        Log.d("aa :: ","aa : "+position);
//    }
}
