package me.tomazwang.app.albatross.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by TomazWang on 2016/9/29.
 */

public class Task implements Parcelable{

    private long id;
    private long listId;
    private String taskName;
    private Date createDate;
    private boolean isChecked;

    public Task(long listId, String taskName, Date createDate, boolean isChecked) {
        this(0,listId, taskName, createDate, isChecked);
    }

    public Task(long id, long listId, String taskName, Date createDate, boolean isChecked) {
        this.id = id;
        this.listId = listId;
        this.taskName = taskName;
        this.createDate = createDate;
        this.isChecked = isChecked;
    }

    protected Task(Parcel in) {
        id = in.readLong();
        listId = in.readLong();
        taskName = in.readString();
        createDate = new Date(in.readLong());
        isChecked = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(listId);
        dest.writeString(taskName);
        dest.writeLong(createDate.getTime());
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isChecked() {
        return isChecked;
    }


    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
