package com.codewithsean.todosapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.codewithsean.todosapp.dao.TodoDao;
import com.codewithsean.todosapp.database.TodoDatabase;
import com.codewithsean.todosapp.model.Todo;

import java.util.List;

public class TodoRepository {
    private TodoDao mTodoDao;
    private LiveData<List<Todo>> mTodos;

    public TodoRepository(Application application) {
        TodoDatabase todoDatabase = TodoDatabase.getInstance(application.getApplicationContext());
        mTodoDao = todoDatabase.todoDao();
        mTodos = mTodoDao.getAllTodos();
    }

    public LiveData<Todo> getTodoById(final long id) {
        return mTodoDao.getTodoById(id);
    }

    public LiveData<List<Todo>> getAllTodos() {
        return mTodoDao.getAllTodos();
    }

    public void addTodo(final Todo todo) {
        TodoDatabase.databaseWriter.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.addTodo(todo);
            }
        });
    }

    public void updateTodo(final Todo todo) {
        TodoDatabase.databaseWriter.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.updateTodo(todo);
            }
        });
    }

    public void deleteTodo(final Todo todo) {
        TodoDatabase.databaseWriter.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.deleteTodo(todo);
            }
        });
    }
}
