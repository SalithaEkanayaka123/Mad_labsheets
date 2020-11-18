package com.example.data_handling.Database;

import android.provider.BaseColumns;

public final class User {
    private User() {
    }
    public static class users implements BaseColumns {
        public static final String USER_TABLE_NAME = "user";
        public static final String USER_ID = "id";
        public static final String USER_NAME = "username";
        public static final String USER_PASSWORD = "password";
    }

}