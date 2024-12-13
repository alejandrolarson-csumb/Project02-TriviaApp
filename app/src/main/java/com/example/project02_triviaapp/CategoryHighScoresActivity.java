package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project02_triviaapp.database.entities.Category;
import com.example.project02_triviaapp.databinding.ActivityCategoryHighScoresBinding;

/**
 * @author Ben Shimek
 * CategoryHighScoresActivity displays a set of buttons representing different trivia categories.
 * When a user clicks on a category, the app will navigate to the leaderboard screen for that category.
 *
 * The activity is designed to let users select categories such as Movies, History, and Other Categories,
 * and then view the top scores for the selected category by navigating to the LeaderboardActivity.
 */

public class CategoryHighScoresActivity extends AppCompatActivity {

    ActivityCategoryHighScoresBinding binding;

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
        binding = ActivityCategoryHighScoresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize buttons
        moviesButton = findViewById(R.id.movies_button);
        historyButton = findViewById(R.id.history_button);
        otherCategoryButton = findViewById(R.id.other_category_button);
        otherCategoryButton2 = findViewById(R.id.other_category_button2);

        binding.backButtonCategoryHighScoresMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        // Set onClickListeners for each button
        /*moviesButton.setOnClickListener(v -> openLeaderboard(1));*/
        binding.moviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaderboard(1);
            }
        });

        historyButton.setOnClickListener(v -> openLeaderboard(2));
        otherCategoryButton.setOnClickListener(v -> openLeaderboard(3));
        otherCategoryButton2.setOnClickListener(v -> openLeaderboard(4));

    }

    /**
     * @author Ben Shimek
     * Starts the LeaderboardActivity and passes the selected category to it via an Intent.
     * @param id The category whose leaderboard is to be displayed. This value will be passed to LeaderboardActivity.
     */
    private void openLeaderboard(long id) {  //TODO: need to create LeaderboardActivity.java
        Intent intent = new Intent(CategoryHighScoresActivity.this, LeaderboardActivity.class);
        intent.putExtra("categoryId", id);
        startActivity(intent);
    }

    public static Intent categoryHighScoresActivityIntentFactory(Context context) {
        return new Intent(context, CategoryHighScoresActivity.class);
    }
}

