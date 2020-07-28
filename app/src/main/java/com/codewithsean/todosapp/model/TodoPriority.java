package com.codewithsean.todosapp.model;

public enum TodoPriority {
    LOW(3),
    MEDIUM(2),
    HIGH(1);

    private int level;

    TodoPriority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
