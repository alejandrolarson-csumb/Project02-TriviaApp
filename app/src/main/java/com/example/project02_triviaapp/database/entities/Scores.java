package com.example.project02_triviaapp.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

/*import java.util.Date;*/

@Entity(tableName = "scores_table",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userid",
                        childColumns = "userOwnerId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Category.class,
                        parentColumns = "categoryId",
                        childColumns = "categoryOwnerId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Scores {

    @PrimaryKey(autoGenerate = true)
    private int scoreId;
    private long userOwnerId;
    private long categoryOwnerId;
    private int score;

    /*@ColumnInfo(name = "date")
    private Date date;*/

    public Scores(long userOwnerId, long categoryOwnerId, int score) {
        this.userOwnerId = userOwnerId;
        this.categoryOwnerId = categoryOwnerId;
        this.score = score;
        /*this.date = date;*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores scores = (Scores) o;
        return scoreId == scores.scoreId && userOwnerId == scores.userOwnerId && categoryOwnerId == scores.categoryOwnerId && score == scores.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreId, userOwnerId, categoryOwnerId, score);
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public long getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(long userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public long getCategoryOwnerId() {
        return categoryOwnerId;
    }

    public void setCategoryOwnerId(long categoryOwnerId) {
        this.categoryOwnerId = categoryOwnerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
