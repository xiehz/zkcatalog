package com.example.hzxie.itaszkcatalog.zkdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;


/**
 * Created by hzxie on 2016/9/21.
 */
public class ZkDbHelper extends SQLiteOpenHelper {
    public ZkDbHelper(Context context) {
        super(context, ZkDbContract.DBNAME, null, ZkDbContract.DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String sql = "create table "+ "table的名字" + "字段名 字段类型（字段长度） not null";
//        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
