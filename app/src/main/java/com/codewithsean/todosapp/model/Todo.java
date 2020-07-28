package com.codewithsean.todosapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todos")
public class Todo {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String description;
    private String notes;
    private TodoPriority priority;
    @ColumnInfo(name = "date_created")
    private Date dateCreated;

    public Todo(String description, String notes, TodoPriority priority) {
        this.description = description;
        this.notes = notes;
        this.priority = priority;
        dateCreated = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public TodoPriority getPriority() {
        return priority;
    }

    public void setPriority(TodoPriority priority) {
        this.priority = priority;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
