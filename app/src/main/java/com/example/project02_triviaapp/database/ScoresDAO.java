package com.example.project02_triviaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project02_triviaapp.database.entities.Scores;

import java.util.List;

@Dao
public interface ScoresDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Scores score);

    @Query("SELECT * FROM " + TriviaDatabase.SCORES_TABLE + " WHERE userOwnerId = :userId")
    LiveData<List<Scores>> getScoresByUser(long userId);

    @Query("SELECT * FROM " + TriviaDatabase.SCORES_TABLE + " WHERE categoryOwnerId = :categoryId")
    LiveData<List<Scores>> getScoresByCategory(long categoryId);

    @Query("SELECT * FROM " + TriviaDatabase.SCORES_TABLE)
    LiveData<List<Scores>> getAllScores();

    @Query("DELETE FROM " + TriviaDatabase.SCORES_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + TriviaDatabase.SCORES_TABLE + " WHERE categoryOwnerId = :categoryId ORDER BY score DESC LIMIT 3")
    List<Scores> getTopScoresForCategory(long categoryId); // TODO: super confused on how this works

}
