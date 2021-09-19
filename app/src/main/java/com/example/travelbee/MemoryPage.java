package com.example.travelbee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MemoryPage extends AppCompatActivity {

    TextView titleName,locationName,dateView,descriptionView;
    ImageView imageView;
    Button update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_page);

        getSupportActionBar().hide();

        titleName = findViewById(R.id.displayTitle);
        locationName = findViewById(R.id.displayLocation);
        dateView = findViewById(R.id.displayDate);
        descriptionView = findViewById(R.id.displayDescription);
        imageView = findViewById(R.id.displayImage);

        Intent i = getIntent();
        String title = i.getStringExtra("title");
        titleName.setText(title);

        String location = i.getStringExtra("location");
        locationName.setText(location);

        String date = i.getStringExtra("date");
        dateView.setText(date);

        String description = i.getStringExtra("description");
        descriptionView.setText(description);

        String image = i.getStringExtra("image");
        Glide.with(imageView.getContext()).load(image).into(imageView);
    }

    /*public void onClickEdit(View view){
      Intent intent = new Intent(MemoryPage.this, UpdateMemory.class);
       startActivity(intent);
   }*/

    public void onClickCancel(View view){
        Intent intent = new Intent(MemoryPage.this, DisplayMemories.class);
        startActivity(intent);
    }
}