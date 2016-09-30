package me.tomazwang.app.albatross.data.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.util.Date;

import me.tomazwang.app.albatross.data.DBContract;
import me.tomazwang.app.albatross.data.DataUtils;
import me.tomazwang.app.albatross.data.cls.Note;

/**
 * Created by TomazWang on 2016/9/30.
 */

public class NoteDAO extends BaseDAO<Note> {


    public NoteDAO(Context context) {

        super(context, DBContract.NoteEntry.TABLE_NAME);
    }

    @Override
    public ContentValues getContentValues(Note note) {

        ContentValues cv = new ContentValues();
        cv.put(DBContract.NoteEntry.COL_TASK_KEY, note.getTaskId());
        cv.put(DBContract.NoteEntry.COL_NOTE_CONTEXT, note.getText());
        cv.put(DBContract.NoteEntry.COL_LAST_EDIT_DATE, DataUtils.dateToString(note.getLastEditedDate()));

        return cv;
    }

    @Override
    public Note parseObject(Cursor c) {


        Note note = null;

        long _id = c.getLong(c.getColumnIndex(DBContract.NoteEntry._ID));
        long _taskKey = c.getLong(c.getColumnIndex(DBContract.NoteEntry.COL_TASK_KEY));
        String _noteText = c.getString(c.getColumnIndex(DBContract.NoteEntry.COL_NOTE_CONTEXT));

        Date _lastEditedDate = new Date();
        try {
            _lastEditedDate = DataUtils.stringToDate(c.getString(c.getColumnIndex(DBContract.NoteEntry.COL_LAST_EDIT_DATE)));
        } catch (ParseException e) {

        }

        return new Note(_id, _taskKey, _noteText, _lastEditedDate);
    }


    @Override
    public void update(Note note) {
        String where = DBContract.NoteEntry._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(note.getId())};

        super.update(note, where, whereArgs);

    }

    @Override
    public void delete(Note note) {
        String where = DBContract.NoteEntry._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(note.getId())};

        super.delete(note, where, whereArgs);

    }

    @Override
    public String getWhereCauseUsingId() {
        return DBContract.NoteEntry._ID +" = ?";
    }
}
