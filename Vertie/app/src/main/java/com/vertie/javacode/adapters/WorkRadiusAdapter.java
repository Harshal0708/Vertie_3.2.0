//package com.vertie.javacode.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RadioButton;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.vertie.R;
//import com.vertie.javacode.singleton.SingletonClass;
//
//
//public class WorkRadiusAdapter extends RecyclerView.Adapter<WorkRadiusAdapter.RecyclerViewHolder> {
//
//    public static  WorkRadius[] arrayList;
//    public Context context;
//    public int selectedPosition = -1;
//
//    public WorkRadiusAdapter(Context context, WorkRadius[] listWorkRadius) {
//        this.arrayList = listWorkRadius;
//        this.context = context;
//    }
//
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_custom_row_layout, viewGroup, false);
//        return new RecyclerViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerViewHolder holder, final int i) {
//
//        holder.label.setText(arrayList[i].name);
//
//        //check the radio button if both position and selectedPosition matches
//        holder.radioButton.setChecked(i == selectedPosition);
//
//        //Set the position tag to both radio button and label
//        holder.radioButton.setTag(i);
//        holder.label.setTag(i);
//
//        try {
//            String selectIndustry = SingletonClass.getInstance().selectedWorkRadius;
//            if(selectIndustry.equals(arrayList[i].name))
//            {
//                holder.radioButton.setChecked(true);
//            }else {
//
//            }
//        }catch (NullPointerException e)
//        {
//
//        }
//
//        holder.radioButton.setOnClickListener(v -> {
//            itemCheckChanged(v);
//            getSelectedItem();
//        });
//
//        holder.label.setOnClickListener(v -> {
//            itemCheckChanged(v);
//            getSelectedItem();
//        });
//    }
//
//    //On selecting any view set the current position to selectedPositon and notify adapter
//    public void itemCheckChanged(View v) {
//        selectedPosition = (Integer) v.getTag();
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return (null != arrayList ? arrayList.length : 0);
//    }
//
//    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView label;
//        private RadioButton radioButton;
//
//        RecyclerViewHolder(View view) {
//            super(view);
//            label = view.findViewById(R.id.label);
//            radioButton = view.findViewById(R.id.radio_button);
//        }
//    }
//
//    //Return the selectedPosition item
//    public String getSelectedItem() {
//        if (selectedPosition != -1) {
//            SingletonClass.getInstance().selectedWorkRadius = arrayList[selectedPosition].name;
//            //Toast.makeText(context, arrayList[selectedPosition].name, Toast.LENGTH_SHORT).show();
//            return arrayList[selectedPosition].name;
//        }
//        return "";
//    }
//
//    //Delete the selected position from the arrayList
//    public void deleteSelectedPosition() {
//        if (selectedPosition != -1) {
//            //arrayList.remove(selectedPosition);
//            selectedPosition = -1;//after removing selectedPosition set it back to -1
//            notifyDataSetChanged();
//        }
//    }
//
//
//}