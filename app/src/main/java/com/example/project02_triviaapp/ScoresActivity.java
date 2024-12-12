package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.project02_triviaapp.database.TriviaDatabase;
import com.example.project02_triviaapp.database.TriviaRepository;
import com.example.project02_triviaapp.database.entities.Scores;
import com.example.project02_triviaapp.database.ScoresDAO;
import com.example.project02_triviaapp.database.entities.User;
import com.example.project02_triviaapp.databinding.ActivityScoresBinding;

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

    private static final String SCORES_ACTIVITY_CATEGORY_ID = "com.example.project02_triviaapp.SCORES_ACTIVITY_CATEGORY_ID";
    private static final String SCORES_ACTIVITY_FINAL_SCORE = "com.example.project02_triviaapp.SCORES_ACTIVITY_FINAL_SCORE";
    private TextView finalScoreText;
    private Button returnToMainMenuButton;
    private TriviaRepository repository;
    private User user;

    ActivityScoresBinding binding;

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
        binding = ActivityScoresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        finalScoreText = findViewById(R.id.final_score_text);
        returnToMainMenuButton = findViewById(R.id.return_to_main_menu_button);

        repository = TriviaRepository.getRepository(getApplication());

        long userFromMain = MainActivity.loggedInUserId;

        Log.i(MainActivity.TAG, "userFromMain equals: " + userFromMain);


        // Get the data passed from GameActivity - NEEDS WORK!!
        Intent fromAct = getIntent();
        // Will be passed from GameActivity to ScoresActivity
        finalScore = fromAct.getIntExtra(SCORES_ACTIVITY_FINAL_SCORE, 0);
        userId = fromAct.getLongExtra("user_id", userFromMain);
        //categoryId = fromAct.getLongExtra("category_id", 0);
        categoryId = fromAct.getIntExtra(SCORES_ACTIVITY_CATEGORY_ID, 0);

        Log.i(MainActivity.TAG, "userId in ScoresActivity: " + userId);
        //Log.i(MainActivity.TAG, "categoryId in ScoresActivity: " + categoryId);
        //Log.i(MainActivity.TAG, "finalScore in ScoresActivity: " + finalScore);

        finalScoreText.setText("Final Score: " + finalScore + "\nUser: " + userId); // Display the final score

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
        repository.insertScores(scores);
    }

    /*private void returnToMainMenu() {
        // Create an Intent to go back to the Main Menu
        Intent intent = new Intent(ScoresActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }*/ //Do we need to make this a method?

    public static Intent scoresIntentFactory(Context context, int categoryId, int finalScore){
        Log.i(MainActivity.TAG, "scoresIntentFactory score: " + finalScore);
        Intent intent = new Intent(context, ScoresActivity.class);
        intent.putExtra(SCORES_ACTIVITY_CATEGORY_ID, categoryId);
        intent.putExtra(SCORES_ACTIVITY_FINAL_SCORE, finalScore);
        return intent;
    }


}
