package com.example.project02_triviaapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    private String categoryText;

    public Category(@NonNull String categoryText) {
        this.categoryText = categoryText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId && Objects.equals(categoryText, category.categoryText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryText);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(String categoryText) {
        this.categoryText = categoryText;
    }
}
