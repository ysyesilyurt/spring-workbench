package com.ysyesilyurt.Enum;

public enum Category {
    Greeting("Greeting"),
    Chill("Chill"),
    Educational("Educational"),
    Anger("Anger"),
    Curiosity("Curiosity"),
    Happiness("Happiness");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
