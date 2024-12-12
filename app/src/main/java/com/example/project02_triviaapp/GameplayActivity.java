package com.example.project02_triviaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.project02_triviaapp.database.TriviaRepository;
import com.example.project02_triviaapp.database.entities.Question;
import com.example.project02_triviaapp.databinding.ActivityGameplayBinding;

import java.util.List;
import java.util.Random;

public class GameplayActivity extends AppCompatActivity {
    private static final String GAMEPLAY_ACTIVITY_CATEGORY_ID = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_CATEGORY_ID";
    private static final String GAMEPLAY_ACTIVITY_QUESTION_ID = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_QUESTION_ID";
    private static final String GAMEPLAY_ACTIVITY_SCORE = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_SCORE";
    ActivityGameplayBinding binding;
    private TriviaRepository repository;

    int questionNum;
    int categoryId;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = TriviaRepository.getRepository(getApplication());

        /**
         * @author Shane Ritter
         * Getting information sent from the gameplayActivityIntentFactory
         */
        Intent fromAct = getIntent();
        questionNum = fromAct.getIntExtra(GAMEPLAY_ACTIVITY_QUESTION_ID, 0);
        categoryId = fromAct.getIntExtra(GAMEPLAY_ACTIVITY_CATEGORY_ID, 0);
        score = fromAct.getIntExtra(GAMEPLAY_ACTIVITY_SCORE, 0);

        //Log.i(MainActivity.TAG, "GameplayActivity questionNum " + questionNum);
        //Log.i(MainActivity.TAG, "GameplayActivity categoryId " + categoryId);

        assert repository != null;
        LiveData<List<Question>> questionObserver = repository.getQuestionsForCategory(categoryId);
        questionObserver.observe(this, questionsInList -> {
            //questionsInList is a List of the questions in a category
            //Log.i(MainActivity.TAG, "GameplayActivity question List size " + questionsInList.size());
            //Log.i(MainActivity.TAG, "GameplayActivity questionNum " + questionNum);

            createQuestionAnswers(questionsInList, questionNum);

            Question particularQuestion = questionsInList.get(questionNum);

            //TODO: Implements an increase in score if answer is correct
            //TODO: Change to Scores Activity instead of Main Activity
            binding.answerASelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(particularQuestion.getCorrectAnswer().equals(binding.answerASelectButton.getText().toString())){
                        Log.i(MainActivity.TAG, "Score is increased!!!");
                        score += 1;
                    }
                    nextQuestion(questionsInList);
                }
            });

            binding.answerBSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(particularQuestion.getCorrectAnswer().equals(binding.answerBSelectButton.getText().toString())){
                        Log.i(MainActivity.TAG, "Score is increased!!!");
                        score += 1;
                    }
                    nextQuestion(questionsInList);
                }
            });

            binding.answerCSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(particularQuestion.getCorrectAnswer().equals(binding.answerCSelectButton.getText().toString())){
                        Log.i(MainActivity.TAG, "Score is increased!!!");
                        score += 1;
                    }
                    nextQuestion(questionsInList);
                }
            });

            binding.answerDSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(particularQuestion.getCorrectAnswer().equals(binding.answerDSelectButton.getText().toString())){
                        Log.i(MainActivity.TAG, "Score is increased!!!");
                        score += 1;
                    }
                    nextQuestion(questionsInList);
                }
            });

        });
    }

    /**
     * @param questionsInList The amount and details of questions in a category is given to us in
     *                        the parameter questionsInList.
     * @author Shane Ritter
     * Method allows the activity to go to the next question in the database using
     * the variable questionNum. If there are no more questions left, we currently (12/11/2024) go
     * to the Main Activity instead.
     */
    private void nextQuestion(List<Question> questionsInList) {
        questionNum += 1;
        if (questionNum == questionsInList.size()) {
            Log.i(MainActivity.TAG, "Final score is: " + score);
            Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
            startActivity(intent);
        } else {
            Intent intent = GameplayActivity.gameplayIntentFactory(getApplicationContext(), questionNum, categoryId, score);
            startActivity(intent);
        }
    }

    /**
     * @param questionsInList he amount and details of questions in a category is given to us in
     *                        the parameter questionsInList.
     * @param questionNum     The question number that the user is currently on
     * @author Shane Ritter
     * The method takes the question, correct answer, and incorrect answer from a Question object
     * in the database and assigns them to their repective TextView and Buttons.
     */
    private void createQuestionAnswers(List<Question> questionsInList, int questionNum) {

        Question particularQuestion = questionsInList.get(questionNum);

        String testQ = particularQuestion.getQuestionText();
        //Log.i(MainActivity.TAG, "GameplayActivity question String " + testQ);
        binding.questionTextView.setText(testQ);


        Random random = new Random();
        int max = 4;
        int min = 1;
        int randomNum1 = min + (int) (Math.random() * ((max - min) + 1));

        int randomNum2 = min + (int) (Math.random() * ((max - min) + 1));
        if (randomNum2 == randomNum1) {
            while (randomNum2 == randomNum1) {
                randomNum2 = min + (int) (Math.random() * ((max - min) + 1));
            }
        }

        int randomNum3 = min + (int) (Math.random() * ((max - min) + 1));
        if (randomNum3 == randomNum1 || randomNum3 == randomNum2) {
            while (randomNum3 == randomNum1 || randomNum3 == randomNum2) {
                randomNum3 = min + (int) (Math.random() * ((max - min) + 1));
            }
        }

        int randomNum4 = min + (int) (Math.random() * ((max - min) + 1));
        if (randomNum4 == randomNum1 || randomNum4 == randomNum2 || randomNum4 == randomNum3) {
            while (randomNum4 == randomNum1 || randomNum4 == randomNum2 || randomNum4 == randomNum3) {
                randomNum4 = min + (int) (Math.random() * ((max - min) + 1));
            }
        }

        String testAC1 = particularQuestion.getCorrectAnswer();

        switch (randomNum1) {
            case 1:
                binding.answerASelectButton.setText(testAC1);
                break;

            case 2:
                binding.answerBSelectButton.setText(testAC1);
                break;

            case 3:
                binding.answerCSelectButton.setText(testAC1);
                break;

            case 4:
                binding.answerDSelectButton.setText(testAC1);
                break;

        }

        String testAI = particularQuestion.getBadAnswers();
        String[] incorrectAnswers = testAI.split(",");

        //Log.i(MainActivity.TAG, "GameplayActivity answer incorrect String 1 " + incorrectAnswers[0]);
        //Log.i(MainActivity.TAG, "GameplayActivity answer incorrect String 2 " + incorrectAnswers[1]);
        //Log.i(MainActivity.TAG, "GameplayActivity answer incorrect String 3 " + incorrectAnswers[2]);

        switch (randomNum2) {
            case 1:
                binding.answerASelectButton.setText(incorrectAnswers[0]);
                break;

            case 2:
                binding.answerBSelectButton.setText(incorrectAnswers[0]);
                break;

            case 3:
                binding.answerCSelectButton.setText(incorrectAnswers[0]);
                break;

            case 4:
                binding.answerDSelectButton.setText(incorrectAnswers[0]);
                break;

        }

        switch (randomNum3) {
            case 1:
                binding.answerASelectButton.setText(incorrectAnswers[1]);
                break;

            case 2:
                binding.answerBSelectButton.setText(incorrectAnswers[1]);
                break;

            case 3:
                binding.answerCSelectButton.setText(incorrectAnswers[1]);
                break;

            case 4:
                binding.answerDSelectButton.setText(incorrectAnswers[1]);
                break;

        }

        switch (randomNum4) {
            case 1:
                binding.answerASelectButton.setText(incorrectAnswers[2]);
                break;

            case 2:
                binding.answerBSelectButton.setText(incorrectAnswers[2]);
                break;

            case 3:
                binding.answerCSelectButton.setText(incorrectAnswers[2]);
                break;

            case 4:
                binding.answerDSelectButton.setText(incorrectAnswers[2]);
                break;

        }
    }

    /**
     * @param questionId The number question to go to in the category
     * @param categoryId The category number of the category selected
     * @author Shane Ritter
     * Method return an intent that includes the question to go to and the category to go to in
     * the database.
     */
    public static Intent gameplayIntentFactory(Context context, int questionId, int categoryId, int score) {
        Intent intent = new Intent(context, GameplayActivity.class);
        intent.putExtra(GAMEPLAY_ACTIVITY_QUESTION_ID, questionId);
        intent.putExtra(GAMEPLAY_ACTIVITY_CATEGORY_ID, categoryId);
        intent.putExtra(GAMEPLAY_ACTIVITY_SCORE, score);
        return intent;
    }
}