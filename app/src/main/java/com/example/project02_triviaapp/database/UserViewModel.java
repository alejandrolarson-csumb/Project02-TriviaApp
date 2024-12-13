package com.example.project02_triviaapp.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project02_triviaapp.database.entities.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private TriviaRepository repository;

    private final LiveData<List<User>> allUsers;

    public UserViewModel(Application application) {
        super(application);
        repository = TriviaRepository.getRepository(application);
        allUsers = repository.getAllUsers();

    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user) {
        repository.insert(user);
    }

}
