package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.example.project02_triviaapp.databinding.ActivityAdminPanelBinding;

public class AdminPanelActivity extends AppCompatActivity {

    private ActivityAdminPanelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminPanelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

    public static Intent AdminPanelIntentFactory(Context context) {
        return new Intent(context, AdminPanelActivity.class);
    }

}