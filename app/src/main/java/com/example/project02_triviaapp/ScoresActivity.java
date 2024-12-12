package com.example.project02_triviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoresActivity extends AppCompatActivity {

    private TextView finalScoreText;
    private Button returnToMainMenuButton;

    // variables to be passed from GameplayActivity
    private int finalScore;
    private long userId;
    private long categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        finalScoreText = findViewById(R.id.final_score_text);
        returnToMainMenuButton = findViewById(R.id.return_to_main_menu_button);

        // Get the data passed from GameActivity
        Intent intent = getIntent();
        // Will be passed form GameActivity to ScoresActivity
        finalScore = intent.getIntExtra("final_score", 0);
        userId = intent.getLongExtra("user_id", 0);
        categoryId = intent.getLongExtra("category_id", 0);

        finalScoreText.setText("Final Score: " + finalScore); // Display the final score

        saveScoreToDatabase();  // save the score
    }

}
