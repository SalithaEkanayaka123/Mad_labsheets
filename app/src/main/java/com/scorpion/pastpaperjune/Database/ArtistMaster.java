package com.scorpion.pastpaperjune.Database;


import android.provider.BaseColumns;

public final class ArtistMaster {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ArtistMaster() {}

    /* Inner class that defines the table contents */
    public static class Photographs implements BaseColumns {
        public static final String TABLE_NAME = "PhotographDetails";
        public static final String COLUMN_1 = "photographName";
        public static final String COLUMN_2 = "artistID";
        public static final String COLUMN_3 = "PhotoCategory";
        public static final String COLUMN_4 = "Image";
    }
    public static class Artists implements BaseColumns {
        public static final String TABLE_NAME = "ArtistDetails";
        public static final String COLUMN_1 = "artistName";
    }
}
