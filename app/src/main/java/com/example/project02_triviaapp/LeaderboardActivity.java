package com.example.project02_triviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LeaderboardActivity extends AppCompatActivity {

    private TextView leaderboardText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderboardText = findViewById(R.id.leaderboard_text);

        // Get the category from the intent
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // Get the top 3 scores for the selected category
        getTopScores(category); //TODO: need to make getTopScores() method
    }

}

