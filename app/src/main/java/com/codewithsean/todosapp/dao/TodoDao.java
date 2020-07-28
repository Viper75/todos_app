package com.codewithsean.todosapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.codewithsean.todosapp.model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Query("SELECT * FROM todos WHERE id = :id")
    LiveData<Todo> getTodoById(long id);

    @Query("SELECT * FROM todos")
    LiveData<List<Todo>> getAllTodos();
}
