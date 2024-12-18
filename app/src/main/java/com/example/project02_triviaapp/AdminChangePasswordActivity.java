package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.project02_triviaapp.database.TriviaRepository;
import com.example.project02_triviaapp.database.entities.User;
import com.example.project02_triviaapp.databinding.ActivityAdminChangePasswordBinding;

public class AdminChangePasswordActivity extends AppCompatActivity {

    private ActivityAdminChangePasswordBinding binding;
    private TriviaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = TriviaRepository.getRepository(getApplication());


        binding.adminChangeUserPasswordBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AdminPanelActivity.AdminPanelIntentFactory(getApplicationContext()));
            }
        });

        binding.adminChangePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUserPassword();
            }
        });

    }

    private void changeUserPassword() {
        String username = binding.adminChangeUserNameEditText.getText().toString();
        String password = binding.adminChangePasswordEditText.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username or password should not be blank",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        LiveData<User> userObserver = repository.getUserByUserName(username);

        userObserver.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    // User exists
                    repository.updatePassword(user.getUserid(),password);
                    Toast.makeText(AdminChangePasswordActivity.this, "password changed", Toast.LENGTH_SHORT).show();
                } else {
                    // User doesn't exist
                    Toast.makeText(AdminChangePasswordActivity.this, "password changed", Toast.LENGTH_SHORT).show();
                }
                userObserver.removeObserver(this);
            }
        });

    }

        public static Intent AdminChangePasswordIntentFactory (Context context){
            return new Intent(context, AdminChangePasswordActivity.class);
        }

    }
