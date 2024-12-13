package com.example.project02_triviaapp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.project02_triviaapp.database.TriviaDatabase;
import com.example.project02_triviaapp.database.UserDAO;
import com.example.project02_triviaapp.database.entities.User;

import java.io.IOException;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoEntityReadWriteTest {
    private UserDAO userDao;
    private TriviaDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TriviaDatabase.class).build();
        userDao = db.userDAO();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertUserTest() throws Exception {
        User expectedUser = new User("Alejandro", "kittycat");
        userDao.insert(expectedUser);

        User retrievedUser = userDao.testGetUserByUserId(1);
        long ExpectedId = 1;
        String ExpectedName = "Alejandro";
        String ExpectedPassword = "kittycat";

        // We are checking that the User entity prior to insertion is same as after
        assertNotNull(retrievedUser);
        assertEquals(ExpectedId, retrievedUser.getUserid());
        assertEquals(ExpectedName, retrievedUser.getUsername());
        assertEquals(ExpectedPassword, retrievedUser.getPassword());
    }

    @Test
    public void userUpdateTest() throws Exception {
        User expectedUser = new User("Superman", "LoisLane");
        userDao.insert(expectedUser);

        long testId = 1;
        String expectedName = "Bizarro";
        String expectedPassword = "BizzaroLoisLane";

        // We are checking that a User entity is being updated as expected with new values
        expectedUser.setUsername(expectedName);
        expectedUser.setPassword(expectedPassword);
        expectedUser.setUserid(testId);
        userDao.updateUser(expectedUser);
        User retrievedUser = userDao.testGetUserByUserId(testId);

        assertEquals(expectedName, retrievedUser.getUsername());
        assertEquals(expectedPassword, retrievedUser.getPassword());
    }

    @Test
    public void userDeleteTest() throws Exception {
        User expectedUser = new User("Superman", "LoisLane");
        userDao.insert(expectedUser);
        User retrievedUser = userDao.testGetUserByUserId(1);
        // We know user is in table
        assertNotNull(retrievedUser);

        userDao.delete(retrievedUser);
        retrievedUser = userDao.testGetUserByUserId(1);
        // After delete, this user entity/row should return null
        assertNull(retrievedUser);

    }



}