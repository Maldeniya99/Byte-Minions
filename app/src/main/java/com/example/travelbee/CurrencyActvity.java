package com.example.travelbee;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CurrencyActvity extends AppCompatActivity {
Spinner sp1,sp2;
EditText ed1;
Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        ed1= findViewById(R.id.txt_camount);
        sp1=findViewById(R.id.spfrom);
        sp2=findViewById(R.id.spto);
        b1=findViewById(R.id.btn1);


        String[] from ={"Sl rupees (Rs)"};
        ArrayAdapter ad = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,from);
        sp1.setAdapter(ad);


        String[] to={"USD (US$)","EUR (€)","AUD (A$)","CAD (C$)","INR (₹)"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,to);
        sp2.setAdapter(ad1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double tot;
                Double amount = Double.parseDouble(ed1.getText().toString());
                if(sp1.getSelectedItem().toString() == "Sl rupees (Rs)"&& sp2.getSelectedItem().toString()=="USD (US$)"){
                    tot=amount * 198.36;
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                }else if(sp1.getSelectedItem().toString() == "Sl rupees (Rs)"&& sp2.getSelectedItem().toString()=="EUR (€)"){
                    tot=amount * 232.55;
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                }else if(sp1.getSelectedItem().toString() == "Sl rupees (Rs)"&& sp2.getSelectedItem().toString()=="AUD (A$)"){
                    tot=amount * 144.29;
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                }else if(sp1.getSelectedItem().toString() == "Sl rupees (Rs)"&& sp2.getSelectedItem().toString()=="CAD (C$)"){
                    tot=amount * 156.27;
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                }else if(sp1.getSelectedItem().toString() == "Sl rupees (Rs)"&& sp2.getSelectedItem().toString()=="INR (₹)"){
                    tot=amount * 2.68;
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}