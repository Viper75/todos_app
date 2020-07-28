package com.codewithsean.todosapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codewithsean.todosapp.R;
import com.codewithsean.todosapp.adapter.TodoListAdapter;
import com.codewithsean.todosapp.databinding.MainLayoutBinding;
import com.codewithsean.todosapp.model.Todo;
import com.codewithsean.todosapp.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainLayoutBinding mBinding;
    private MainViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = MainLayoutBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        final TodoListAdapter adapter = new TodoListAdapter(this);

        mBinding.todoListRv.setLayoutManager(new LinearLayoutManager(this));
        mBinding.todoListRv.setAdapter(adapter);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getAllTodos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter.setTodos(todos);
            }
        });

        mBinding.addTodoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                startActivity(intent);
            }
        });
    }
}