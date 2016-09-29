package me.tomazwang.app.albatross.data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/9/29.
 */


interface AbstractDAO<E> {

    ContentValues getContentValues(E e);
    E parseObject(Cursor c);

    void open();

    long insert(E e);

    E getItemById(long id);
    ArrayList<E> getItemById(long[] id, String order);
    ArrayList<E> getAll(String order);

    void update(E e, String where, String[] whereArgs);
    void delete(E e, String where, String[] whereArgs);

    void update(E e);
    void delete(E e);

    String getWhereCauseUsingId();





}
