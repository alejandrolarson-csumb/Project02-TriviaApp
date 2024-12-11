package com.example.project02_triviaapp.database;

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

}
