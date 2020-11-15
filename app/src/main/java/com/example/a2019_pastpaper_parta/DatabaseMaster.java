package com.example.a2019_pastpaper_parta;

import android.provider.BaseColumns;

public final class DatabaseMaster {
    private DatabaseMaster() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String USER_TABLE_NAME = "users";
        public static final String USER_USER_NAME = "username";
        public static final String USER_PASSWORD = "password";
        public static final String USER_USERTYPE = "userType";
    }
    public static class Movie implements BaseColumns {
        public static final String MOVIE_TABLE_NAME = "movies";
        public static final String MOVIE_MOVIE_NAME = "moviename";
        public static final String MOVIE_YEAR = "year";
    }
    public static class Comments implements BaseColumns {
        public static final String COMMENTS_TABLE_NAME = "comments";
        public static final String COMMENTS_MOVIE_NAME = "moviename";
        public static final String COMMENTS_MOVIE_RATING = "rating";
        public static final String COMMENTS_MOVIE_COMMENTS = "movie_comments";
    }
}
