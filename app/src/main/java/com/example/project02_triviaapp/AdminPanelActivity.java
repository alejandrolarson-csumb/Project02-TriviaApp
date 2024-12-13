package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.project02_triviaapp.databinding.ActivityAdminPanelBinding;

public class AdminPanelActivity extends AppCompatActivity {

    private ActivityAdminPanelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminPanelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.adminPanelBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext()));
            }
        });

        binding.adminPanelViewListOfUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AdminUsersListActivity.AdminUsersListIntentFactory(getApplicationContext()));
            }
        });

        binding.adminPanelChangeUserPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AdminUsersListActivity.AdminUsersListIntentFactory(getApplicationContext()));
            }
        });
    }

    public static Intent AdminPanelIntentFactory(Context context) {
        return new Intent(context, AdminPanelActivity.class);
    }

}