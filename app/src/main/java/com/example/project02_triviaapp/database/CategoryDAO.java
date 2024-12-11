package com.example.project02_triviaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project02_triviaapp.database.entities.Category;

@Dao
public interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    @Query("SELECT * FROM " + TriviaDatabase.CATEGORY_TABLE + " WHERE categoryName = :categoryName")
    LiveData<Category> getCategoryByCategoryName(String categoryName);

    @Query("SELECT * FROM " + TriviaDatabase.CATEGORY_TABLE + " WHERE categoryid = :categoryid")
    LiveData<Category> getCategoryId(int categoryid);
}
