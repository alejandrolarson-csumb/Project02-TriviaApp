package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LiveData;

import com.example.project02_triviaapp.database.TriviaRepository;
import com.example.project02_triviaapp.database.entities.User;
import com.example.project02_triviaapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private TriviaRepository repository;

    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = TriviaRepository.getRepository(getApplication());


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
    }
    private void signUpUser() {
        String username = binding.userNameLoginEditText.getText().toString();
        String password = binding.passwordLoginEditText.getText().toString();
        if(username.isEmpty() || password.isEmpty()) {
            toastMaker("Username or password should not be blank");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if(user != null){
                toastMaker("Username already registered");
                return;
            } else {
                // Insert new user into database
                User newUser = new User(username,password);
                repository.insert(newUser);
                // TODO how to check if this insert is successful?
                toastMaker("Account registered");
            }
        });
    }

    private void verifyUser() {
        String username = binding.userNameLoginEditText.getText().toString();
        if(username.isEmpty()) {
            toastMaker("Username should not be blank");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if(user != null){
                Log.i(MainActivity.TAG, "What is the user's username " + user.getUsername());
                String password = binding.passwordLoginEditText.getText().toString();
                Log.i(MainActivity.TAG, "What is the user's password " + user.getPassword());
                if(password.equals(user.getPassword())){
                    Log.i(MainActivity.TAG, "The Login Process was completed in verifyUser() " + user.getUserid());
                    //startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(), user.getUserid()));
                    startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(), user.getUserid()));
                } else {
                    toastMaker("Invalid password.");
                    binding.passwordLoginEditText.setSelection(0);
                }
            } else{
                toastMaker(String.format("%s is not a valid username", username));
                binding.userNameLoginEditText.setSelection(0);
            }
        });
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }



}