package com.example.emmanuel.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by emmanuel on 11/6/17.
 */

public class InventoryContract {

    public InventoryContract() {}

    public static final class InventoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "stock";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_IMAGE = "image";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                InventoryEntry.TABLE_NAME + "(" +
                InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                InventoryEntry.COLUMN_NAME + " TEXT NOT NULL," +
                InventoryEntry.COLUMN_PRICE + " TEXT NOT NULL," +
                InventoryEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                InventoryEntry.COLUMN_IMAGE + " TEXT NOT NULL" + ");";
    }
}
