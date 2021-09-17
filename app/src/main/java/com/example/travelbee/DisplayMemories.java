package com.example.travelbee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbee.adapters.Adapter;
import com.example.travelbee.models.Memories;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayMemories extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    List<Memories> items;
    DatabaseReference databaseReference;
    ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_memories);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add = findViewById(R.id.img_add);

        items = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Memories");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Memories memories = ds.getValue(Memories.class);
                    items.add(memories);
                }

                adapter = new Adapter(items);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        

    }

    public void onClick(View view){
        Intent intent = new Intent(DisplayMemories.this, CreateMemory.class);
        startActivity(intent);
    }
}