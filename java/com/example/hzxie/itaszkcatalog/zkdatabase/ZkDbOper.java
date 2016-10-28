package com.example.hzxie.itaszkcatalog.zkdatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hzxie on 2016/9/21.
 */
public class ZkDbOper {

    public static void createDb(SQLiteDatabase dber, String tablename)
    {
        dber.isOpen();
        String sql = "create table "+ "table的名字" + "字段名 字段类型（字段长度） not null";
        dber.execSQL(sql);
    }
    public static void insertDb(SQLiteDatabase dber, String tablename, String[] fileds, String[] values)
    {
        assert(fileds.length == values.length);
        ContentValues cv = new ContentValues();
        for (int i = 0; i < fileds.length; ++i)
        {
            cv.put(fileds[i], values[i]);
        }
        dber.insert(tablename, null, cv);
    }
    public static void onDeleteDb(SQLiteDatabase dber,String sql )
    {
//        String whereClause = "username = ?";//删除的条件子句
//        String[] whereArgs = {"Jack Johnson"};//删除的条件参数
//        mdb.delete("user",whereClause, whereArgs);

//        String sql = "delete from user where username = 'Jack Johnson'";
        dber.execSQL(sql);
    }
    public static void onUpdateDb(SQLiteDatabase dber,String sql)
    {
//        ContentValues cv = new ContentValues();
//        cv.put("password", "love world");
//        String whereClause = "username = ?";//条件子句
//        String[] whereArgs = {"Jack Johnson"};//条件参数
//        mdb.update("user",cv, whereClause,whereArgs);

//        String sql = "update [user] set password = 'love world' where username = 'Jack Johnson'";
        dber.execSQL(sql);
    }
    public static void onSearchDb(SQLiteDatabase dber, String sql )
    {
//        Cursor result = mdb.query("user", null,null,null,null,null,null);
//        if(result.moveToFirst())
//        {
//            for(int i = 0; i < result.getCount(); ++i)
//            {
//                String username = result.getString(result.getColumnIndex("username"));
//                String password = result.getString(result.getColumnIndex("password"));
//
//                Toast.makeText(this,username + password,Toast.LENGTH_SHORT).show();
//
//                result.moveToNext();
//            }
//        }

//        String sql = "select * from user where username = ?";
        Cursor result = dber.rawQuery(sql, new String[]{"Jack Johnson"});
        if(result.moveToFirst())
        {
            String password = result.getString(result.getColumnIndex("password"));
        }
    }

    public static boolean isTableExist(SQLiteDatabase dber, String tablename)
    {
        boolean result = false;
        Cursor c = null;
        try
        {
            String sql = "select count (*) as c from sqlite_master"  +" where type = 'table' and name = '" + tablename.trim() + "'";
            c = dber.rawQuery(sql, null);
            if(c.moveToNext())
            {
                int count = c.getInt(0);
                if(count > 0)
                    result = true;
            }
        }
        catch (Exception ex)
        {

        }
        return result;
    }
}
