package com.codewithsean.todosapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codewithsean.todosapp.model.Todo;
import com.codewithsean.todosapp.repository.TodoRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private TodoRepository repository;
    private LiveData<List<Todo>> mTodos;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new TodoRepository(application);
        mTodos = repository.getAllTodos();
    }

    public LiveData<List<Todo>> getAllTodos() {
        return mTodos;
    }

    public LiveData<Todo> getTodoById(long id) {
        return repository.getTodoById(id);
    }

    public void addTodo(Todo todo) {
        repository.addTodo(todo);
    }

    public void updateTodo(Todo todo) {
        repository.updateTodo(todo);
    }

    public void deleteTodo(Todo todo) {
        repository.deleteTodo(todo);
    }
}
