package me.tomazwang.app.albatross.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.*;

/**
 * Created by TomazWang on 2016/9/29.
 */
@RunWith(AndroidJUnit4.class)
public class DBHelperTest {


    private Context mContext;

    @Before
    public void setUp() throws Exception {

        this.mContext = InstrumentationRegistry.getTargetContext();
        deleteDB(mContext);

    }

    void deleteDB(Context ctx) {
        Log.d(TAG, "deleteDB");
        ctx.deleteDatabase(DBHelper.DATABASE_NAME);
    }

    @Test
    public void onCreate() throws Exception {

        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(DBContract.TodoListEntry.TABLE_NAME);
        tableNameHashSet.add(DBContract.TaskEntry.TABLE_NAME);
        tableNameHashSet.add(DBContract.NoteEntry.TABLE_NAME);

        mContext.deleteDatabase(DBHelper.DATABASE_NAME);
        SQLiteDatabase db = new DBHelper(
                this.mContext).getWritableDatabase();

        assertEquals(true, db.isOpen());

        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0));
        } while (c.moveToNext());

        // if this fails, it means that your database doesn't contain both the location entry
        // and weather entry tables
        assertTrue("Error: Your database was created without both the location entry and weather entry tables",
                tableNameHashSet.isEmpty());


        // -- Testing TABLE_NAME
        testColum(db, c,
                DBContract.TodoListEntry.TABLE_NAME,
                DBContract.TodoListEntry._ID,
                DBContract.TodoListEntry.COL_LIST_NAME,
                DBContract.TodoListEntry.COL_CREATE_DATE,
                DBContract.TodoListEntry.COL_FOLDER_KEY);

        // -- Testing TABLE_NAME
        testColum(db, c,
                DBContract.TaskEntry.TABLE_NAME,
                DBContract.TaskEntry._ID,
                DBContract.TaskEntry.COL_LIST_KEY,
                DBContract.TaskEntry.COL_TASK_NAME,
                DBContract.TaskEntry.COL_CREATE_DATE,
                DBContract.TaskEntry.COL_IS_CHECKED);


        // -- Testing TABLE_NAME
        testColum(db, c,
                DBContract.NoteEntry.TABLE_NAME,
                DBContract.NoteEntry._ID,
                DBContract.NoteEntry.COL_TASK_KEY,
                DBContract.NoteEntry.COL_LAST_EDIT_DATE,
                DBContract.NoteEntry.COL_LAST_EDIT_DATE,
                DBContract.NoteEntry.COL_NOTE_CONTEXT);


        db.close();

    }

    private void testColum(SQLiteDatabase db, Cursor c, String tableName, String... colName) {

        c = db.rawQuery("PRAGMA table_info(" + tableName + ")", null);

        assertTrue("Error: This means that we were unable to query the database for table information.", c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> columnNameSet = new HashSet<String>();

        for (int i = 0; i < colName.length; i++) {
            columnNameSet.add(colName[i]);
        }

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            Log.d(TAG, "onCreate: colum in "+ tableName+" titled " + columnName);
            columnNameSet.remove(columnName);
        } while (c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns

        assertTrue("Error: The database doesn't contain all of the required "+ tableName +" columns"
                        + Arrays.toString(columnNameSet.toArray()),
                columnNameSet.isEmpty());


    }

    @Test
    public void testTableList(){

        SQLiteDatabase db = new DBHelper(this.mContext).getWritableDatabase();

        assertEquals(true, db.isOpen());

        // -- add value
        ContentValues cv = TestValue.createTestTodoList();

        long rowId = db.insert(DBContract.TodoListEntry.TABLE_NAME, null, cv);

        assertFalse("row id = -1 when querying "+DBContract.TodoListEntry.TABLE_NAME, rowId == -1);

        Cursor c = db.query(
                DBContract.TodoListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        TestValue.validateCursor("table "+DBContract.TodoListEntry.TABLE_NAME+" failed to validate.",c, cv);

        assertFalse("Error: more than one row return from db", c.moveToNext());

        c.close();

        db.close();

    }


    @Test
    public void testTableTask(){

        SQLiteDatabase db = new DBHelper(this.mContext).getWritableDatabase();

        assertEquals(true, db.isOpen());

        // -- add value
        ContentValues cv = TestValue.createTestTask();

        long rowId = db.insert(DBContract.TaskEntry.TABLE_NAME, null, cv);

        assertFalse("row id = -1 when querying "+DBContract.TaskEntry.TABLE_NAME, rowId == -1);

        Cursor c = db.query(
                DBContract.TaskEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        TestValue.validateCursor("table "+DBContract.TaskEntry.TABLE_NAME+" failed to validate.",c, cv);

        assertFalse("Error: more than one row return from db", c.moveToNext());

        c.close();

        db.close();

    }






}