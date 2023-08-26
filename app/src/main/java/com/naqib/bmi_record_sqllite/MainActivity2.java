package com.naqib.bmi_record_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText weight, height;
    Button callSave, bmiRec;
    CalendarView calendarView;
    String selectedDate;
    DBHandler dbHandler;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        weight = findViewById(R.id.etWeight);
        height = findViewById(R.id.etHeight);
        callSave = findViewById(R.id.btnCalSave);
        bmiRec = findViewById(R.id.btnView);
        calendarView = findViewById(R.id.cvDate);
        dbHandler = new DBHandler(this);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String curDate = String.valueOf(dayOfMonth);
            String Year = String.valueOf(year);
            String Month = String.valueOf(month+1);

            Log.e("date", curDate + "/" + Month + "/" + Year);
            selectedDate = curDate + "/" + Month + "/" + Year;
        });

        callSave.setOnClickListener(v -> {
            String weightStr = weight.getText().toString();
            String heightStr = height.getText().toString();
            if(selectedDate != null){
                try {
                    double weightValue = Double.parseDouble(weightStr);
                    double heightValue = Double.parseDouble(heightStr);String WeightValue = String.format("%.2f", weightValue);
                    String HeightValue = String.format("%.2f", heightValue);

                    double bmi = weightValue / (heightValue * heightValue);
                    String BMI = String.format("%.2f", bmi);

                    String bmiStatus = "";
                    if (bmi < 18.5) {
                        bmiStatus = "Underweight";
                    } else if (bmi >= 18.5 && bmi <= 24.9) {
                        bmiStatus = "Normal Weight";
                    } else if (bmi >= 25.0 && bmi <= 29.9) {
                        bmiStatus = "Overweight";
                    } else if (bmi >= 30.0 && bmi <= 34.9) {
                        bmiStatus = "Obesity class I";
                    } else if (bmi >= 35.0 && bmi <= 39.9) {
                        bmiStatus = "Obesity class II";
                    } else if (bmi >= 40) {
                        bmiStatus = "Obesity class III";
                    }

                    dbHandler.addRecord(selectedDate, Double.parseDouble(WeightValue), Double.parseDouble(HeightValue), Double.parseDouble(BMI), bmiStatus);

                    Toast.makeText(getApplicationContext(), "Your BMI is " + BMI + ", and your BMI data is " + bmiStatus + "\nSuccessfully saved!", Toast.LENGTH_SHORT).show();
                    weight.setText("");
                    height.setText("");
                } catch (Exception e) {
                    BmiError();
                }
            }
            else{
                BmiError();
            }
        });

        bmiRec.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BMIRecordActivity.class);
            startActivity(intent);
        });
    }
    public void BmiError(){
        if((weight.getText().toString()).equals("")&&(height.getText().toString()).equals("")){
            weight.setError("Please Enter Weight (kg)");
            height.setError("Please Enter Height (kg)");
        }
        else if((weight.getText().toString()).equals("")){
            weight.setError("Please Enter Weight (kg)");
        }
        else if((height.getText().toString()).equals("")){
            height.setError("Please Enter Weight (kg)");
        }
        if (selectedDate == null){
            Toast.makeText(getApplicationContext(),"Please Choose the Date",Toast.LENGTH_LONG).show();
        }
    }
}