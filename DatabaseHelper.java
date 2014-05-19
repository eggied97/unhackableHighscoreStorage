/*
 * Copyright (c) 2014. EDeveloping, Egbert Dijkstra
 */

package INSERT_PACKAGE_HERE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Egbert Dijkstra on 12-4-2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    
    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;	
    private static final String DATABASE_NAME = "NAME_OF_DATABASE_HERE";
    private static final String TABLE_SCORE = "scoreTable";
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE = "score";
    private static final String CREATE_TABLE_SCORE = "CREATE TABLE " + TABLE_SCORE 
												   + "(" + KEY_ID + " INTEGER PRIMARY KEY," 
												   + KEY_SCORE + " INTEGER)";
												   
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {        
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);
    }

    private void initValues(){
        setScore(0);
    }

    public int getScore() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SCORE + " WHERE " + KEY_ID + " = " + 0;

        Log.d(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null){
            c.moveToFirst();
		}else{
			//there are not any scores in the database yet, call initValues(), and then getScore() again.
			initValues();
			return getScore();
		}

        int a = c.getInt(c.getColumnIndex(KEY_SCORE));

        return a;
    }

    public int updateScore(int a) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, a);
		
        return db.update(TABLE_SCORE, values, KEY_ID + " = ?", new String[] { String.valueOf(0) });
    }

    private void setScore(int a) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID , 0);
        values.put(KEY_SCORE, a);

        // insert row
        long b = db.insert(TABLE_SCORE, null, values);
        Log.d("setScore" , b+"");
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
