package me.tomazwang.app.albatross;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.tomazwang.app.albatross.DBContract.TodoListEntry;

import static me.tomazwang.app.albatross.DBContract.*;

/**
 * Created by TomazWang on 2016/9/28.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "albatross.db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create db

        final String SQL_CREATE_TABLE_LIST =
                "CREATE TABLE " + TodoListEntry.TABLE_TODOLIST
                        + " ("
                        + TodoListEntry._ID + "INTEGER PRIMARY KEY, "
                        + TodoListEntry.COL_LIST_NAME + "TEXT NOT NULL, "
                        + TodoListEntry.COL_CREATE_DATE + "TEXT NOT NULL, "
                        + TodoListEntry.COL_FOLDER_KEY + "INTEGER NOT NULL"
                        + " );";


        final String SQL_CREATE_TABLE_TASK =
                "CREATE TABLE " + TaskEntry.TABLE_TASK
                        + " ("
                        + TaskEntry._ID + "INTEGER PRIMARY KEY, "
                        + TaskEntry.COL_LIST_KEY + "INTEGER NOT NULL, "
                        + TaskEntry.COL_TASK_NAME + "TEXT NOT NULL, "
                        + TaskEntry.COL_CREATE_DATE + "TEXT NOT NULL, "
                        + TaskEntry.COL_IS_CHECKED + "BOLB NOT NULL"
                        + " );";


        final String SQL_CREATE_TABLE_NOTE =
                "CREATE TABLE " + NoteEntry.TABLE_NOTE
                        + " ("
                        + NoteEntry._ID + "INTEGER PRIMARY KEY, "
                        + NoteEntry.COL_TASK_KEY + "INTEGER NOT NULL, "
                        + NoteEntry.COL_LAST_EDIT_DATE + "TEXT, "
                        + NoteEntry.COL_NOTE_CONTEXT + "TEXT"
                        + " );";



        db.execSQL(SQL_CREATE_TABLE_LIST);
        db.execSQL(SQL_CREATE_TABLE_TASK);
        db.execSQL(SQL_CREATE_TABLE_NOTE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // TODO: really upgrade the db

        db.execSQL("DROP TABLE IF EXISTS "+TodoListEntry.TABLE_TODOLIST);
        db.execSQL("DROP TABLE IF EXISTS "+TaskEntry.TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS "+NoteEntry.TABLE_NOTE);

        onCreate(db);



    }
}
