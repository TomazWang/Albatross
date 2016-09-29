package me.tomazwang.app.albatross.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by TomazWang on 2016/9/29.
 */

public class TaskDAO extends BaseDAO<Task> {


    public TaskDAO(Context context, String tableName) {
        super(context, tableName);
    }

    @Override
    public ContentValues getContentValues(Task task) {

        ContentValues cv = new ContentValues();
        cv.put(DBContract.TaskEntry.COL_TASK_NAME, task.getTaskName());
        cv.put(DBContract.TaskEntry.COL_LIST_KEY, task.getListId());
        cv.put(DBContract.TaskEntry.COL_IS_CHECKED, (task.isChecked()? 1:0) );
        cv.put(DBContract.TaskEntry.COL_CREATE_DATE, DataUtils.dateToString(task.getCreateDate()));

        return cv;
    }

    @Override
    public Task parseObject(Cursor c) {

        Task task = null;

        long _id = c.getLong(c.getColumnIndex(DBContract.TaskEntry._ID));
        String _taskName = c.getString(c.getColumnIndex(DBContract.TaskEntry.COL_TASK_NAME));
        long _listId = c.getLong(c.getColumnIndex(DBContract.TaskEntry.COL_LIST_KEY));
        boolean _isChecked = c.getInt(c.getColumnIndex(DBContract.TaskEntry.COL_IS_CHECKED)) > 0;

        Date _createDate = new Date();
        try {
            _createDate = DataUtils.stringToDate(c.getString(c.getColumnIndex(DBContract.TaskEntry.COL_CREATE_DATE)));
        } catch (ParseException e) {

        }

        return new Task(_id, _listId, _taskName, _createDate, _isChecked);
    }

    @Override
    public String getWhereCauseUsingId() {
        return DBContract.TaskEntry._ID +" = ?";
    }

    @Override
    public void update(Task task) {
        String where = DBContract.TaskEntry._ID +" = ?";
        String[] whereArgs = new String[]{String.valueOf(task.getId())};

        super.update(task,where,whereArgs);
    }

    @Override
    public void delete(Task task) {
        String where = DBContract.TaskEntry._ID +" = ?";
        String[] whereArgs = new String[]{String.valueOf(task.getId())};

        super.delete(task,where,whereArgs);
    }
}
