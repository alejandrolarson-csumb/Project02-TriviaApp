package com.example.project02_triviaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project02_triviaapp.database.entities.Question;

import java.util.List;

@Dao
public interface QuestionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Question... question);
    @Delete
    void delete(Question question);

    @Query("SELECT * FROM " + TriviaDatabase.QUESTION_TABLE + " WHERE categoryOwnerId = :categoryId")
    LiveData<List<Question>> getQuestionsForCategory(int categoryId);


    @Query(" SELECT * FROM " + TriviaDatabase.QUESTION_TABLE + " WHERE questionId = :questionId")
    LiveData<Question> getQuestionId(int questionId);
}
