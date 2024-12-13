package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_triviaapp.database.TriviaRepository;
import com.example.project02_triviaapp.database.UserListAdapter;
import com.example.project02_triviaapp.database.UserViewModel;
import com.example.project02_triviaapp.databinding.ActivityAdminUsersListBinding;

public class AdminUsersListActivity extends AppCompatActivity {

    private ActivityAdminUsersListBinding binding;
    private UserViewModel userViewModel;

    private TriviaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminUsersListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        RecyclerView recyclerView = binding.userDisplayRecyclerView;
        final UserListAdapter adapter = new UserListAdapter(new UserListAdapter.UserListDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = TriviaRepository.getRepository(getApplication());

        userViewModel.getAllUsers().observe(this, users -> {
            adapter.submitList(users);
        });


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