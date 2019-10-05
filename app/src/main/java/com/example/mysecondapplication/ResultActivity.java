package com.example.mysecondapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView output = findViewById(R.id.result);
        View body = findViewById(R.id.resultPage);
        body.setBackgroundColor(Color.rgb(29, 36, 42));
        Intent intent = getIntent();
        String BMI_res = intent.getStringExtra("BMI_Output");
        output.setText(BMI_res);
    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
