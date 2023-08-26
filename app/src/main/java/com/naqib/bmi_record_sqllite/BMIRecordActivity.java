package com.naqib.bmi_record_sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class BMIRecordActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BMIRecordAdapter adapter;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_record);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHandler = new DBHandler(this);

        List<BMIRecord> bmiRecordList = dbHandler.getAllBMIRecords();
        adapter = new BMIRecordAdapter(bmiRecordList);
        recyclerView.setAdapter(adapter);
    }
}