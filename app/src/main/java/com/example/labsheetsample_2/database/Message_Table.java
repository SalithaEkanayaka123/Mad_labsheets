package com.example.labsheetsample_2.database;

import android.provider.BaseColumns;

public class Message_Table {
    private Message_Table() {}
    public static class Message implements BaseColumns {
        public static final String MESSAGE_TABLE_NAME = "message";
        public static final String MESSAGE_ID = "mid";
        public static final String MESSAGE_USER_NAME = "user";
        public static final String MESSAGE_SUBJECT = "subject";
        public static final String MESSAGE_MESSAGE = "msg";
    }
}
