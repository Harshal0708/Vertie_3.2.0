package com.vertie.modules.dashboard.historyfragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vertie.R
import com.vertie.data.medicalRecord.MedicalRecord
import com.vertie.data.medicalRecord.TensionIndex
import com.vertie.databinding.MedicalRecordBinding
import com.vertie.javacode.models.Mood
import javax.inject.Inject

class HistoryAdapter @Inject constructor(private val viewModel: HistoryViewModel, private val con : Context,private val data : List<MedicalRecord>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var medicalRecords = data

    @SuppressLint("NotifyDataSetChanged")
    fun updateMedicalRecords(medicalRecords : List<MedicalRecord>) {
        this.medicalRecords = medicalRecords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        return ViewHolder( MedicalRecordBinding
            .inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.bind(medicalRecords[position],con)
    }

    override fun getItemCount(): Int {
        return medicalRecords.size
    }

    inner class ViewHolder(private val itemviewbinding : MedicalRecordBinding) : RecyclerView.ViewHolder(itemviewbinding.root){
        fun bind(item : MedicalRecord,context : Context){
            if(item.tensionIndex == null){
                item.tensionIndex = TensionIndex(0,"0","","")
            }else {
                item.tensionIndex.valueString = (item.tensionIndex.value.toString() ?: "") + "/100"
            }
//            moodArray.add(Mood("Happy", R.drawable.happy_mood))
//            moodArray.add(Mood("Good", R.drawable.good_mood))
//            moodArray.add(Mood("Okay", R.drawable.okay_mood))
//            moodArray.add(Mood("Sad", R.drawable.bad_mood))
//            medicalRecord.mood.status
            itemviewbinding.medicalRecord = item
//            if(item.mood.status.equals("Okay")){
//                itemviewbinding.fourth.setBackgroundResource(R.drawable.okay_mood);
//            }else if(item.tensionIndex.value.equals("Good")){
//                itemviewbinding.fourth.setBackgroundResource(R.drawable.good_mood);
//            }else if(item.tensionIndex.value.equals("Happy")){
//                itemviewbinding.fourth.setBackgroundResource(R.drawable.ic_happy_mood);
//            }else if(item.tensionIndex.value.equals("Sad")){
//                itemviewbinding.fourth.setBackgroundResource(R.drawable.bad_mood);
//            }else{
//                itemviewbinding.fourth.setBackgroundResource(R.drawable.ic_happy_mood);
//            }
            itemviewbinding.executePendingBindings()
        }
    }
}