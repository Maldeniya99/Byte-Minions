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

        String[] from= {"USD (US$)", "EUR (€)", "AUD (A$)", "CAD (C$)", "INR (₹)"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        sp1.setAdapter(ad);


        String[] to = {"Sl rupees (Rs)"};
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


            if (sp2.getSelectedItem().toString() == "Sl rupees (Rs)" && sp1.getSelectedItem().toString() == "USD (US$)") {
                currency = c_calculations.convertUSDToSLruppee(currency);
                display_value.setText(new Double(currency).toString());
            } else if (sp2.getSelectedItem().toString() == "Sl rupees (Rs)" && sp1.getSelectedItem().toString() == "EUR (€)") {
                currency = c_calculations.convertEURToSLruppee(currency);
                display_value.setText(new Double(currency).toString());
            } else if (sp2.getSelectedItem().toString() == "Sl rupees (Rs)" && sp1.getSelectedItem().toString() == "AUD (A$)") {
                currency = c_calculations.convertAUDToSLruppee(currency);
                display_value.setText(new Double(currency).toString());
            } else if (sp2.getSelectedItem().toString() == "Sl rupees (Rs)" && sp1.getSelectedItem().toString() == "CAD (C$)") {
                currency = c_calculations.convertCADToSLruppee(currency);
                display_value.setText(new Double(currency).toString());
            } else if (sp2.getSelectedItem().toString() == "Sl rupees (Rs)" && sp1.getSelectedItem().toString() == "INR (₹)") {
                currency = c_calculations.convertINRToSLruppee(currency);
                display_value.setText(new Double(currency).toString());

            }


        }


}