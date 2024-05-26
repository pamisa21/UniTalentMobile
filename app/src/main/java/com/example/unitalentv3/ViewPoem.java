package com.example.unitalentv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewPoem extends AppCompatActivity {

    Button btnGoBack;
    TextView textTitle, textAuthor, textPoem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poem);

        textTitle  = findViewById(R.id.txtPoemTitle);
        textAuthor  = findViewById(R.id.txtPoemAuthor);
        textPoem  = findViewById(R.id.txtPoem);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String author = intent.getStringExtra("author");
            String poem = intent.getStringExtra("poem");

            textTitle.setText(title);
            textAuthor.setText(author);
            textPoem.setText(poem);
        }

        btnGoBack = findViewById(R.id.btnGoBack);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
    }
}