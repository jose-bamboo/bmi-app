package com.example.mysecondapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View body = findViewById(R.id.body);
        body.setBackgroundColor(Color.rgb(29, 36, 42));
        final Switch toggleSwitch = findViewById(R.id.toggleSwitch);
        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView weightLbl = findViewById(R.id.weightLbl);
                TextView heightLbl = findViewById(R.id.heightLbl);
                EditText weightTxt = findViewById(R.id.weight);
                EditText heightTxt = findViewById(R.id.height);
                Button calculate = findViewById(R.id.calculate);
                if(isChecked) {
                    weightLbl.setText(R.string.weightLbs);
                    heightLbl.setText(R.string.heightIn);
                    calculate.setText(R.string.imperial);
                    weightTxt.setHint(R.string.weightLbs);
                    heightTxt.setHint("Height ((ft * 12) + in -> in)");
                    toggleSwitch.setText(R.string.imperial);
                    Toast.makeText(getApplicationContext(), "Pounds/in", Toast.LENGTH_LONG).show();
                } else {
                    weightLbl.setText(R.string.weightKg);
                    heightLbl.setText(R.string.heightCm);
                    calculate.setText(R.string.metric);
                    weightTxt.setHint(R.string.weightKg);
                    heightTxt.setHint(R.string.heightCm);
                    toggleSwitch.setText(R.string.metric);
                    Toast.makeText(getApplicationContext(), "KG/cm", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void calculate(View view, EditText weightVal, EditText heightVal) {
        Intent intent;
        float weight = Float.parseFloat(weightVal.getText().toString());
        float height = Float.parseFloat(heightVal.getText().toString());
        float BMI = weight / square(height/100);
        // Store data in HashMap with String and String as key Value Pairs
        Map<String, String> dataMap = new LinkedHashMap<>();
        dataMap.put("Category", validate(BMI));
        dataMap.put("Result", Float.toString(BMI));
        // Attempt to store data in JSON file
        System.out.println(dataMap.toString());
        intent = new Intent(this, ResultActivity.class);
        intent.putExtra("BMI_Output", dataMap.toString());
        startActivity(intent);
    }

    public void imperial(View view, EditText weightVal, EditText heightVal) {
        Intent intent;
        float weight = Float.parseFloat(weightVal.getText().toString());
        float height = Float.parseFloat(heightVal.getText().toString());
        float BMI = (weight / square(height)) * 703;
        // Store data in HashMap with String and String as key Value Pairs
        Map<String, String> dataMap = new LinkedHashMap<>();
        dataMap.put("Category", validate(BMI));
        dataMap.put("Result", Float.toString(BMI));
        // Attempt to store data in JSON file
        System.out.println(dataMap.toString());
        intent = new Intent(this, ResultActivity.class);
        intent.putExtra("BMI_Output", dataMap.toString());
        startActivity(intent);
    }

    public void validate(View view) {
        EditText mEdit = findViewById(R.id.weight);
        EditText mEdit2 = findViewById(R.id.height);
        String weightVal = mEdit.getText().toString();
        String heightVal = mEdit2.getText().toString();
        Button calculate = findViewById(R.id.calculate);
        if(calculate.getText().equals("Metric")) {
            if(weightVal.isEmpty() || heightVal.isEmpty()) {
                Toast.makeText(this, "Please do not leave fields blank.", Toast.LENGTH_LONG).show();
            } else {
                calculate(view, mEdit, mEdit2);
            }
        } else {
            if(weightVal.isEmpty() || heightVal.isEmpty()) {
                Toast.makeText(this, "Please do not leave fields blank.", Toast.LENGTH_LONG).show();
            } else {
                imperial(view, mEdit, mEdit2);
            }
        }
    }

    public void clearFields(View view) {
        EditText textOne = findViewById(R.id.weight);
        EditText textTwo = findViewById(R.id.height);
        textOne.setText("");
        textTwo.setText("");
    }

    public void toggleSomething(View view) {
        View body = findViewById(R.id.body);
        int redVal = (int)Math.round(Math.random() * 150);
        int greenVal = (int)Math.round(Math.random() * 150);
        int blueVal = (int)Math.round(Math.random() * 150);
        body.setBackgroundColor(Color.rgb(redVal, greenVal, blueVal));
    }

    public String validate(float BMI) {
        String BMI_res;
        if(BMI < 18.5) {
            BMI_res = "Literally Dead";
        } else if (BMI >= 18.5 && BMI <= 24.9) {
            BMI_res = "Ideal";
        } else if (BMI >= 25 && BMI <= 29.9) {
            BMI_res = "Overweight";
        } else {
            BMI_res = "Literally Fat Ass";
        }
        return BMI_res;
    }

    public float square(float num){
        return num * num;
    }
}
