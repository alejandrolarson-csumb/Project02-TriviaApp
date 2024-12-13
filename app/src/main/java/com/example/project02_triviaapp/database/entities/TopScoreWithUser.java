package com.example.project02_triviaapp.database.entities;

public class TopScoreWithUser {

    private int scoreId;
    private long userOwnerId;
    private long categoryOwnerId;
    private int score;
    private String username;

    // Constructor, getters, and setters

    public TopScoreWithUser(int scoreId, long userOwnerId, long categoryOwnerId, int score, String username) {
        this.scoreId = scoreId;
        this.userOwnerId = userOwnerId;
        this.categoryOwnerId = categoryOwnerId;
        this.score = score;
        this.username = username;
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

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
