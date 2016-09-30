package me.tomazwang.app.albatross.data.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import me.tomazwang.app.albatross.data.DBHelper;

/**
 * Created by TomazWang on 2016/9/29.
 */

public abstract class BaseDAO<E> implements AbstractDAO<E>{

    private final String mTableName;
    private SQLiteDatabase db;
    private DBHelper mDBHelper;
    private Context mContext;

    public BaseDAO(Context context, String tableName) {

        this.mContext = context;
        this.mTableName = tableName;

        mDBHelper = DBHelper.getHelper(mContext);
        open();

    }

    public String getTableName() {
        return mTableName;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public DBHelper getmDBHelper() {
        return mDBHelper;
    }

    public Context getmContext() {
        return mContext;
    }



    @Override
    public void open() {
        if(mDBHelper == null){
            mDBHelper = DBHelper.getHelper(mContext);
        }

        db = mDBHelper.getWritableDatabase();
    }

    @Override
    public long insert(E e) {
        ContentValues cv = getContentValues(e);

        return db.insert(mTableName, null, cv);
    }


    @Override
    public E getItemById(long id) {

        final String where = getWhereCauseUsingId();
        String[] whereArgs = new String[]{String.valueOf(id)};

        Cursor c = db.query(mTableName,
                null, // all columns
                where,
                whereArgs,
                null,
                null,
                null
        );

        E data = null;

        while (c.moveToFirst()){

            data = parseObject(c);

        }

        return data;


    }

    @Override
    public ArrayList<E> getItemById(long[] ids, String orderColName) {

        ArrayList<E> dataList = new ArrayList<>();

        final String where = getWhereCauseUsingId();
        String[] whereArgs = null;

        if(ids != null){
            whereArgs = new String[ids.length];
            for(int i=0; i<ids.length; i++){
                whereArgs[i] = String.valueOf(ids[i]);
            }
        }

        Cursor c = db.query(mTableName,
                null,
                where,
                whereArgs,
                null,
                null,
                orderColName
        );


        c.moveToFirst();
        do{
            E data = parseObject(c);
            dataList.add(data);
        }while (c.moveToNext());

        return dataList;

    }

    @Override
    public ArrayList<E> getAll(String order) {
        return  getItemById(null, order);
    }


    @Override
    public void update(E e, String where, String[] whereArgs) {

        ContentValues cv = getContentValues(e);
        db.update(mTableName, cv, where, whereArgs);
    }

    @Override
    public void delete(E e, String where, String[] whereArgs) {
        db.delete(mTableName, where, whereArgs);
    }
}
