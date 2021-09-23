package com.example.travelbee;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TemperatureCalculator extends AppCompatActivity {

    EditText et_value;
    RadioButton btn_celcius;
    RadioButton btn_fahrenhite;
    Button btn_calculate;
    TextView display_value, display_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_calculator);

        et_value = findViewById(R.id.et_temp);
        btn_celcius = findViewById(R.id.btn_celcius);
        btn_fahrenhite = findViewById(R.id.btn_fahrenhite);
        btn_calculate = findViewById(R.id.btn_temp);
        display_value = findViewById(R.id.tv_calculatedTemp);
        display_name = findViewById(R.id.temp_name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });
    }

    private void calculateAnswer() {
        Calculations calculations = new Calculations();
        String temp_value = et_value.getText().toString();
        if(TextUtils.isEmpty(temp_value)){
            Toast.makeText(this, "Please add a value", Toast.LENGTH_LONG).show();
        }
        else {
            try {
                Float temp = Float.parseFloat(temp_value);
                if (btn_celcius.isChecked()) {
                    temp = calculations.convertCelciusToFahrenheit(temp);
                    display_name.setText("Fahrenhite");
                } else if (btn_fahrenhite.isChecked()) {
                    temp = calculations.convertFahrenheitToCelcius(temp);
                    display_name.setText("Celcius");
                } else {
                    Toast.makeText(this, "Please Radio!", Toast.LENGTH_LONG).show();

                }
                display_value.setText(new Float(temp).toString());
            } catch (NullPointerException e) {
            }

        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}