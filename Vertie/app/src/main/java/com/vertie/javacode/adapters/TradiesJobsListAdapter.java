//package com.vertie.javacode.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.vertie.R;
//import com.vertie.javacode.utility.Constant;
//
//import java.util.ArrayList;
//
//public class TradiesJobsListAdapter extends RecyclerView.Adapter<TradiesJobsListAdapter.ViewHolder> {
//
//    Context context;
//    ArrayList<Jobs> jobs;
//    OnClickListner onClickListner;
//
//    String tag;
//
//    public TradiesJobsListAdapter(Context context, ArrayList<Jobs> jobs, String tag) {
//        this.context = context;
//        this.jobs = jobs;
//        this.tag = tag;
//    }
//
//    public  interface  OnClickListner {
//        void onClickEvent(View view,int position,Jobs jobs);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_offers_list,parent,false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        holder.tvHeading.setText(jobs.get(position).title +" - $"+jobs.get(position).price);
//        holder.tvDescription.setText(jobs.get(position).address);
//        holder.tvDate.setText(jobs.get(position).start_date+" "+jobs.get(position).start_time);
//
//
//        if(tag.equals(Constant.MM_MODEL_EMPLOYER))
//        {
//            holder.tvStatus.setText(jobs.get(position).status);
//            if(jobs.get(position).job_status.equals(Constant.MM_JOB_STATUS_SECURED)){
//                holder.tvStatus.setBackgroundResource(R.drawable.btn_background);
//
//            }else if(jobs.get(position).job_status.equals(Constant.MM_JOB_STATUS_OPENED)){
//                holder.tvStatus.setBackgroundResource(R.drawable.btn_background_open);
//
//            }else
//                holder.tvStatus.setBackgroundResource(R.drawable.dialog_corner_shap);
//        }
//
//        else if(tag.equals(Constant.MM_MODEL_TRADIE))
//        {
//
//            if(jobs.get(position).job_status.equals(Constant.MM_JOB_STATUS_COMPLETED)
//                    || jobs.get(position).job_status.equals(Constant.MM_JOB_STATUS_CANCELLED)){
//                holder.tvStatus.setText(jobs.get(position).job_status);
//            }else {
//                holder.tvStatus.setText(jobs.get(position).status);
//            }
//
//            if(jobs.get(position).status.equals(Constant.MM_STATUS_ACCEPTED)
//                    || jobs.get(position).status.equals(Constant.MM_STATUS_INVITED)
//                    || jobs.get(position).status.equals(Constant.MM_STATUS_OPENED)){
//                holder.tvStatus.setBackgroundResource(R.drawable.btn_background_open);
//
//            }
//            else if(jobs.get(position).job_status.equals(Constant.MM_STATUS_COMPLETED)){
//            holder.tvStatus.setBackgroundResource(R.drawable.btn_background_secured);
//            }
//
//            else if(jobs.get(position).status.equals(Constant.MM_STATUS_CANCELLED)
//                    || jobs.get(position).status.equals(Constant.MM_STATUS_REJECTED)
//                    || jobs.get(position).job_status.equals(Constant.MM_JOB_STATUS_CANCELLED)){
//                holder.tvStatus.setBackgroundResource(R.drawable.btn_backgroung_cancel);
//            }
//
//            else {
//                holder.tvStatus.setBackgroundResource(R.drawable.btn_background);
//            }
//
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return jobs.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        public TextView tvHeading;
//        public TextView tvDescription;
//        public TextView tvDate;
//        public TextView tvStatus;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            tvHeading = itemView.findViewById(R.id.tvMainHeading);
//            tvDescription = itemView.findViewById(R.id.tvDescription);
//            tvDate = itemView.findViewById(R.id.tvDate);
//            tvStatus = itemView.findViewById(R.id.tvStatus);
//
//            itemView.setOnClickListener(view -> {
//                onClickListner.onClickEvent(view,getAdapterPosition(),jobs.get(getAdapterPosition()));
//            });
//
//        }
//    }
//
//    public void setOnClickListner(OnClickListner onClickListner) {
//        this.onClickListner = onClickListner;
//    }
//}
