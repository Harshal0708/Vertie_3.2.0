package com.vertie.javacode;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.vertie.R;
import com.vertie.javacode.singleton.SingletonClass;
public class ListItemSelectionAdapter extends RecyclerView.Adapter<ListItemSelectionAdapter.RecyclerViewHolder> {

    public static  ListItem[] arrayList;
    public Context context;
    public int selectedPosition = -1;
    static OnClickListner onClickListner;
    public ListItemSelectionAdapter(Context context, ListItem[] listListItems) {
        this.arrayList = listListItems;
        this.context = context;
    }

    public  interface  OnClickListner {
        void onClickEvent(View view,int position,ListItem item);
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_custom_row_layout, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int i) {

        holder.label.setText(arrayList[i].name);
        if(i == selectedPosition){
            holder.selectedItem.setVisibility(View.VISIBLE);
            holder.listItem.setBackgroundResource(R.drawable.question_background_select_2);
        }else {
            holder.selectedItem.setVisibility(View.GONE);
            holder.listItem.setBackgroundResource(R.drawable.question_background_1);
        }

        //Set the position tag to both radio button and label
        holder.selectedItem.setTag(i);
        holder.label.setTag(i);
        try {
            String selectIndustry = SingletonClass.getInstance().selectedStep5;
            if(selectIndustry.equals(arrayList[i].name))
            {
                holder.selectedItem.setVisibility(View.VISIBLE);
                holder.listItem.setBackgroundResource(R.drawable.question_background_select_2);
            }else {

            }
        }catch (NullPointerException e)
        {

        }

        holder.selectedItem.setOnClickListener(v -> {
            itemCheckChanged(v);
            getSelectedItem();
            onClickListner.onClickEvent(v,i,arrayList[i]);
        });

        holder.label.setOnClickListener(v -> {
            itemCheckChanged(v);
            getSelectedItem();
            onClickListner.onClickEvent(v,i,arrayList[i]);
        });

    }

    //On selecting any view set the current position to selectedPositon and notify adapter
    public void itemCheckChanged(View v) {
        selectedPosition = (Integer) v.getTag();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.length : 0);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView label;
        private ImageView selectedItem;
        private ConstraintLayout listItem;
        RecyclerViewHolder(View view) {
            super(view);
            label = view.findViewById(R.id.text);
            selectedItem = view.findViewById(R.id.img);
            listItem = view.findViewById(R.id.question_two_cons_1);
        }
    }

    //Return the selectedPosition item
    public String getSelectedItem() {
        if (selectedPosition != -1) {
//            SingletonClass.getInstance().selectedStep5 = arrayList[selectedPosition].name;
            //Toast.makeText(context, arrayList[selectedPosition].name, Toast.LENGTH_SHORT).show();
            return arrayList[selectedPosition].name;
        }
        return "";
    }

    //Delete the selected position from the arrayList
    public void deleteSelectedPosition() {
        if (selectedPosition != -1) {
            //arrayList.remove(selectedPosition);
            selectedPosition = -1;//after removing selectedPosition set it back to -1
            notifyDataSetChanged();
        }
    }
    public void setOnClickListner(OnClickListner onClickListner) {
        this.onClickListner = onClickListner;
    }
}