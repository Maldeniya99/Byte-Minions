package com.example.travelbee;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelbee.models.Memories;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateMemory extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ImageView image;
    TextInputEditText date, title, location, description;
    ImageView picker;
    Button upload, cancel;

    Uri imageUri;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("Memories");
    private StorageReference storage = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memory);

        getSupportActionBar().hide();

        image = findViewById(R.id.iv_upload);
        date = findViewById(R.id.et_date);
        picker = findViewById(R.id.img_datepicker);
        title = findViewById(R.id.et_sourceLocation);
        location = findViewById(R.id.et_location);
        description = findViewById(R.id.et_description);
        upload = findViewById(R.id.btn_update);
        cancel = findViewById(R.id.btn_cancel);

        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Please note that use your package name here
                com.example.travelbee.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.travelbee.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");

            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertToDB();
            }

            private void InsertToDB() {
                if(imageUri != null) {
//                    StorageReference reference =  storage.getReference().child("images/*" + UUID.randomUUID().toString());
                    final StorageReference reference1 = storage.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
                    reference1.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Intent i = new Intent(CreateMemory.this, DisplayMemories.class);
                                    String memoryId = db.push().getKey();
                                    String title1 = title.getText().toString();
                                    String location1 = location.getText().toString();
                                    String description1 = description.getText().toString();
                                    String date1 = date.getText().toString();
                                    Memories memories = new Memories(uri.toString(), title1, location1, description1, date1);
                                    db.child(memoryId).setValue(memories);
                                    Toast.makeText(CreateMemory.this, "Memory Created Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(i);
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }
            private String getFileExtension(Uri imageUri) {
                ContentResolver cr = getContentResolver();
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                return mime.getExtensionFromMimeType(cr.getType(imageUri));
            }
        });
    }

    public void onClick(View view){
        Intent intent = new Intent(CreateMemory.this, DisplayMemories.class);
        startActivity(intent);
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result != null){
                        image.setImageURI(result);
                        imageUri = result;
                    }
                }
            });


    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(mCalender.getTime());
        date.setText(selectedDate);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}