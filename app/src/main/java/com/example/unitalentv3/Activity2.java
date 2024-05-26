package com.example.unitalentv3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    Button btn_login, btn_goBack;
    EditText ins_email, password;

    ProgressBar progress;

    private String userEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btn_login = findViewById(R.id.btn_Login);
        btn_goBack = findViewById(R.id.btn_GoBack);
        ins_email = findViewById(R.id.edt_Ins_Email);
        password = findViewById(R.id.edt_Password);
        progress = findViewById(R.id.progress_bar);

        btn_login.setOnClickListener(this);
        btn_goBack.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        int check_IE = 0;
        int check_password = 0;


        switch (view.getId()) {
            case R.id.btn_Login:

                userEmail = ins_email.getText().toString();

                if (TextUtils.isEmpty(ins_email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please put your institutional email.", Toast.LENGTH_SHORT).show();
                    check_IE = 1;
                } else if (!isValidEmail(ins_email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid institutional email address", Toast.LENGTH_SHORT).show();
                    check_IE = 1;
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password is empty", Toast.LENGTH_SHORT).show();
                    check_IE = 1;
                } else if (!isValidPassword(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid password", Toast.LENGTH_SHORT).show();
                    check_password = 1;
                }

                if (check_IE == 0 && check_password == 0) {
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
                                    Intent go_intent = new Intent(getApplicationContext(), Activity3.class);
                                    go_intent.putExtra("userEmail", userEmail);
                                    startActivity(go_intent);
                                }
                            });
                        }
                    });
                    td.start();
                }
                Intent intent = getIntent();
                int identifyUser = intent.getIntExtra("identifyUser", 1);

                // Use the identifyUser value as needed
                identifyUser = 1;
                break;

            case R.id.btn_GoBack:
                finish();
                break;

            default:
                break;
        }
    }


    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+@cmu\\.edu\\.ph";
        return !TextUtils.isEmpty(email) && email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "\\d{10,12}";
        return !TextUtils.isEmpty(password) && password.matches(passwordPattern);
    }


}