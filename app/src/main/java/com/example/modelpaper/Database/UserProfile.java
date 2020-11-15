package com.example.modelpaper.Database;

import android.provider.BaseColumns;

public final class UserProfile {
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String USER_ID = "ID";
        public static final String USER_NAME = "userName";
        public static final String USER_DOB = "dateOfBirth";
        public static final String USER_PASSWORD = "password";
        public static final String USER_GENDER = "gender";
    }
}
