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

import com.example.project02_triviaapp.database.entities.Category;
import com.example.project02_triviaapp.databinding.ActivityCategoryMenuBinding;

public class CategoryMenuActivity extends AppCompatActivity {

    ActivityCategoryMenuBinding binding;

    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO:Back Button Not Currently Working
        binding.backButtonCategoryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.selectMusicCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), 0);
                startActivity(intent);
            }
        });

    }

    public static Intent categoryIntentFactory(Context context) {
        return new Intent(context, CategoryMenuActivity.class);
    }
}