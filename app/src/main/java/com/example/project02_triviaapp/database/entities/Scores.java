package com.example.project02_triviaapp.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/*import java.util.Date;*/

@Entity(tableName = "scores_table",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userId",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Category.class,
                        parentColumns = "categoryId",
                        childColumns = "categoryId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Scores {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "scoreId")
    private long scoreId;

    @ColumnInfo(name = "userId")
    private long userId;

    @ColumnInfo(name = "categoryId")
    private long categoryId;

    @ColumnInfo(name = "score")
    private int score;

    /*@ColumnInfo(name = "date")
    private Date date;*/

    public Scores(long userId, long categoryId, int score) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.score = score;
        /*this.date = date;*/
    }

    public long getScoreId() {
        return scoreId;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /*public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }*/

}
