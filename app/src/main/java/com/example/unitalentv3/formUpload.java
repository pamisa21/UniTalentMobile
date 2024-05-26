package com.example.unitalentv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class formUpload extends AppCompatActivity {

    Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_upload);

        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(formUpload.this, "Upload Complete!", Toast.LENGTH_SHORT).show();
                Toast.makeText(formUpload.this, "Ay jokeeeee...coming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}