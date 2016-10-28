package com.example.hzxie.itaszkcatalog;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hzxie.itaszkcatalog.zkdatabase.ZkDbContract;
import com.example.hzxie.itaszkcatalog.zkdatabase.ZkDbHelper;
import com.example.hzxie.itaszkcatalog.zkdatabase.ZkDbOper;
import com.example.hzxie.itaszkcatalog.zkwebservice.NetworkActivity;

public class ZkCatalogActivity extends Activity {

    public  final static String LOCATION_MESSAGE = "com.example.hzxie.itascatalog.locationmessage";
    private EditText mlongitude;
    private EditText mlatitude;
    private EditText mcode;
    private Spinner mtype;
    private EditText mcoordx;
    private EditText mcoordy;
    private EditText mheight;
    private EditText mdepth;
    private EditText mdirection;
    private EditText mdegree;
    private EditText mcoverdepth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zk_catalog);
        Log.e("debug", "onStartZKCatalog: create" );

        String[] messages = this.getIntent().getStringArrayExtra(LOCATION_MESSAGE);

        Log.e("debug", "onStartZKCatalog: create11" );

        assert(messages.length == 2);
        this.initUi(messages);
    }

    private void initUi(String[] messages)
    {
        mlongitude =(EditText)findViewById(R.id.longtitude);
        mlongitude.setText(messages[0]);
        mlatitude =(EditText)findViewById(R.id.latitude);
        mlatitude.setText(messages[1]);
        mcode =(EditText)findViewById(R.id.code);
        mtype =(Spinner)findViewById(R.id.type);
        mcoordx =(EditText)findViewById(R.id.coordx);
        mcoordy =(EditText)findViewById(R.id.coordy);
        mheight =(EditText)findViewById(R.id.height);
        mdepth =(EditText)findViewById(R.id.depth);
        mdirection =(EditText)findViewById(R.id.direction);
        mdegree =(EditText)findViewById(R.id.degree);
        mcoverdepth =(EditText)findViewById(R.id.coverdepth);

    }
    public void btn_OnOKZKCatalog(View view)
    {
        try
        {
            ZkDbHelper dbHelper = new ZkDbHelper(this);
            SQLiteDatabase dber = dbHelper.getWritableDatabase();
            if(dber == null) return ;

            if(!dber.isOpen())return;

            //判断是否存在表，不存在，则创建
            try
            {
                if(! ZkDbOper.isTableExist(dber, ZkDbContract.ZKBasicInfo.TABLE_NAME))
                {
                    dber.execSQL(ZkDbContract.ZKBasicInfo.createSQL());
                }
            }
            catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT);
            }


            String sql = ZkDbContract.ZKBasicInfo.insertSQL() + "("+
                    mlongitude.getText().toString() +","+
                    mlatitude.getText().toString() +","+
                    "'"+mcode.getText().toString()+ "'" +","+
                    "'"+ mtype.getSelectedItem().toString() +"'"+","+
                    mcoordx.getText().toString() +","+
                    mcoordy.getText().toString() +","+
                    mheight.getText().toString() +","+
                    mdepth.getText().toString() +","+
                    mdirection.getText().toString() +","+
                    mdegree.getText().toString() +","+
                    mcoverdepth.getText().toString()
                    +")" ;
            dber.execSQL(sql);
            msql = sql;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            Log.e("Catalog","save......");
        }
        this.finish();
    }
    String  msql = null;
    public void btn_OnCancelCatalog(View view)
    {
        mcode.setText("");
        mtype.setSelection(0);
        mcoordx.setText("");
        mcoordy.setText("");
        mheight.setText("");
        mdepth.setText("");
        mdirection.setText("");
        mdegree.setText("");
        mcoverdepth.setText("");
    }
}
