package com.example.project02_triviaapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.project02_triviaapp.database.entities.Question;

import org.junit.Before;
import org.junit.Test;

public class QuestionEntityTest {

    private Question question1;
    private Question question2;

    @Before
    public void setUp() {
        // Setup data for the tests
        question1 = new Question(1, "What is Team 7's name?", "Endless Recursion", "T7,Rat,Cityscape");
        question2 = new Question(1, "What is Team 7's name?", "Endless Recursion", "T7,Rat,Cityscape");
    }

    @Test
    public void testGetQuestionId() {
        // Test the getter for questionId
        question1.setQuestionId(1);
        assertEquals(1, question1.getQuestionId());
    }

    @Test
    public void testSetQuestionId() {
        // Test the setter for questionId
        question1.setQuestionId(10);
        assertEquals(10, question1.getQuestionId());
    }

    @Test
    public void testGetCategoryOwnerId() {
        // Test the getter for categoryOwnerId
        assertEquals(1, question1.getCategoryOwnerId());
    }

    @Test
    public void testSetCategoryOwnerId() {
        // Test the setter for categoryOwnerId
        question1.setCategoryOwnerId(100);
        assertEquals(100, question1.getCategoryOwnerId());
    }

    @Test
    public void testGetQuestionText() {
        // Test the getter for questionText
        assertEquals("What is Team 7's name?", question1.getQuestionText());
    }

    @Test
    public void testSetQuestionText() {
        // Test the setter for questionText
        question1.setQuestionText("What is CST338?");
        assertEquals("What is CST338?", question1.getQuestionText());
    }

    @Test
    public void testGetCorrectAnswer() {
        // Test the getter for correctAnswer
        assertEquals("Endless Recursion", question1.getCorrectAnswer());
    }

    @Test
    public void testSetCorrectAnswer() {
        // Test the setter for correctAnswer
        question1.setCorrectAnswer("End");
        assertEquals("End", question1.getCorrectAnswer());
    }

    @Test
    public void testGetBadAnswers() {
        // Test the getter for badAnswers
        assertEquals("T7,Rat,Cityscape", question1.getBadAnswers());
    }

    @Test
    public void testSetBadAnswers() {
        // Test the setter for badAnswers
        question1.setBadAnswers("Urban,Animal,Language");
        assertEquals("Urban,Animal,Language", question1.getBadAnswers());
    }

    @Test
    public void testEqualsWithSameValues() {
        // Test equals method for objects with same values
        assertTrue(question1.equals(question2));
    }

    @Test
    public void testEqualsWithDifferentValues() {
        // Test equals method for objects with different values
        question2.setQuestionText("What is JavaDoc?");
        assertFalse(question1.equals(question2));
    }

    @Test
    public void testEqualsWithNull() {
        // Test equals method for null
        assertFalse(question1.equals(null));
    }

    @Test
    public void testHashCode() {
        // Test hashCode for objects with same values
        assertEquals(question1.hashCode(), question2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentValues() {
        // Test hashCode for objects with different values
        question2.setQuestionText("What is JavaDoc?");
        assertNotEquals(question1.hashCode(), question2.hashCode());
    }
}
