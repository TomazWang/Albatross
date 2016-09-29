package me.tomazwang.app.albatross.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import me.tomazwang.app.albatross.data.DBContract.TodoListEntry;

import static android.content.ContentValues.TAG;
import static me.tomazwang.app.albatross.data.DBContract.*;

/**
 * Created by TomazWang on 2016/9/28.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "albatross.db";

    private static DBHelper mDBHelper;


    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getHelper(Context context){

        if(mDBHelper != null){
            return mDBHelper;
        }

        mDBHelper = new DBHelper(context);

        return mDBHelper;

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create db
        Log.d(TAG, "onCreate: create db");

        final String SQL_CREATE_TABLE_LIST =
                "CREATE TABLE " + TodoListEntry.TABLE_NAME
                        + " ("
                        + TodoListEntry._ID + " INTEGER PRIMARY KEY, "
                        + TodoListEntry.COL_LIST_NAME + " TEXT NOT NULL, "
                        + TodoListEntry.COL_CREATE_DATE + " TEXT NOT NULL, "
                        + TodoListEntry.COL_FOLDER_KEY + " INTEGER NOT NULL"
                        + " );";


        final String SQL_CREATE_TABLE_TASK =
                "CREATE TABLE " + TaskEntry.TABLE_NAME
                        + " ("
                        + TaskEntry._ID + " INTEGER PRIMARY KEY, "
                        + TaskEntry.COL_LIST_KEY + " INTEGER NOT NULL, "
                        + TaskEntry.COL_TASK_NAME + " TEXT NOT NULL, "
                        + TaskEntry.COL_CREATE_DATE + " TEXT NOT NULL, "
                        + TaskEntry.COL_IS_CHECKED + " INTEGER NOT NULL"
                        + " );";


        final String SQL_CREATE_TABLE_NOTE =
                "CREATE TABLE " + NoteEntry.TABLE_NAME
                        + " ("
                        + NoteEntry._ID + " INTEGER PRIMARY KEY, "
                        + NoteEntry.COL_TASK_KEY + " INTEGER NOT NULL, "
                        + NoteEntry.COL_LAST_EDIT_DATE + " TEXT, "
                        + NoteEntry.COL_NOTE_CONTEXT + " TEXT"
                        + " );";



        db.execSQL(SQL_CREATE_TABLE_LIST);
        db.execSQL(SQL_CREATE_TABLE_TASK);
        db.execSQL(SQL_CREATE_TABLE_NOTE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // TODO: really upgrade the db use alter table

        db.execSQL("DROP TABLE IF EXISTS "+TodoListEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TaskEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+NoteEntry.TABLE_NAME);

        onCreate(db);

    }
}
