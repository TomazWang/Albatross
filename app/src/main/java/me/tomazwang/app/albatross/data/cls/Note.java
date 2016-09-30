package me.tomazwang.app.albatross.data.cls;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by TomazWang on 2016/9/30.
 */

public class Note implements Parcelable{

    private long id;
    private long taskId;
    private String text;

    private Date lastEditedDate;

    public Note(long id, long taskId, String text, Date lastEditedDate) {
        this.id = id;
        this.taskId = taskId;
        this.text = text;
        this.lastEditedDate = lastEditedDate;
    }


    public Note(long taskId, String text, Date lastEditedDate) {
        this(0,taskId,text,lastEditedDate);
    }

    protected Note(Parcel in) {
        id = in.readLong();
        taskId = in.readLong();
        text = in.readString();
        lastEditedDate = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(taskId);
        dest.writeString(text);
        dest.writeLong(lastEditedDate.getTime());
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Date lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
