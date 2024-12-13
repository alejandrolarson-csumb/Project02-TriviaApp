package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_triviaapp.database.UserViewModel;
import com.example.project02_triviaapp.databinding.ActivityAdminUsersListBinding;

public class AdminUsersListActivity extends AppCompatActivity {

    private ActivityAdminUsersListBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminUsersListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //RecyclerView recyclerView = binding.userDisplayRecyclerView;





        binding.adminUserListBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPanelActivity.AdminPanelIntentFactory(getApplicationContext())));
            }
        });

    }

    public static Intent AdminUsersListIntentFactory(Context context) {
        return new Intent(context, AdminUsersListActivity.class);
    }

}