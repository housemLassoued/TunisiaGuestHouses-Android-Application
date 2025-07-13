package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "guest_manager.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_GUESTS = "CREATE TABLE guests (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "region TEXT NOT NULL, " +
                "city TEXT NOT NULL, " +
                "price TEXT NOT NULL, " +
                "image TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_GUESTS);
    }
    public boolean updateGuest(int id, String name, String region, String city, String price, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("region", region);
        values.put("city", city);
        values.put("price", price);
        values.put("image", image);

        int result = db.update("guests", values, "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // Méthode pour supprimer un invité
    public boolean deleteGuest(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("guests", "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public Cursor getFilteredGuests(String region, String city, String priceRange) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM guests WHERE region LIKE ? AND city LIKE ? AND " + priceRange;
        return db.rawQuery(query, new String[]{region, city});
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS guests");
        onCreate(db);
    }
}
