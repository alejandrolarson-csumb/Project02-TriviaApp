package com.example.project02_triviaapp.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions_table", foreignKeys = @ForeignKey() ForeignKey(
        entity = Category.class,
        childColumns = "categoryName",
        parentColumns = "categoryid"
))
public class Questions {
    @PrimaryKey(autoGenerate = true)
    private int questionid;


}
