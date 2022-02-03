package com.erengulbahar.moviedb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(@Nullable Context context)
    {
        super(context,"Moviesx.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //We create table for database here.
        db.execSQL("CREATE TABLE Movie(id INTEGER PRIMARY KEY, Genre VARCHAR, Title VARCHAR, Overview VARCHAR, Image VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //We upgrade datas which in database here.
        db.execSQL("DROP TABLE IF EXISTS Movie");
        onCreate(db);
    }

    public void insertData(String genre, String title, String overView, String image)
    {
        //We save datas here.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Genre",genre);
        contentValues.put("Title",title);
        contentValues.put("Overview",overView);
        contentValues.put("Image",image);

        db.insert("Movie",null,contentValues);
    }

    public void deleteData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Movie");
        onCreate(db);
    }
}