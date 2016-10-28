package com.example.hzxie.itaszkcatalog.zkdatabase;

import android.database.sqlite.SQLiteDatabase;
import android.os.ParcelUuid;
import android.provider.BaseColumns;

/**
 * Created by hzxie on 2016/9/21.
 * Container of Constants
 * URL
 * Table
 * Column
 * Field
 */
public final class ZkDbContract {
    public ZkDbContract()
    {

    }

    public static final String DBNAME = "zks.db";//数据库文件名
    public static final int DBVERSION = 1;//数据库版本
    public static final String PRIMARY_KEY = "iid";//表的主键 统一

    public static abstract class ZKBasicInfo implements BaseColumns
    {
        public static final String TABLE_NAME = "boreholeInfo";//表名

        public static final String LONGITUDE_COLUMN = "longitude";
        public static final String LATITUDE_COLUMN = "latitude";
        public static final String CODE_COLUMN = "code";
        public static final String TYPE_COLUMN = "borehole_type";
        public static final String COORDX_COLUMN = "X";
        public static final String COORDY_COLUMN = "Y";
        public static final String COORDZ_COLUMN = "Z";
        public static final String DEPTH_COLUMN = "HoleDepth";
        public static final String DIRECTION_COLUMN = "direction";
        public static final String DEGREE_COLUMN = "OpeningAngle";
        public static final String COVERDEPTH_COLUMN = "thickness_cover";

        public static final String createSQL()
        {
            String sql = "create table " + TABLE_NAME + " ("
                    +PRIMARY_KEY + " integer not null primary key AUTOINCREMENT,"
                    +LONGITUDE_COLUMN + " real not null,"
                    +LATITUDE_COLUMN + " real not null,"
                    +CODE_COLUMN +" varchar(64) not null collate NOCASE,"
                    +TYPE_COLUMN +" integer,"
                    +COORDX_COLUMN +" real not null,"
                    +COORDY_COLUMN +" real not null,"
                    +COORDZ_COLUMN +" real not null,"
                    +DEPTH_COLUMN + " real,"
                    +DIRECTION_COLUMN + " real,"
                    +DEGREE_COLUMN + " real,"
                    +COVERDEPTH_COLUMN + " real"
                    +")";
            return sql;
        }
        public static final String insertSQL()
        {
            String sql = "insert into " + TABLE_NAME + " (" +
                    LONGITUDE_COLUMN + ","+
                    LATITUDE_COLUMN + ","+
                    CODE_COLUMN + ","+
                    TYPE_COLUMN + ","+
                    COORDX_COLUMN + ","+
                    COORDY_COLUMN + ","+
                    COORDZ_COLUMN + ","+
                    DEPTH_COLUMN + ","+
                    DIRECTION_COLUMN + ","+
                    DEGREE_COLUMN + ","+
                    COVERDEPTH_COLUMN+")"+ "values ";

            return sql;
        }
    }

    public static abstract class ZkCfgUser implements BaseColumns
    {
        public static final String TABLE_NAME = "Cfg_User";
        public static final String USERNAME_COLUMN = "user_name";
        public static final String PASSWORDS_COLUMN = "passwords";
        public static final String DISPLAYNAME_COLUMN = "display_name";
        public static final String COMMENTTEXT_COLUMN = "comment_text";
        public static final String DISABLED_COLUMN = "disabled";
        public static final String EMAIL_COLUMN = "email";
    }

}
