package com.example.project02_triviaapp.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project02_triviaapp.MainActivity;
import com.example.project02_triviaapp.database.entities.Category;
import com.example.project02_triviaapp.database.entities.Question;
import com.example.project02_triviaapp.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Category.class, Question.class}, version = 2, exportSchema = false)
public abstract class TriviaDatabase extends RoomDatabase {

    public static final String USER_TABLE = "user_table";
    public static final String CATEGORY_TABLE = "category_table";
    public static final String QUESTION_TABLE = "question_table";

    private static final String DATABASE_NAME = "trivia_database";
    public abstract UserDAO userDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract QuestionDAO questionDAO();
    private static volatile TriviaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TriviaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TriviaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TriviaDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues).build();
                }
            }
        }
        return INSTANCE;
    }

    // Initializing stuff in database on creation
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();
                CategoryDAO catDao = INSTANCE.categoryDAO();
                QuestionDAO questDao = INSTANCE.questionDAO();
                // TODO: may not want to delete all in the future
                dao.deleteAll();
                //Insert users for testing purposes
                User admin = new User("admin1", "admin1");
                admin.setAdmin(true);
                dao.insert(admin);
                User testUser1 = new User("testuser1", "testuser1");
                dao.insert(testUser1);

                // Insert Category
                Category moviesCategory = new Category("movies");
                Category historyCategory = new Category("history");
                long moviesID = catDao.insert(moviesCategory);
                long historyID = catDao.insert(historyCategory);

                // Initializing all questions for Movies
                Question question = new Question(moviesID,
                        "Who played Rick Deckard in the film Blade Runner?",
                        "Harrison Ford",
                        "Tom Cruise,David Duchovny,Kurt Russell");
                questDao.insert(question);
                question = new Question(moviesID,"What was the first computer animated film?",
                        "Toy Story",
                        "Shrek,Despicable Me,Dinosaur");
                questDao.insert(question);

                //Initializing all Questions for History
                question = new Question(historyID,
                        "What was Walt Disney’s plan for EPCOT before his death?",
                        "A prototype futuristic city",
                        "Another theme park,Another movie studio,A farm");
                questDao.insert(question);
                question = new Question(historyID,
                        "What was the deadliest disease in history so far?",
                        "The Black Plague",
                        "The Spanish Flu,Smallpox,The HIV/AIDS pandemic");
                questDao.insert(question);






            });
        }
    };

}
