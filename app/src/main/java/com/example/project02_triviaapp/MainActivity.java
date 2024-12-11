package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project02_triviaapp.database.TriviaRepository;
import com.example.project02_triviaapp.database.entities.User;
import com.example.project02_triviaapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.project02_triviaapp.MAIN_ACTIVITY_USER_ID";
    public static final String TAG = "DAC_TRIVIALOG";
    private static final int LOGGED_OUT = -1;
    private ActivityMainBinding binding;
    private TriviaRepository repository;
    private int loggedInUserId = -1;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        invalidateOptionsMenu();

        repository = TriviaRepository.getRepository(getApplication());

        binding.isAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
            }
        });

    }

    public static Intent MainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.logout_menu);
        item.setVisible(true);
        //item.setTitle(user.getUsername());
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this, "Logout to be implemented", Toast.LENGTH_SHORT).show();
                //logout()
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
}