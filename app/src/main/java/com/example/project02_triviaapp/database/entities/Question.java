package com.example.project02_triviaapp.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "question_table",
    foreignKeys = @ForeignKey(
        entity = Category.class,
        parentColumns = "categoryId",
        childColumns = "categoryOwnerId",
        onDelete = ForeignKey.CASCADE // delete question if category deleted
    )
)
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int questionId;
    private int categoryOwnerId; //foreign key
    private String questionText;
    private String correctAnswer;
    private String badAnswers; // takes form "badAnswer1,badAnswer2,badAnswer3"

    public Question(int categoryOwnerId, String questionText,
                    String correctAnswer, String badAnswers) {
        this.categoryOwnerId = categoryOwnerId;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.badAnswers = badAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionId == question.questionId && categoryOwnerId == question.categoryOwnerId &&
                Objects.equals(questionText, question.questionText) && Objects.equals(correctAnswer,
                question.correctAnswer) && Objects.equals(badAnswers, question.badAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, categoryOwnerId, questionText, correctAnswer, badAnswers);
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getCategoryOwnerId() {
        return categoryOwnerId;
    }

    public void setCategoryOwnerId(int categoryOwnerId) {
        this.categoryOwnerId = categoryOwnerId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getBadAnswers() {
        return badAnswers;
    }

    public void setBadAnswers(String badAnswers) {
        this.badAnswers = badAnswers;
    }
}
