package com.example.project02_triviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project02_triviaapp.database.entities.Category;

/**
 * @author Ben Shimek
 * CategoryHighScoresActivity displays a set of buttons representing different trivia categories.
 * When a user clicks on a category, the app will navigate to the leaderboard screen for that category.
 *
 * The activity is designed to let users select categories such as Movies, History, and Other Categories,
 * and then view the top scores for the selected category by navigating to the LeaderboardActivity.
 */

public class CategoryHighScoresActivity extends AppCompatActivity {

    private Button moviesButton;
    private Button historyButton;
    private Button otherCategoryButton;
    private Button otherCategoryButton2;

    /**
     * @author Ben Shimek
     * Called when the activity is created.
     * This method initializes the buttons, sets up onClick listeners, and starts the appropriate activity
     * when a category is selected by the user.
     * @param savedInstanceState The saved instance state of the activity.
     */

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
        moviesButton.setOnClickListener(v -> openLeaderboard(Category.MOVIES)); // TODO: need to create constant fields in Category.java
        historyButton.setOnClickListener(v -> openLeaderboard(Category.HISTORY));
        otherCategoryButton.setOnClickListener(v -> openLeaderboard(Category.OTHER_CATEGORY));
        otherCategoryButton2.setOnClickListener(v -> openLeaderboard(Category.OTHER_CATEGORY_2));

    }

    /**
     * @author Ben Shimek
     * Starts the LeaderboardActivity and passes the selected category to it via an Intent.
     * @param category The category whose leaderboard is to be displayed. This value will be passed to LeaderboardActivity.
     */
    private void openLeaderboard(String category) {  //TODO: need to create LeaderboardActivity.java
        Intent intent = new Intent(CategoryHighScoresActivity.this, LeaderboardActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}

