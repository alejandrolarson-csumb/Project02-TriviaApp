package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.example.project02_triviaapp.database.TriviaRepository;
import com.example.project02_triviaapp.database.entities.Category;
import com.example.project02_triviaapp.database.entities.Question;
import com.example.project02_triviaapp.databinding.ActivityGameplayBinding;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GameplayActivity extends AppCompatActivity {

    private static final String GAMEPLAY_ACTIVITY_CATEGORY_ID = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_CATEGORY_ID";
    ActivityGameplayBinding binding;

    private TriviaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = TriviaRepository.getRepository(getApplication());

        /*LiveData<Question> questionObserver = repository.getQuestionId(1);


        Question question = questionsForCategory.getValue().get(0);
        String test = question.getQuestionText();
        binding.questionTextView.setText(test);*/

        //Works
        binding.questionTextView.setText("Testing to see if this works");
        binding.answerASelectButton.setText("This");
        binding.answerBSelectButton.setText("is");
        binding.answerCSelectButton.setText("super");
        binding.answerDSelectButton.setText("annoying");

    }

    public static Intent gameplayMusicIntentFactory(Context context) {
        Intent intent = new Intent(context, GameplayActivity.class);
        //intent.putExtra(GAMEPLAY_ACTIVITY_CATEGORY_ID, categoryId);
        return intent;
    }
}