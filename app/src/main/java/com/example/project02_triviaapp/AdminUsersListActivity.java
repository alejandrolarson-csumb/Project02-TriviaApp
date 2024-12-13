package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project02_triviaapp.databinding.ActivityAdminUsersListBinding;

public class AdminUsersListActivity extends AppCompatActivity {

    private ActivityAdminUsersListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminUsersListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public static Intent AdminUsersListIntentFactory(Context context) {
        return new Intent(context, AdminUsersListActivity.class);
    }

}