package me.bxhuynh.lab11_noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "NoteDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "notes";
    private static final String ID_COL = "id";
    private static final String TITLE_COL = "title";
    private static final String CONTENT_COL = "content";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT, "
                + CONTENT_COL + " TEXT)";
        sqLiteDatabase.execSQL(query);
        Log.d("DB", "Created databaseeee");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addNewNote(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE_COL, title);
        values.put(CONTENT_COL, content);
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<NoteModel> readNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "  + TABLE_NAME, null);
        ArrayList<NoteModel> noteArr = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                noteArr.add(new NoteModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return noteArr;
    }

    public void updateNote(int id, String newTitle, String newContent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE_COL, newTitle);
        values.put(CONTENT_COL, newContent);
        db.update(TABLE_NAME, values, "id=?", new String[]{Integer.toString(id)});
    }

    public void deleteNoteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{Integer.toString(id)});

    }
}
