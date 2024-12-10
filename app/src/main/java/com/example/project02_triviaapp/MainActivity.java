package com.example.project02_triviaapp;

import android.os.Bundle;


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

        repository = TriviaRepository.getRepository(getApplication());

    }
}