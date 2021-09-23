package com.example.travelbee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createNote extends AppCompatActivity {

    EditText mcreatetitleofnote, mcreatecontentofnote;
    FloatingActionButton msavenote;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    FirebaseFirestore firebaseFirestore;

    ProgressBar mprogressBarOfCreateNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);


        msavenote=findViewById(R.id.savenote);
        mcreatecontentofnote=findViewById(R.id.createcontentofnote);
        mcreatetitleofnote=findViewById(R.id.createtitleofnote);

        mprogressBarOfCreateNote = findViewById(R.id.progressbarofcreatenote);
        Toolbar toolbar = findViewById(R.id.toolbarofcreatenote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


        msavenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mcreatetitleofnote.getText().toString();
                String content = mcreatecontentofnote.getText().toString();
                if(title.isEmpty()||content.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Both fields are required", Toast.LENGTH_SHORT).show();
                }else{


                    mprogressBarOfCreateNote.setVisibility(View.VISIBLE);

                    DocumentReference documentReference = firebaseFirestore.collection("notes").document(firebaseUser.getUid())
                            .collection("myNotes").document();

                    Map<String,Object> note = new HashMap<>();

                    note.put("title",title);
                    note.put("content",content);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Note Created Successfully", Toast.LENGTH_SHORT).show();
                            mprogressBarOfCreateNote.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(createNote.this,notesActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to create note", Toast.LENGTH_SHORT).show();
                            mprogressBarOfCreateNote.setVisibility(View.INVISIBLE);
//                            startActivity(new Intent(createNote.this,notesActivity.class));
                        }
                    });
                }
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}