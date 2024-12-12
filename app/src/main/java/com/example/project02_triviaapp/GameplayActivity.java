package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    private static final String GAMEPLAY_ACTIVITY_QUESTION_ID = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_QUESTION_ID";
    ActivityGameplayBinding binding;

    private TriviaRepository repository;

    int questionNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = TriviaRepository.getRepository(getApplication());

        Intent fromAct = getIntent();
        questionNum = fromAct.getIntExtra(GAMEPLAY_ACTIVITY_QUESTION_ID, 0);
        Log.i(MainActivity.TAG, "GameplayActivity questionNum " + questionNum);

        assert repository != null;
        LiveData<List<Question>> questionObserver = repository.getQuestionsForCategory(1);
        questionObserver.observe(this, question -> {
            //question is a List of the questions in a category
            Log.i(MainActivity.TAG, "GameplayActivity question List size " + question.size());


            if(questionNum == question.size()){
                //TODO: Change to Scores Activity instead of Main Activity
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }

            Log.i(MainActivity.TAG, "GameplayActivity questionNum " + questionNum);

            createQuestionAnswers(question, questionNum);

            //submitAnswer();
            binding.answerASelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Score increases by 1
                    questionNum += 1;
                    if(questionNum == question.size()){
                        Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                        startActivity(intent);
                    } else {
                        Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum);
                        startActivity(intent);
                    }
                }
            });

            binding.answerBSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionNum += 1;
                    if(questionNum == question.size()){
                        Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                        startActivity(intent);
                    } else {
                        Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum);
                        startActivity(intent);
                    }
                }
            });
            binding.answerCSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionNum += 1;
                    if(questionNum == question.size()){
                        Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                        startActivity(intent);
                    } else {
                        Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum);
                        startActivity(intent);
                    }
                }
            });
            binding.answerDSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionNum += 1;
                    if(questionNum == question.size()){
                        Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                        startActivity(intent);
                    } else {
                        Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum);
                        startActivity(intent);
                    }
                }
            });

        });
    }

    private void submitAnswer() {
        binding.answerASelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Score increases by 1
                questionNum += 1;
                Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum);
                startActivity(intent);
            }
        });

        binding.answerBSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionNum += 1;
                Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum);
                startActivity(intent);
            }
        });
        binding.answerCSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionNum += 1;
                Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum);
                startActivity(intent);
            }
        });
        binding.answerDSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionNum += 1;
                Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum);
                startActivity(intent);
            }
        });
    }

    public void createQuestionAnswers(List<Question> question, int questionNum) {

        Question particularQuestion = question.get(questionNum);

        String testQ = particularQuestion.getQuestionText();
        //Log.i(MainActivity.TAG, "GameplayActivity question String " + testQ);
        binding.questionTextView.setText(testQ);

        String testAC1 = particularQuestion.getCorrectAnswer();
        //Log.i(MainActivity.TAG, "GameplayActivity answer correct String " + testAC1);
        binding.answerASelectButton.setText(testAC1);

        String testAI = particularQuestion.getBadAnswers();
        String[] incorrectAnswers = testAI.split(",");

        //Log.i(MainActivity.TAG, "GameplayActivity answer incorrect String 1 " + incorrectAnswers[0]);
        //Log.i(MainActivity.TAG, "GameplayActivity answer incorrect String 2 " + incorrectAnswers[1]);
        //Log.i(MainActivity.TAG, "GameplayActivity answer incorrect String 3 " + incorrectAnswers[2]);
        binding.answerBSelectButton.setText(incorrectAnswers[0]);
        binding.answerCSelectButton.setText(incorrectAnswers[1]);
        binding.answerDSelectButton.setText(incorrectAnswers[2]);
    }

    //Added questionId, revert if issue
    public static Intent gameplayMusicIntentFactory(Context context, int questionId) {
        Intent intent = new Intent(context, GameplayActivity.class);
        //intent.putExtra(GAMEPLAY_ACTIVITY_CATEGORY_ID, categoryId);
        intent.putExtra(GAMEPLAY_ACTIVITY_QUESTION_ID, questionId);
        return intent;
    }
}