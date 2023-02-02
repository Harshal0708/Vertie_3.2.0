package com.vertie.javacode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vertie.R;
import com.vertie.javacode.models.Mood;

import java.util.ArrayList;

public class MoodAdapter extends ArrayAdapter<Mood> {

    public MoodAdapter(Context context,
                       ArrayList<Mood> algorithmList) {
        super(context, 0, algorithmList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView,
                          ViewGroup parent) {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_mood, parent, false);
        }

        ImageView imgMood = convertView.findViewById(R.id.imgLanguage);
        TextView textViewName = convertView.findViewById(R.id.tvLanguage);
        Mood currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            imgMood.setImageResource(currentItem.getAvtarImg());
            textViewName.setText(currentItem.getType());
        }
        return convertView;
    }
}
