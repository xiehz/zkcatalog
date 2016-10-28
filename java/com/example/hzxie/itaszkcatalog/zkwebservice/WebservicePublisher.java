package com.example.hzxie.itaszkcatalog.zkwebservice;

import android.os.AsyncTask;


/**
 * Created by hzxie on 2016/10/12.
 * 访问webservice
 * Connect
 * Post
 * Get
 * UpdateNetwork
 * State
 */
public class WebservicePublisher implements IServicePublisher{

    private IServiceObserver mObserver;
    public WebservicePublisher(String account , String password)
    {
    }

    @Override
    public void RegisterObserver(IServiceObserver observer) {
        mObserver = observer;
    }

    @Override
    public void DisAttach() {
        //释放资源
        mObserver = null;
    }

    public void CallIsConnected()
    {
       new IsConnectedTask().execute();
    }

    private class IsConnectedTask extends  AsyncTask<Void,Void,Boolean>
    {
        @Override
        protected Boolean doInBackground(Void... voids) {
            return (Boolean) WebserviceSoaper.CallWebService(WebserviceConstant.Namespace,WebserviceConstant.Endpoint,
                    WebserviceConstant.IsConnectedMethod);
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            if(mObserver!= null)
            mObserver.OnIsConnected(b);
        }
    }
    public void CallState()
    {
        new StateTask().execute();
    }
    private class StateTask extends AsyncTask<Void,Void,Integer>
    {
        @Override
        protected Integer doInBackground(Void... voids) {
            return (Integer) WebserviceSoaper.CallWebService(WebserviceConstant.Namespace,WebserviceConstant.Endpoint,
                    WebserviceConstant.StateMethod);
        }

        @Override
        protected void onPostExecute(Integer b) {
            super.onPostExecute(b);
            if(mObserver!= null)
            mObserver.OnState(b);
        }
    }

    public void CallSelect(String sql)
    {
        new SelectTask().execute(sql);
    }
    private class SelectTask extends AsyncTask<String ,Void,String >
    {
        @Override
        protected String doInBackground(String... strings) {
            return WebserviceSoaper.CallWebService(WebserviceConstant.Namespace,WebserviceConstant.Endpoint,
                    WebserviceConstant.SelectMethod,strings).toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(mObserver!= null)
            mObserver.OnSelect(s);
        }
    }

    public void CallInsert(String sql)
    {
        try
        {
            new InsertTask().execute(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private class InsertTask extends AsyncTask<String ,Void,Boolean >
    {
        @Override
        protected Boolean doInBackground(String... strings) {
            return (Boolean) WebserviceSoaper.CallWebService(WebserviceConstant.Namespace,WebserviceConstant.Endpoint,
                    WebserviceConstant.InsertMethod,strings);
        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
            if(mObserver!= null)
            mObserver.OnInsert(s);
        }
    }

    public void CallUpdate(String sql)
    {
        new UpdateTask().execute(sql);
    }
    private class UpdateTask extends AsyncTask<String ,Void,Boolean >
    {
        @Override
        protected Boolean doInBackground(String... strings) {
            return (Boolean) WebserviceSoaper.CallWebService(WebserviceConstant.Namespace,WebserviceConstant.Endpoint,
                    WebserviceConstant.UpdateMethod,strings);
        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
            if(mObserver!= null)
            mObserver.OnUpdate(s);
        }
    }
    public void CallDelete(String sql)
    {
        new DeleteTask().execute();
    }
    private class DeleteTask extends AsyncTask<String ,Void,Boolean >
    {
        @Override
        protected Boolean doInBackground(String... strings) {
            return (Boolean) WebserviceSoaper.CallWebService(WebserviceConstant.Namespace,WebserviceConstant.Endpoint,
                    WebserviceConstant.DeleteMethod,strings);
        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
            if(mObserver!= null)
            mObserver.OnDelete(s);
        }
    }
}
