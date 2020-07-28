package com.codewithsean.todosapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithsean.todosapp.activity.AddTodoActivity;
import com.codewithsean.todosapp.databinding.TodoItemBinding;
import com.codewithsean.todosapp.model.Todo;

import java.text.SimpleDateFormat;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoItemViewHolder> {
    public static final String EXTRA_TODO_ID = "todoId";

    class TodoItemViewHolder extends RecyclerView.ViewHolder {
        public TodoItemBinding binding;

        public TodoItemViewHolder(TodoItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    private List<Todo> mTodos;
    private LayoutInflater mInflater;
    private Context mContext;

    public TodoListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoItemViewHolder(TodoItemBinding.inflate(mInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull final TodoItemViewHolder holder, int position) {
        if (mTodos != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            final Todo todo = mTodos.get(position);
            holder.binding.todoDescTv.setText(todo.getDescription());
            holder.binding.todoDateCreatedTv.setText(sdf.format(todo.getDateCreated()));

            holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, AddTodoActivity.class);
                    intent.putExtra(EXTRA_TODO_ID, todo.getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mTodos != null)
            return mTodos.size();
        return 0;
    }

    public void setTodos(List<Todo> mTodos) {
        this.mTodos = mTodos;
        notifyDataSetChanged();
    }
}
