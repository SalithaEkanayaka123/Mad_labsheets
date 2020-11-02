package com.example.labsheetsample_2.database;

import android.provider.BaseColumns;
public final class User_Table {
    private User_Table() {}

    /* Inner class that defines the table contents */
    public static class User implements BaseColumns {
        public static final String USER_TABLE_NAME = "user";
        public static final String USER_ID = "uid";
        public static final String USER_NAME = "name";
        public static final String USER_PASSWORD = "password";
        public static final String USER_TYPE = "type";
    }
}
