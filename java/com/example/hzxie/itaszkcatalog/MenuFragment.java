package com.example.hzxie.itaszkcatalog;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hzxie.itaszkcatalog.zkdatabase.ZkDbContract;
import com.example.hzxie.itaszkcatalog.zkdatabase.ZkDbHelper;
import com.example.hzxie.itaszkcatalog.zkdatabase.ZkDbOper;
import com.example.hzxie.itaszkcatalog.zkwebservice.WebserviceConstant;
import com.example.hzxie.itaszkcatalog.zkwebservice.WebserviceSoaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    private View currentView;
    private ImageView iv_login;
    private Button btn_sync;
    private String user_name;
    private final int MSG_ID = 1;

    public View getCurrentView() {
        return currentView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        currentView = inflater.inflate(R.layout.fragment_menu,
                container, false);
        btn_sync = (Button) currentView.findViewById(R.id.btn_sync);
        iv_login = (ImageView) currentView.findViewById(R.id.iv_login);
        TextView tv = (TextView)currentView.findViewById(R.id.menu_username);
        tv.setText(user_name);
        btn_sync.setOnClickListener(this);
        iv_login.setOnClickListener(this);
        return currentView;
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        FragmentTransaction ft = getFragmentManager().beginTransaction();// 开始一个事物
        switch (v.getId()) {
            case R.id.btn_sync:
//                new AsyncSqlserver().execute();
                doAsyncThread();
                break;
            case R.id.btn_exit:
                break;
            default:
//                android.app.Fragment homeFragment = new ContentFragment();
//                ft.replace(R.id.slidingpane_content, homeFragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                ft.commit();
        }
        ((CatalogMapView) getActivity()).getSlidingPaneLayout().closePane();
    }
    public void SetUserName(String name)
    {
        user_name = name;
    }

    private JSONObject doAsync()
    {
        JSONObject jsonObject = null;
        try
        {
            ZkDbHelper dbHelper = new ZkDbHelper(getActivity());
            SQLiteDatabase dber = dbHelper.getWritableDatabase();
            if(dber == null) return jsonObject;

            if(!dber.isOpen())return jsonObject;
            Cursor dataSet = dber.query(ZkDbContract.ZKBasicInfo.TABLE_NAME,null,null,null,null,null,null);

            while (dataSet.moveToNext())
            {
                jsonObject = new JSONObject();
                int index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.LONGITUDE_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.LONGITUDE_COLUMN, dataSet.getDouble(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.LATITUDE_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.LATITUDE_COLUMN,dataSet.getDouble(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.CODE_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.CODE_COLUMN,dataSet.getString(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.TYPE_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.TYPE_COLUMN,dataSet.getInt(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.COORDX_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.COORDX_COLUMN,dataSet.getDouble(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.COORDY_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.COORDY_COLUMN,dataSet.getDouble(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.COORDZ_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.COORDZ_COLUMN,dataSet.getDouble(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.DEPTH_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.DEPTH_COLUMN,dataSet.getDouble(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.DIRECTION_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.DIRECTION_COLUMN,dataSet.getDouble(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.DEGREE_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.DEGREE_COLUMN,dataSet.getDouble(index));

                index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.COVERDEPTH_COLUMN);
                jsonObject.put(ZkDbContract.ZKBasicInfo.COVERDEPTH_COLUMN,dataSet.getDouble(index));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
        }
        return jsonObject;
    }

    private void doAsyncThread()
    {
        Thread asyncThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ZkDbHelper dbHelper = new ZkDbHelper(getActivity());
                SQLiteDatabase dber = dbHelper.getWritableDatabase();
                if(dber == null) return;

                if(!dber.isOpen())return;
                Cursor dataSet = dber.query(ZkDbContract.ZKBasicInfo.TABLE_NAME,null,null,null,null,null,null);

                JSONObject jsonObject ;
                try
                {
                    while (dataSet.moveToNext())
                    {
                        jsonObject = new JSONObject();
                        int index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.LONGITUDE_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.LONGITUDE_COLUMN, dataSet.getDouble(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.LATITUDE_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.LATITUDE_COLUMN,dataSet.getDouble(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.CODE_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.CODE_COLUMN,dataSet.getString(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.TYPE_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.TYPE_COLUMN,dataSet.getInt(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.COORDX_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.COORDX_COLUMN,dataSet.getDouble(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.COORDY_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.COORDY_COLUMN,dataSet.getDouble(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.COORDZ_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.COORDZ_COLUMN,dataSet.getDouble(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.DEPTH_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.DEPTH_COLUMN,dataSet.getDouble(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.DIRECTION_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.DIRECTION_COLUMN,dataSet.getDouble(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.DEGREE_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.DEGREE_COLUMN,dataSet.getDouble(index));

                        index = dataSet.getColumnIndex(ZkDbContract.ZKBasicInfo.COVERDEPTH_COLUMN);
                        jsonObject.put(ZkDbContract.ZKBasicInfo.COVERDEPTH_COLUMN,dataSet.getDouble(index));
                        WebserviceSoaper.CallWebServiceUsedJSon(WebserviceConstant.Namespace,
                                WebserviceConstant.Endpoint,WebserviceConstant.InsertMethod,jsonObject.toString());

                    }
                }
                catch (JSONException e)
                {

                }

                Message msg = new Message();
                msg.arg1 = MSG_ID;
                msg.obj = null;
                handler.sendMessage(msg);
            }
        });

        asyncThread.start();

    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1)
            {
                case MSG_ID:
                    Toast.makeText(MenuFragment.this.getActivity(),"同步成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private class  AsyncSqlserver extends AsyncTask<String,Void,String >
    {
        @Override
        protected String doInBackground(String... strings) {
            JSONObject jsonObject = doAsync();
            try
            {
                return WebserviceSoaper.CallWebServiceUsedJSon(WebserviceConstant.Namespace,
                        WebserviceConstant.Endpoint,WebserviceConstant.InsertMethod,jsonObject.toString());
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
