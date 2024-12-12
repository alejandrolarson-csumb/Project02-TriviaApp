package com.example.project02_triviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project02_triviaapp.database.entities.Category;

public class CategoryHighScoresActivity extends AppCompatActivity {

    private Button moviesButton;
    private Button historyButton;
    private Button otherCategoryButton;
    private Button otherCategoryButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_high_scores);

        // Initialize buttons
        moviesButton = findViewById(R.id.movies_button);
        historyButton = findViewById(R.id.history_button);
        otherCategoryButton = findViewById(R.id.other_category_button);
        otherCategoryButton2 = findViewById(R.id.other_category_button2);

        // Set onClickListeners for each button
        moviesButton.setOnClickListener(v -> openLeaderboard(Category.MOVIES));
        historyButton.setOnClickListener(v -> openLeaderboard(Category.HISTORY));
        otherCategoryButton.setOnClickListener(v -> openLeaderboard(Category.OTHER_CATEGORY));
        otherCategoryButton2.setOnClickListener(v -> openLeaderboard(Category.OTHER_CATEGORY_2));

    }

    private void openLeaderboard(String category) {
        Intent intent = new Intent(CategoryHighScoresActivity.this, LeaderboardActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}

