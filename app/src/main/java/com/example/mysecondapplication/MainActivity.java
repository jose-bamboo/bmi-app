package com.example.mysecondapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View body = findViewById(R.id.body);
        body.setBackgroundColor(170);
    }

    public void calculate(View view, EditText weightVal, EditText heightVal) {
        Intent intent;
        float weight = Integer.parseInt(weightVal.getText().toString());
        float height = Integer.parseInt(heightVal.getText().toString());
        float BMI = weight / square(height/100);
        // Store data in HashMap with String and String as key Value Pairs
        Map<String, String> dataMap = new LinkedHashMap<>();
        dataMap.put("Category", validate(BMI));
        dataMap.put("Result", Float.toString(Math.round(BMI)));
        // Attempt to store data in JSON file
        System.out.println(dataMap.toString());
        intent = new Intent(this, ResultActivity.class);
        intent.putExtra("BMI_Output", dataMap.toString());
        startActivity(intent);
    }

    public void validate(View view) {
        EditText mEdit = findViewById(R.id.weight);
        EditText mEdit2 = findViewById(R.id.height);
        String weightVal = mEdit.toString();
        String heightVal = mEdit2.toString();
        Intent main = new Intent(this, MainActivity.class);
        if(weightVal.isEmpty() && heightVal.isEmpty()) {
            startActivity(main);
            Toast.makeText(this, "Please do not leave fields blank.", Toast.LENGTH_LONG).show();
        } else {
            calculate(view, mEdit, mEdit2);
        }
    }

    public void clearFields(View view) {
        EditText textOne = findViewById(R.id.weight);
        EditText textTwo = findViewById(R.id.height);
        textOne.setText("");
        textTwo.setText("");
    }

    public void toggleSomething(View view) {
        System.out.println("Hello World");
        Toast.makeText(this, "Event Toggled", Toast.LENGTH_LONG).show();
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

    public int square(float num){
        return Math.round(num * num);
    }
}
