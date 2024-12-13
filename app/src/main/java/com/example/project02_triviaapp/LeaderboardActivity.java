package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project02_triviaapp.database.TriviaDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.example.project02_triviaapp.database.ScoresDAO;
import com.example.project02_triviaapp.database.entities.Scores;

import java.util.List;

/**
 * @author Ben Shimek
 * LeaderboardActivity displays the top 3 scores for a selected trivia category.
 * The category is passed to the activity throug an Intent, and the activity queries the database
 * for the top 3 scores related to that category.
 *
 * The activity updates the UI to show the top scores or a message indicating that no scores are available.
 */

public class LeaderboardActivity extends AppCompatActivity {

    private TextView leaderboardText;

    /**
     * @author Ben Shimek
     * Called when the activity is created.
     * This method retrieves the category passed through the Intent, then queries the database
     * for the top 3 scores for that category and displays them in the leaderboard.
     * @param savedInstanceState The saved instance state for the activity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderboardText = findViewById(R.id.leaderboard_text);

        // Get the category from the intent
        /*Intent intent = getIntent();*/
        long categoryId = getIntent().getLongExtra("categoryId",-1);

        // Get the top 3 scores for the selected category
        if (categoryId != -1) {
            getTopScores(categoryId);
        }else{
            leaderboardText.setText("Invalid category.");
        }
    }

    /**
     * @author Ben Shimek
     * Queries the database for the top 3 scores for a given category and updates the UI
     * to display the leaderboard. If no scores are available, a message is shown.
     * This method performs the database query in a background thread to prevent blocking the UI thread.
     * @param categoryId The category for which to fetch the top scores.
     */
    private void getTopScores(long categoryId) {

        // Get the top 3 scores for the given category
        new Thread(() -> {
            // Get the database instance
            TriviaDatabase db = Room.databaseBuilder(getApplicationContext(),
                    TriviaDatabase.class, "trivia_database").build();
            ScoresDAO scoresDAO = db.scoresDAO();

           LiveData<List<Scores>> topScores = scoresDAO.getTopScoresForCategory(categoryId);

            // Update the UI with the top scores
            runOnUiThread(() -> {
                if (topScores != null && !topScores.isEmpty()) {
                    StringBuilder leaderboard = new StringBuilder();
                    int count = Math.min(topScores.size(),3);
                    for (int i = 0; i < count; i++) {
                        Scores score = topScores.get(i);
                        leaderboard.append(i + 1)
                                .append(". ")
                                .append("User: ")
                                .append(score.getUserOwnerId()) // TODO: need to get the userID from User
                                .append(" - Score: ")
                                .append(score.getScore())
                                .append("\n");
                    }
                    leaderboardText.setText(leaderboard.toString());
                }else{
                    leaderboardText.setText("No scores available for this category.");
                }
            });
        }).start();
    }

    public static Intent leaderboardActivityIntentFactory(Context context) {
        return new Intent(context, LeaderboardActivity.class);
    }

}

