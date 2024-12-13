package com.example.project02_triviaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project02_triviaapp.database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);
    @Delete
    void delete(User user);
    @Query("DELETE FROM " + TriviaDatabase.USER_TABLE)
    void deleteAll();
    @Query("SELECT * FROM " + TriviaDatabase.USER_TABLE + " ORDER BY username ASC")
    LiveData<List<User>> getAlphabetizedUsers();

    @Query("SELECT * FROM " + TriviaDatabase.USER_TABLE + " WHERE username = :username")
    LiveData<User> getUserByUserName(String username);

    @Query("SELECT * FROM " + TriviaDatabase.USER_TABLE + " WHERE userid = :userId")
    LiveData<User> getUserByUserId(long userId);

    @Query("UPDATE " + TriviaDatabase.USER_TABLE + " SET password = :pw WHERE userid = :id")
    void updatePassword(long id, String pw);

    //This query is useful for unit testing
    @Query("SELECT * FROM " + TriviaDatabase.USER_TABLE + " WHERE username = :name")
    List<User> findUsersByName(String name);

    @Query("SELECT * FROM " + TriviaDatabase.USER_TABLE + " WHERE userid = :userId")
    User testGetUserByUserId(long userId);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM " + TriviaDatabase.USER_TABLE)
    List<User> getAllUsersNotLiveData();


}
