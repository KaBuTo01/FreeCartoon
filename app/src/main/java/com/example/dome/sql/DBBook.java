package com.example.dome.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dome.bean.BookList;
import com.example.dome.bean.SearchList;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DBHleper
 * @Author name
 * @Date 2023/2/16
 * @Description
 */
public class DBBook extends SQLiteOpenHelper {
    private static final String BOOK = "book";
    private static final String ID = "id";
    private static final String IMAGE_URL = "ImageUrl";
    private static final String NAME = "name";
    private static final String URL = "url";
    private static final String TEXT_1 = "text1";
    private static final String TEXT_2 = "text2";
    private static final String TEXT_3 = "text3";

    public DBBook(@Nullable Context context) {
        super(context, BOOK + ".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + BOOK + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IMAGE_URL + " TEXT," + NAME + " TEXT," + URL + " TEXT," + TEXT_1 + " TEXT," + TEXT_2 + " TEXT," + TEXT_3 + " TEXT)";
        db.execSQL(sql);
    }

    public String addBook(BookList list){
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_URL,list.getImageUrl());
        cv.put(NAME,list.getName());
        cv.put(URL,list.getUrl());
        cv.put(TEXT_1,list.getText1());
        cv.put(TEXT_2,list.getText2());
        cv.put(TEXT_3,list.getText3());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long insert = sqLiteDatabase.insert(BOOK, IMAGE_URL, cv);
        if (insert == -1){
            return "fail";
        }
        sqLiteDatabase.close();
        return "success";
    }
    public String deleteBook(String url){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int delete = sqLiteDatabase.delete(BOOK, URL + "=?", new String[]{String.valueOf(url)});
        sqLiteDatabase.close();
        if (delete == 0){
            return "fail";
        }
        return "success";
    }
    public boolean isUrl(String url){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from   "+BOOK+"  where "+URL+"=? ", new String[]{url});
        int urlIndex = cursor.getColumnIndex(URL);
        while (cursor.moveToNext()){
                sqLiteDatabase.close();
                return true;
        }
        sqLiteDatabase.close();
        return false;
    }
    public List<BookList> getBook(){
        List<BookList> list = new ArrayList<>();
        BookList bookList;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + BOOK, null);
        int idIndex = cursor.getColumnIndex(ID);
        int imageUrlIndex = cursor.getColumnIndex(IMAGE_URL);
        int nameIndex = cursor.getColumnIndex(NAME);
        int urlIndex = cursor.getColumnIndex(URL);
        int text1Index = cursor.getColumnIndex(TEXT_1);
        int text2Index = cursor.getColumnIndex(TEXT_2);
        int text3Index = cursor.getColumnIndex(TEXT_3);
        while (cursor.moveToNext()){
            bookList = new BookList(cursor.getInt(idIndex),
                    cursor.getString(imageUrlIndex),
                    cursor.getString(nameIndex),
                    cursor.getString(urlIndex),
                    cursor.getString(text1Index),
                    cursor.getString(text2Index),
                    cursor.getString(text3Index)
                    );
            list.add(bookList);
        }

        return list;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
