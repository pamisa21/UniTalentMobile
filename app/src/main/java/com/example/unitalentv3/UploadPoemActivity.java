package com.example.unitalentv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class UploadPoemActivity extends AppCompatActivity {
    Button btnUpload;
    EditText txtTitle, txtName, txtPoem;

    private DatabaseReference databaseRef;
    Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_upload);

        btnUpload = findViewById(R.id.button_upload);

        txtTitle = findViewById(R.id.editTitle);
        txtName = findViewById(R.id.editName);
        txtPoem = findViewById(R.id.editPoem);

        databaseRef = FirebaseDatabase.getInstance().getReference("poems");

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPoem();
            }
        });
    }

    private void uploadPoem() {

        if (!TextUtils.isEmpty(txtPoem.getText().toString().trim())) {

            UploadPoem upload = new UploadPoem (txtTitle.getText().toString().trim(), txtName.getText().toString().trim(), txtPoem.getText().toString());

            String uploadId = databaseRef.push().getKey();
            databaseRef.child(uploadId).setValue(upload);


            Toast.makeText(UploadPoemActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();

            Intent go_intent = new Intent(getApplicationContext(), Activity3.class);
            startActivity(go_intent);
        } else {
            Toast.makeText(this, "No Poem entered", Toast.LENGTH_SHORT).show();
        }
    }

}
