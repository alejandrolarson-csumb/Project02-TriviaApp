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

/**
 * @author Ben Shimek
 * ScoresActivity.java displays the final score of the user after completing a trivia game.
 * It also handles saving the user's score to the local database and allows the user to return to the main menu.
 *
 * The activity receives the final score, userId, and categoryId from the previous activity (GameplayActivity.java) using Intent.
 * The score is automatically saved to the database when the activity is created.
 * A button allows the user to navigate back to the MainActivity.
 */

public class ScoresActivity extends AppCompatActivity {

    private TextView finalScoreText;
    private Button returnToMainMenuButton;

    // variables to be passed from GameplayActivity
    private int finalScore;
    private long userId;
    private long categoryId;

    /**
     * @author Ben Shimek
     * Called when the activity is created.
     * This method sets up the UI, retrieves data passed from the previous activity, displays the final score,
     * saves the score to the database, and sets the listener for the return button.
     *
     * @param savedInstanceState The saved instance state for the activity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        finalScoreText = findViewById(R.id.final_score_text);
        returnToMainMenuButton = findViewById(R.id.return_to_main_menu_button);

        // Get the data passed from GameActivity - NEEDS WORK!!
        Intent intent = getIntent();
        // Will be passed from GameActivity to ScoresActivity
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
                finish();
            }
        });

        /*returnToMainMenuButton.setOnClickListener(v -> returnToMainMenu());*/ // Use this instead?
    }

    /**
     * @author Ben Shimek
     * Saves the user's score to the local database.
     * A new Score object is created and inserted into the Scores table in the database.
     * This operation is performed in a background thread to avoid blocking the main UI thread.
     */
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

    /*private void returnToMainMenu() {
        // Create an Intent to go back to the Main Menu
        Intent intent = new Intent(ScoresActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }*/ //Do we need to make this a method?

}
