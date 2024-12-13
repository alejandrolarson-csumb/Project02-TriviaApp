package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project02_triviaapp.databinding.ActivityAdminChangePasswordBinding;

public class AdminChangePasswordActivity extends AppCompatActivity {

    private ActivityAdminChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.adminChangeUserPasswordBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AdminPanelActivity.AdminPanelIntentFactory(getApplicationContext()));
            }
        });

    }

    public static Intent AdminChangePasswordIntentFactory(Context context) {
        return new Intent(context, AdminChangePasswordActivity.class);
    }

}