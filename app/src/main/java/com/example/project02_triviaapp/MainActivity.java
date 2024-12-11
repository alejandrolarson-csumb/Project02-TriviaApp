package com.example.project02_triviaapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.project02_triviaapp.database.TriviaRepository;
import com.example.project02_triviaapp.database.entities.User;
import com.example.project02_triviaapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.project02_triviaapp.MAIN_ACTIVITY_USER_ID";
    static final String SHARED_PREFERENCE_USERID_KEY = "com.example.project02_triviaapp.SHARED_PREFERENCE_USERID_KEY";
    static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.example.project02_triviaapp.SAVED_INSTANCE_STATE_USERID_KEY";

    public static final String TAG = "DAC_TRIVIALOG";
    private ActivityMainBinding binding;
    private TriviaRepository repository;
    //Changed from private to public
    public int loggedInUserId = -1;
    private int LOGGED_OUT = -1;
    private User user;

    public int getLoggedInUserId() {
        return loggedInUserId;
    }

    public void setLoggedInUserId(int loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        repository = TriviaRepository.getRepository(getApplication());
        loginUser(savedInstanceState);

        /**
        if (loggedInUserId == -1) {
            Log.i(TAG, "loggedInUserId == -1");
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }*/

        updateSharedPreference();

        binding.playNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CategoryMenuActivity.categoryIntentFactory(getApplicationContext()));
            }
        });

    }

    public static Intent MainActivityIntentFactory(Context context, long userId) {
        Intent intent = new Intent(context, MainActivity.class);
        Log.i(MainActivity.TAG, "What is the userid in mainactivityIntentFactory " + userId);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;

    }

    /**
    public static Intent mainActivityIntentFactory(Context context) {
        return new Intent(context, MainActivity.class);
    }*/

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
        if (user == null) {
            return false;
        }
        item.setTitle(user.getUsername());
        if(user.getUsername().equals("admin1")){
            binding.isAdminButton.setVisibility(View.VISIBLE);
        }
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                showLogoutDialog();
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    private void showLogoutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");
        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertBuilder.create().show();
    }

    private void loginUser(Bundle savedInstanceState){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        //Not Updating after user login
        loggedInUserId = sharedPreferences.getInt(getString(R.string.preference_userid_key), LOGGED_OUT);
        Log.i(TAG, "User id in loginUser " + loggedInUserId);

        if (loggedInUserId == LOGGED_OUT & savedInstanceState != null) {
            loggedInUserId = savedInstanceState.getInt(SHARED_PREFERENCE_USERID_KEY, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (this.user != null) {
                invalidateOptionsMenu();
            }
        });
    }

    private void logout() {
        loggedInUserId = LOGGED_OUT;
        updateSharedPreference();

        getIntent().putExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
    }


    private void updateSharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userid_key), loggedInUserId);
        sharedPrefEditor.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INSTANCE_STATE_USERID_KEY, loggedInUserId);
        updateSharedPreference();
    }
}