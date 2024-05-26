package com.example.unitalentv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.unitalentv3.databinding.Activity3Binding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Activity3 extends AppCompatActivity {

    Activity3Binding binding;
    String userEmail;

    // Create an instance of ProfileFragment
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");

        // Initialize the profileFragment with the userEmail value
        profileFragment = ProfileFragment.newInstance(userEmail);

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        int checkUser = intent.getIntExtra("identifyUser", 1);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;

                case R.id.library:
                    if (checkUser == 1) {
                        replaceFragment(new LibraryFragment());
                    } else {
                        Toast.makeText(Activity3.this, "Sorry this feature is not available for Guests.", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.profile:
                    if (checkUser == 1) {
                        replaceFragment(profileFragment); // Replace with the existing profileFragment instance
                    } else {
                        Toast.makeText(Activity3.this, "Sorry this feature is not available for Guests.", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            return true;
        });


        FloatingActionButton fab = findViewById(R.id.btnfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkUser == 1) {
                    showToDoListDialog();
                } else {
                    Toast.makeText(Activity3.this, "Sorry this feature is not available for Guests.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showToDoListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialogStyle);
        builder.setTitle("Upload:");

        String[] items = {"Art", "Poem", "Music"};

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                // Handle item selection

                Intent go_intent;

                switch (items[position]) {
                    case "Art":
                        // Art selected
                        Toast.makeText(getApplicationContext(), "Art", Toast.LENGTH_SHORT).show();
                        go_intent = new Intent(getApplicationContext(), UploadArtActivity.class);
                        startActivity(go_intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;

                    case "Poem":
                        // Poem selected
                        Toast.makeText(getApplicationContext(), "Poem", Toast.LENGTH_SHORT).show();
                        go_intent = new Intent(getApplicationContext(), UploadPoemActivity.class);
                        startActivity(go_intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;

                    case "Music":
                        // Music selected
                        Toast.makeText(getApplicationContext(), "Music", Toast.LENGTH_SHORT).show();
                        go_intent = new Intent(getApplicationContext(), Activity2.class);
                        startActivity(go_intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;

                    default:
                        // Handle default case if needed
                        break;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
