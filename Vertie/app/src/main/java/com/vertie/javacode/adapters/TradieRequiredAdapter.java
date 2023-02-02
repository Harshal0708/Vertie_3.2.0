//package com.vertie.javacode.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.vertie.R;
//
//
//public class TradieRequiredAdapter extends RecyclerView.Adapter<TradieRequiredAdapter.ViewHolder> {
//
//    private Context context;
//    public static TradieRequired[] tradieRequireds;
//
//    public TradieRequiredAdapter(Context context, TradieRequired[] tradieRequireds) {
//        this.context = context;
//        this.tradieRequireds = tradieRequireds;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tradie_required_checkbox,parent,false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        holder.checkBox.setChecked(tradieRequireds[position].isSelected());
//        holder.tv.setText(tradieRequireds[position].name);
//
//        holder.checkBox.setTag(position);
//        holder.checkBox.setOnClickListener(v -> {
//            Integer pos = (Integer) holder.checkBox.getTag();
//            if (tradieRequireds[position].isSelected()) {
//                tradieRequireds[position].setSelected(false);
//            } else {
//                tradieRequireds[position].setSelected(true);
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return tradieRequireds.length;
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        protected CheckBox checkBox;
//        private TextView tv;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            checkBox = itemView.findViewById(R.id.cb);
//            tv = itemView.findViewById(R.id.tv);
//        }
//    }
//
//}
