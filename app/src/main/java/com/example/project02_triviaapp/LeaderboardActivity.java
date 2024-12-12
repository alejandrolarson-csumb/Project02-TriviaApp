package com.example.project02_triviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project02_triviaapp.database.TriviaDatabase;
import androidx.room.Room;
import com.example.project02_triviaapp.database.ScoresDAO;
import com.example.project02_triviaapp.database.entities.Scores;

import java.util.List;

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

    private void getTopScores(String category) {
        // Get the database instance
        TriviaDatabase db = Room.databaseBuilder(getApplicationContext(),
                TriviaDatabase.class, "trivia_database").build();
        ScoresDAO scoresDAO = db.scoresDAO();

        // Get the top 3 scores for the given category
        new Thread(() -> {
            List<Scores> topScores = scoresDAO.getTopScoresForCategory(category); //TODO: need to create method in ScoresDAO

            // Update the UI with the top scores
            runOnUiThread(() -> {
                if (topScores.isEmpty()) {
                    leaderboardText.setText("No scores available for this category.");
                } else {
                    StringBuilder leaderboard = new StringBuilder();
                    for (int i = 0; i < Math.min(topScores.size(), 3); i++) {
                        Scores score = topScores.get(i);
                        leaderboard.append(i + 1)
                                .append(". ")
                                .append("User: ")
                                .append(score.getUserId()) // TODO: need to get the userID from User
                                .append(" - Score: ")
                                .append(score.getScore())
                                .append("\n");
                    }
                    leaderboardText.setText(leaderboard.toString());
                }
            });
        }).start();
    }

}

