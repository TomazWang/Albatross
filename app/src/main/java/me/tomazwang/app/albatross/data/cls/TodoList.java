package me.tomazwang.app.albatross.data.cls;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by TomazWang on 2016/9/29.
 */

public class TodoList implements Parcelable{

    private long id;
    private String listName;
    private Date createDate;
    private int folderId;

    public TodoList(String listName, int folderId, Date createDate){
        this(0, listName, folderId, createDate);
    }

    public TodoList(long id, String listName, int folderId, Date createDate){
        this.id = id;
        this.listName = listName;
        this.folderId = folderId;
        this.createDate = createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    protected TodoList(Parcel in) {
        id = in.readInt();
        listName = in.readString();
        folderId = in.readInt();
        createDate = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(listName);
        dest.writeInt(folderId);
        dest.writeLong(createDate.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TodoList> CREATOR = new Creator<TodoList>() {
        @Override
        public TodoList createFromParcel(Parcel in) {
            return new TodoList(in);
        }

        @Override
        public TodoList[] newArray(int size) {
            return new TodoList[size];
        }
    };


    // TODO: Override toString
    // TODO: Override hashCode
    // TODO: Override equals
}
