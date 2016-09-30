package me.tomazwang.app.albatross.data.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.util.Date;

import me.tomazwang.app.albatross.data.DBContract;
import me.tomazwang.app.albatross.data.DataUtils;
import me.tomazwang.app.albatross.data.cls.TodoList;

/**
 * Created by TomazWang on 2016/9/29.
 */

public class TodoListDAO extends BaseDAO<TodoList>{



    public TodoListDAO(Context mContext) {
        super(mContext, DBContract.TodoListEntry.TABLE_NAME);
    }


    public ContentValues getContentValues(TodoList todoList){
        ContentValues cv = new ContentValues();

        cv.put(DBContract.TodoListEntry.COL_LIST_NAME, todoList.getListName());
        cv.put(DBContract.TodoListEntry.COL_FOLDER_KEY, todoList.getFolderId());
        cv.put(DBContract.TodoListEntry.COL_CREATE_DATE, DataUtils.dateToString(todoList.getCreateDate()));

        return cv;
    }

    @Override
    public String getWhereCauseUsingId() {
        return DBContract.TodoListEntry._ID + " = ?";
    }

    @Override
    public TodoList parseObject(Cursor c) {


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

        return new TodoList(_id,_name, _folderId, _createDate);
    }

    public void update(TodoList todoList){

        final String where = DBContract.TodoListEntry._ID +" = ?";
        String[] whereArgs = new String[]{String.valueOf(todoList.getId())};

        super.update(todoList, where, whereArgs);
    }

    public void delete(TodoList todoList){
        final String where = DBContract.TodoListEntry._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(todoList.getId())};

        super.delete(todoList, where, whereArgs);

    }






}
