package com.codewithsean.todosapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.codewithsean.todosapp.converter.Converters;
import com.codewithsean.todosapp.dao.TodoDao;
import com.codewithsean.todosapp.model.Todo;
import com.codewithsean.todosapp.model.TodoPriority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
@TypeConverters(value = {Converters.class})
public abstract class TodoDatabase extends RoomDatabase {
    private static volatile TodoDatabase INSTANCE;
    private static final String DATABASE_NAME = "todos_db";
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriter = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract TodoDao todoDao();

    public static TodoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, TodoDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
