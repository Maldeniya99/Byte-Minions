package com.example.travelbee;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CurrencyActvity extends AppCompatActivity {
    Spinner sp1, sp2;
    EditText ed1;
    Button b1;
    TextView display_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        ed1 = findViewById(R.id.txt_camount);
        sp1 = findViewById(R.id.spfrom);
        sp2 = findViewById(R.id.spto);
        b1 = findViewById(R.id.btn1);
        display_value = findViewById(R.id.tv_display_value);

        String[] from = {"Sl rupees (Rs)"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        sp1.setAdapter(ad);


        String[] to = {"USD (US$)", "EUR (€)", "AUD (A$)", "CAD (C$)", "INR (₹)"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to);
        sp2.setAdapter(ad1);

    }

        protected void onResume () {
            super.onResume();
            b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    calculateAnswer();
                }

            });


        }
        private void calculateAnswer () {
            CurrencyCal c_calculations = new CurrencyCal();
            String currency_value = ed1.getText().toString();
            Double currency = Double.parseDouble(currency_value);


            if (sp1.getSelectedItem().toString() == "Sl rupees (Rs)" && sp2.getSelectedItem().toString() == "USD (US$)") {
                currency = c_calculations.convertSLruppeeToUSD(currency);
                display_value.setText(new Double(currency).toString());
            } else if (sp1.getSelectedItem().toString() == "Sl rupees (Rs)" && sp2.getSelectedItem().toString() == "EUR (€)") {
                currency = c_calculations.convertSLrupeeToEUR(currency);
                display_value.setText(new Double(currency).toString());
            } else if (sp1.getSelectedItem().toString() == "Sl rupees (Rs)" && sp2.getSelectedItem().toString() == "AUD (A$)") {
                currency = c_calculations.convertSLrupeeToAUD(currency);
                display_value.setText(new Double(currency).toString());
            } else if (sp1.getSelectedItem().toString() == "Sl rupees (Rs)" && sp2.getSelectedItem().toString() == "CAD (C$)") {
                currency = c_calculations.convertSLrupeeToCAD(currency);
                display_value.setText(new Double(currency).toString());
            } else if (sp1.getSelectedItem().toString() == "Sl rupees (Rs)" && sp2.getSelectedItem().toString() == "INR (₹)") {
                currency = c_calculations.convertSLrupeeToINR(currency);
                display_value.setText(new Double(currency).toString());

            }


        }


}