package me.tomazwang.app.albatross.data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by TomazWang on 2016/9/29.
 */

public class TestValue {


    static void validateCursor(String error, Cursor valueCursor, ContentValues expectedValues) {
        assertTrue("Empty cursor returned. " + error, valueCursor.moveToFirst());
        validateCurrentRecord(error, valueCursor, expectedValues);
        valueCursor.close();
    }

    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() +
                    "' did not match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }


    static ContentValues createTestTodoList() {

        ContentValues cv = new ContentValues();

        cv.put(DBContract.TodoListEntry.COL_LIST_NAME, "Test List");
        cv.put(DBContract.TodoListEntry.COL_FOLDER_KEY, 0);
        cv.put(DBContract.TodoListEntry.COL_CREATE_DATE, "2016-09-29");

        return cv;

    }


    static ContentValues createTestTask() {

        ContentValues cv = new ContentValues();

        cv.put(DBContract.TaskEntry.COL_LIST_KEY, 0);
        cv.put(DBContract.TaskEntry.COL_CREATE_DATE, "2016-01-01");
        cv.put(DBContract.TaskEntry.COL_IS_CHECKED, 0);
        cv.put(DBContract.TaskEntry.COL_TASK_NAME, "test task");

        return cv;
    }
}
