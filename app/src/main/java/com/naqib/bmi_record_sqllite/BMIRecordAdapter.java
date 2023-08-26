package com.naqib.bmi_record_sqllite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BMIRecordAdapter extends RecyclerView.Adapter<BMIRecordAdapter.ViewHolder> {

    private List<BMIRecord> bmiRecordList;

    public BMIRecordAdapter(List<BMIRecord> bmiRecordList) {
        this.bmiRecordList = bmiRecordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bmi_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BMIRecord bmiRecord = bmiRecordList.get(position);

        holder.dateTextView.setText("Date : " + bmiRecord.getDate());
        holder.weightTextView.setText("Weight : " + bmiRecord.getWeight());
        holder.heightTextView.setText("Height : " + bmiRecord.getHeight());
        holder.bmiTextView.setText("BMI : " + bmiRecord.getBMI());
        holder.statusTextView.setText("BMI Status : " + bmiRecord.getStatus());
    }

    @Override
    public int getItemCount() {
        return bmiRecordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView weightTextView;
        TextView heightTextView;
        TextView bmiTextView;
        TextView statusTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.tvBMIDate);
            weightTextView = itemView.findViewById(R.id.tvBMIWeight);
            heightTextView = itemView.findViewById(R.id.tvBMIHeight);
            bmiTextView = itemView.findViewById(R.id.tvBMI);
            statusTextView = itemView.findViewById(R.id.tvBMIDesc);
        }
    }
}
