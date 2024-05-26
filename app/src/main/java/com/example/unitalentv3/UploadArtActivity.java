package com.example.unitalentv3;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.PixelCopy;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadArtActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    Button btnUpload, btnChooseImage;
    EditText txtTitle, txtName;
    ImageView imageView;
    ProgressBar mProgressBar;

    private StorageReference storageRef;
    private DatabaseReference databaseRef;
    private StorageTask storageTask;

    Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        btnUpload = findViewById(R.id.btnUpload);
        btnChooseImage = findViewById(R.id.btnImage);

        txtTitle = findViewById(R.id.editTitle);
        txtName = findViewById(R.id.editName);

        imageView = findViewById(R.id.imageView);

        mProgressBar = findViewById(R.id.progress_bar);


        storageRef = FirebaseStorage.getInstance().getReference("arts");
        databaseRef = FirebaseDatabase.getInstance().getReference("arts");


        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storageTask != null && storageTask.isInProgress()) {
                    Toast.makeText(getApplicationContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
                } else  {
                    uploadFile();
                }

            }
        });
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(imageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));

            storageTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;

                                    Upload upload = new Upload(txtTitle.getText().toString().trim(), txtName.getText().toString().trim(), downloadUrl.toString());
                                    String uploadId = databaseRef.push().getKey();
                                    databaseRef.child(uploadId).setValue(upload);
                                }
                            });
                            Toast.makeText(UploadArtActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                            Intent go_intent = new Intent(getApplicationContext(), Activity3.class);
                            startActivity(go_intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadArtActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
