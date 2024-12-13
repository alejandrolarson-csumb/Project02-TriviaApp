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

/**
 * @author Shane Ritter
 * GameplayActivity contains the main gameplay loop of the Trivia game and uses the questions_table
 * from the trivia_database. This Activity grabs a list of questions that match the category the
 * user has picked. The questions are loaded in one at a time with their corresponding answers,
 * which are randomized to different buttons. If the user clicks on the button with the correct
 * answers, there score increases by one. Next set of questions and answers are loaded in until all
 * questions in the list has been answered.
 */

public class GameplayActivity extends AppCompatActivity {
    private static final String GAMEPLAY_ACTIVITY_CATEGORY_ID = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_CATEGORY_ID";
    private static final String GAMEPLAY_ACTIVITY_QUESTION_ID = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_QUESTION_ID";
    private static final String GAMEPLAY_ACTIVITY_SCORE = "com.example.project02_triviaapp.GAMEPLAY_ACTIVITY_SCORE";
    ActivityGameplayBinding binding;
    private TriviaRepository repository;
    public static int getQuestionListSize;
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
         * Getting information sent from the gameplayActivityIntentFactory. If no information is
         * sent, the default value is zero
         */
        Intent fromAct = getIntent();
        questionNum = fromAct.getIntExtra(GAMEPLAY_ACTIVITY_QUESTION_ID, 0);
        categoryId = fromAct.getIntExtra(GAMEPLAY_ACTIVITY_CATEGORY_ID, 0);
        score = fromAct.getIntExtra(GAMEPLAY_ACTIVITY_SCORE, 0);

        //Log.i(MainActivity.TAG, "GameplayActivity questionNum " + questionNum);
        Log.i(MainActivity.TAG, "GameplayActivity categoryId " + categoryId);

        assert repository != null;
        LiveData<List<Question>> questionObserver = repository.getQuestionsForCategory(categoryId);
        questionObserver.observe(this, questionsInList -> {
            //questionsInList is a List of the questions in a category
            //Log.i(MainActivity.TAG, "GameplayActivity question List size " + questionsInList.size());
            //Log.i(MainActivity.TAG, "GameplayActivity questionNum " + questionNum);

            getQuestionListSize = questionsInList.size();

            createQuestionAnswers(questionsInList, questionNum);

            Question particularQuestion = questionsInList.get(questionNum);

            /**
             * @author Shane Ritter
             * Waits for one of the four buttons with answers to the question to be clicked. If the
             * text on the button equals the correct answer, the score is increased and nextQuestion
             * is called.
             */
            binding.answerASelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (particularQuestion.getCorrectAnswer().equals(binding.answerASelectButton.getText().toString())) {
                        Log.i(MainActivity.TAG, "Score is increased!!!");
                        score += 1;
                    }
                    nextQuestion(questionsInList);
                }
            });

            binding.answerBSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (particularQuestion.getCorrectAnswer().equals(binding.answerBSelectButton.getText().toString())) {
                        Log.i(MainActivity.TAG, "Score is increased!!!");
                        score += 1;
                    }
                    nextQuestion(questionsInList);
                }
            });

            binding.answerCSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (particularQuestion.getCorrectAnswer().equals(binding.answerCSelectButton.getText().toString())) {
                        Log.i(MainActivity.TAG, "Score is increased!!!");
                        score += 1;
                    }
                    nextQuestion(questionsInList);
                }
            });

            binding.answerDSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (particularQuestion.getCorrectAnswer().equals(binding.answerDSelectButton.getText().toString())) {
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
            Log.i(MainActivity.TAG, "Final score is: " + score + " CategoryId " + categoryId);
            Intent intent = ScoresActivity.scoresIntentFactory(getApplicationContext(), categoryId, score);
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
     * in the database and assigns them to their repective TextView and Buttons. Answers are
     * assigned to their buttons randomly.
     */
    private void createQuestionAnswers(List<Question> questionsInList, int questionNum) {

        Question particularQuestion = questionsInList.get(questionNum);

        String testQ = particularQuestion.getQuestionText();
        binding.questionTextView.setText(testQ);


        /**
         * @author Shane Ritter
         * Random number generated between 1-4 is put into the randomNum variables. These numbers
         * correspond to a different button (Ex: 1 = A, 2= B, etc.). If a number generated is
         * already generated, a different number will be generated. Each randonNum variable are
         * then connected to either a correct or incorrect answer, which then becomes the text on
         * a button.
         */
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

        String correctAnswerFromTable = particularQuestion.getCorrectAnswer();

        switch (randomNum1) {
            case 1:
                binding.answerASelectButton.setText(correctAnswerFromTable);
                break;

            case 2:
                binding.answerBSelectButton.setText(correctAnswerFromTable);
                break;

            case 3:
                binding.answerCSelectButton.setText(correctAnswerFromTable);
                break;

            case 4:
                binding.answerDSelectButton.setText(correctAnswerFromTable);
                break;
        }

        String badAnswersFromTable = particularQuestion.getBadAnswers();
        String[] incorrectAnswers = badAnswersFromTable.split(",");

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
     * @param score      The current score the player has earned
     * @author Shane Ritter
     * Method return an intent that includes the parameters questionId, categoryId, and score.
     * These are saved as they are needed to identify the next question and keep track of the
     * user's score.
     */
    public static Intent gameplayIntentFactory(Context context, int questionId, int categoryId, int score) {
        Intent intent = new Intent(context, GameplayActivity.class);
        intent.putExtra(GAMEPLAY_ACTIVITY_QUESTION_ID, questionId);
        intent.putExtra(GAMEPLAY_ACTIVITY_CATEGORY_ID, categoryId);
        intent.putExtra(GAMEPLAY_ACTIVITY_SCORE, score);
        return intent;
    }
}