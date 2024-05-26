package com.example.unitalentv3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCmuStudent, btnGuest;

    ProgressBar progress;

    private int identifyUser;

    public int getIdentifyUser() {
        return identifyUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCmuStudent = findViewById(R.id.btn_CMU_Student);
        btnGuest = findViewById(R.id.btn_Guest);
        progress = findViewById(R.id.progress_bar);

        btnCmuStudent.setOnClickListener(this);
        btnGuest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_CMU_Student:
                identifyUser = 1;
                Intent go_intent = new Intent(getApplicationContext(), Activity2.class);
                go_intent.putExtra("identifyUser", identifyUser);
                startActivity(go_intent);

                break;
            case R.id.btn_Guest:

                Thread td = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            progress.incrementProgressBy(10);
                            SystemClock.sleep(500);
                        }

                        // Wait until the progress bar is done loading
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                identifyUser = 0;
                                Intent go_intent2 = new Intent(getApplicationContext(), Activity3.class);
                                go_intent2.putExtra("identifyUser", identifyUser);

                                startActivity(go_intent2);
                            }
                        });
                    }
                });
                td.start();


                break;
            default:
                break;


        }
    }
}