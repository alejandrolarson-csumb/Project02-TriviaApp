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

public class GameplayActivity extends AppCompatActivity {
    private static final String GAMEPLAY_ACTIVITY_CATEGORY_ID = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_CATEGORY_ID";
    private static final String GAMEPLAY_ACTIVITY_QUESTION_ID = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_QUESTION_ID";
    ActivityGameplayBinding binding;
    private TriviaRepository repository;

    int questionNum;
    int categoryId;

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

        //Log.i(MainActivity.TAG, "GameplayActivity questionNum " + questionNum);
        //Log.i(MainActivity.TAG, "GameplayActivity categoryId " + categoryId);

        assert repository != null;
        LiveData<List<Question>> questionObserver = repository.getQuestionsForCategory(categoryId);
        questionObserver.observe(this, questionsInList -> {
            //questionsInList is a List of the questions in a category
            //Log.i(MainActivity.TAG, "GameplayActivity question List size " + questionsInList.size());
            //Log.i(MainActivity.TAG, "GameplayActivity questionNum " + questionNum);

            createQuestionAnswers(questionsInList, questionNum);

            //TODO: Implements an increase in score if answer is correct
            //TODO: Change to Scores Activity instead of Main Activity
            binding.answerASelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQuestion(questionsInList);
                }
            });

            binding.answerBSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQuestion(questionsInList);
                }
            });
            binding.answerCSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQuestion(questionsInList);
                }
            });
            binding.answerDSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQuestion(questionsInList);
                }
            });

        });
    }

    /**
     * @author Shane Ritter
     * Method allows the activity to go to the next question in the database using
     * the variable questionNum. If there are no more questions left, we currently (12/11/2024) go
     * to the Main Activity instead.
     * @param questionsInList The amount and details of questions in a category is given to us in
     * the parameter questionsInList.
     */
    private void nextQuestion(List<Question> questionsInList) {
        questionNum += 1;
        if (questionNum == questionsInList.size()) {
            Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
            startActivity(intent);
        } else {
            Intent intent = GameplayActivity.gameplayMusicIntentFactory(getApplicationContext(), questionNum, categoryId);
            startActivity(intent);
        }
    }

    /**
     * @author Shane Ritter
     * The method takes the question, correct answer, and incorrect answer from a Question object
     * in the database and assigns them to their repective TextView and Buttons.
     * @param questionsInList he amount and details of questions in a category is given to us in
     * the parameter questionsInList.
     * @param questionNum The question number that the user is currently on
     */
    private void createQuestionAnswers(List<Question> questionsInList, int questionNum) {

        Question particularQuestion = questionsInList.get(questionNum);

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

    /**
     * @author Shane Ritter
     * Method return an intent that includes the question to go to and the category to go to in
     * the database.
     * @param questionId The number question to go to in the category
     * @param categoryId The category number of the category selected
     */
    public static Intent gameplayMusicIntentFactory(Context context, int questionId, int categoryId) {
        Intent intent = new Intent(context, GameplayActivity.class);
        intent.putExtra(GAMEPLAY_ACTIVITY_QUESTION_ID, questionId);
        intent.putExtra(GAMEPLAY_ACTIVITY_CATEGORY_ID, categoryId);
        return intent;
    }
}