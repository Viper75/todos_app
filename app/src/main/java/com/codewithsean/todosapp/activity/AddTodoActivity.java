package com.codewithsean.todosapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codewithsean.todosapp.R;
import com.codewithsean.todosapp.adapter.TodoListAdapter;
import com.codewithsean.todosapp.databinding.AddTodoLayoutBinding;
import com.codewithsean.todosapp.model.Todo;
import com.codewithsean.todosapp.model.TodoPriority;
import com.codewithsean.todosapp.viewModel.MainViewModel;

public class AddTodoActivity extends AppCompatActivity implements View.OnClickListener {
    private AddTodoLayoutBinding binding;
    private MainViewModel mViewModel;
    private boolean editMode = false;
    private Todo mTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddTodoLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        long todoId = getIntent().getLongExtra(TodoListAdapter.EXTRA_TODO_ID, 0);

        if (todoId != 0) {
            mViewModel.getTodoById(todoId).observe(this, new Observer<Todo>() {
                @Override
                public void onChanged(Todo todo) {
                    mTodo = todo;
                    binding.todoDescEt.setText(todo.getDescription());
                    binding.todoNotesEt.setText(todo.getNotes());

                    switch (todo.getPriority()) {
                        case HIGH:
                            binding.priorityHighRd.setChecked(true);
                            break;
                        case MEDIUM:
                            binding.priorityMediumRd.setChecked(true);
                            break;
                        case LOW:
                            binding.priorityLowRd.setChecked(true);
                            break;
                    }
                }
            });

            editMode = true;
        }

        binding.addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String todo_desc = binding.todoDescEt.getText().toString();
        String todo_notes = binding.todoNotesEt.getText().toString();
        TodoPriority todoPriority = null;
        switch (binding.prioritiesRg.getCheckedRadioButtonId()) {
            case R.id.priority_low_rd:
                todoPriority = TodoPriority.LOW;
                break;
            case R.id.priority_medium_rd:
                todoPriority = TodoPriority.MEDIUM;
                break;
            case R.id.priority_high_rd:
                todoPriority = TodoPriority.HIGH;
                break;
        }



        if (!editMode) {
            Todo todo = new Todo(todo_desc, todo_notes, todoPriority);
            mViewModel.addTodo(todo);
        }
        else {
            mTodo.setDescription(todo_desc);
            mTodo.setNotes(todo_notes);
            mTodo.setPriority(todoPriority);
            mViewModel.updateTodo(mTodo);
        }


        finish();
    }
}