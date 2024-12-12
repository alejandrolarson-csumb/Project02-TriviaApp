package com.example.project02_triviaapp;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

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

    }
}

