package me.tomazwang.app.albatross.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by TomazWang on 2016/9/29.
 */

public class TodoListDAO {


    protected SQLiteDatabase db;
    private DBHelper mDBHelper;
    private Context mContext;


    public TodoListDAO(Context mContext) {
        this.mContext = mContext;

        mDBHelper = DBHelper.getHelper(mContext);
        open();

    }


    public void open(){
        if(mDBHelper == null){
            mDBHelper = DBHelper.getHelper(mContext);
        }

        db = mDBHelper.getWritableDatabase();
    }


    public ContentValues getContentValues(TodoList todoList){
        ContentValues cv = new ContentValues();

        cv.put(DBContract.TodoListEntry.COL_LIST_NAME, todoList.getListName());
        cv.put(DBContract.TodoListEntry.COL_FOLDER_KEY, todoList.getFolderId());
        cv.put(DBContract.TodoListEntry.COL_CREATE_DATE, DataUtils.dateToString(todoList.getCreateDate()));

        return cv;
    }

    public long insert(TodoList todoList){
        ContentValues cv = getContentValues(todoList);

        long id = db.insert(DBContract.TodoListEntry.TABLE_NAME, null, cv);
        return id;

    }


    public TodoList getTodoListById(int id){

        final String where = DBContract.TodoListEntry._ID +" = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        Cursor c = db.query(DBContract.TodoListEntry.TABLE_NAME,
                    null, // all columns
                    where,
                    whereArgs,
                    null,
                    null,
                    null
                    );

        TodoList todoList = null;

        while (c.moveToFirst()){
            int _id = c.getInt(c.getColumnIndex(DBContract.TodoListEntry._ID));
            String _name = c.getString(c.getColumnIndex(DBContract.TodoListEntry.COL_LIST_NAME));
            String _createDateText = c.getString(c.getColumnIndex(DBContract.TodoListEntry.COL_CREATE_DATE));
            Date _createDate;

            try {
                _createDate = DataUtils.stringToDate(_createDateText);
            } catch (ParseException e) {
                _createDate = new Date();
            }

            int _folderId = c.getInt(c.getColumnIndex(DBContract.TodoListEntry.COL_FOLDER_KEY));


            todoList = new TodoList(_id,_name,_folderId,_createDate);
        }

        return todoList;

    }

    public ArrayList<TodoList> getTodoLists(){
        return getTodoLists(null);
    }

    public ArrayList<TodoList> getTodoLists(int[] ids){

        ArrayList<TodoList> todoLists = new ArrayList<>();

        final String where = DBContract.TodoListEntry._ID +" = ?";
        String[] whereArgs = null;

        if(ids != null){
            whereArgs = new String[ids.length];
            for(int i=0; i<ids.length; i++){
                whereArgs[i] = String.valueOf(ids[i]);
            }
        }

        Cursor c = db.query(DBContract.TodoListEntry.TABLE_NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                DBContract.TodoListEntry._ID
                );


        c.moveToFirst();
        do{
            long _id = c.getLong(c.getColumnIndex(DBContract.TodoListEntry._ID));
            String _name = c.getString(c.getColumnIndex(DBContract.TodoListEntry.COL_LIST_NAME));
            String _createDateText = c.getString(c.getColumnIndex(DBContract.TodoListEntry.COL_CREATE_DATE));
            Date _createDate;

            try {
                _createDate = DataUtils.stringToDate(_createDateText);
            } catch (ParseException e) {
                _createDate = new Date();
            }

            int _folderId = c.getInt(c.getColumnIndex(DBContract.TodoListEntry.COL_FOLDER_KEY));


            TodoList todoList = new TodoList(_id,_name,_folderId,_createDate);
            todoLists.add(todoList);
        }while (c.moveToNext());

        return todoLists;

    }



    public void update(TodoList todoList){

        final String where = DBContract.TodoListEntry._ID +" = ?";
        String[] whereArgs = new String[]{String.valueOf(todoList.getId())};

        ContentValues cv = getContentValues(todoList);
        db.update(DBContract.TodoListEntry.TABLE_NAME, cv, where, whereArgs);

    }

    public void delete(TodoList todoList){
        final String where = DBContract.TodoListEntry._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(todoList.getId())};

        ContentValues cv = getContentValues(todoList);
        db.delete(DBContract.TodoListEntry.TABLE_NAME, where, whereArgs);

    }






}
