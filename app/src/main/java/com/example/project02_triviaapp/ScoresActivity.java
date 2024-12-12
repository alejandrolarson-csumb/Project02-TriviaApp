package com.example.project02_triviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.project02_triviaapp.database.TriviaDatabase;
import com.example.project02_triviaapp.database.entities.Scores;
import com.example.project02_triviaapp.database.ScoresDAO;

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

        // click to return to main menu
        //TODO: intent to return to main menu);
        returnToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ScoresActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }

    private void saveScoreToDatabase() {
        TriviaDatabase db = Room.databaseBuilder(getApplicationContext(),
                TriviaDatabase.class, "trivia_database").build();

        ScoresDAO scoresDAO = db.scoresDAO(); // Get the DAO for Scores

        Scores scores = new Scores(userId, categoryId, finalScore);  // Create a new Score object

        // Insert the score into the database in a background thread
        new Thread(() -> {
            scoresDAO.insert(scores);
            runOnUiThread(() -> {
                finish();
            });
        }).start();
    }

}
