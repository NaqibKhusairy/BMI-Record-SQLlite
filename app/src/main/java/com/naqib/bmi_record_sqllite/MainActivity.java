package com.naqib.bmi_record_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = findViewById(R.id.button);

        enter.setOnClickListener(view -> {
            Intent x = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(x);
        });
    }
}