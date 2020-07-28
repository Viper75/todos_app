package com.codewithsean.todosapp.converter;

import androidx.room.TypeConverter;

import com.codewithsean.todosapp.model.TodoPriority;

import java.util.Date;

public class Converters {
    @TypeConverter
    public Date fromTimestamp(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public TodoPriority fromInt(int level) {
        for (TodoPriority priority : TodoPriority.values()) {
            if (priority.getLevel() == level)
                return priority;
        }
        return null;
    }

    @TypeConverter
    public Integer toInt(TodoPriority priority) {
        return priority == null ? null : priority.getLevel();
    }
}
