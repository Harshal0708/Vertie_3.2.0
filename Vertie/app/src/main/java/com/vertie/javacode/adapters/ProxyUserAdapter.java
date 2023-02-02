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
import com.vertie.javacode.models.ProxyUserModel;

import java.util.ArrayList;

public class ProxyUserAdapter extends ArrayAdapter<ProxyUserModel> {

    OnClickListner onClickListner;

    public ProxyUserAdapter(@NonNull Context context, ArrayList<ProxyUserModel> proxyUserModelArrayList) {
        super(context, 0, proxyUserModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        ProxyUserModel proxyUserModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.idTVCourse);
        ImageView courseIV = listitemView.findViewById(R.id.idIVcourse);

        courseTV.setText(proxyUserModel.getCourse_name());
        courseIV.setImageResource(proxyUserModel.getImgid());


        listitemView.setOnClickListener(view -> {
            onClickListner.onClickEvent(view,position, proxyUserModel);
        });

        return listitemView;
    }

    public  interface  OnClickListner {
        void onClickEvent(View view, int position, ProxyUserModel item);
    }

    public void setOnClickListner(OnClickListner onClickListner) {
        this.onClickListner = onClickListner;
    }

}
