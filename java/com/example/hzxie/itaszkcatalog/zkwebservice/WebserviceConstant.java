package com.example.hzxie.itaszkcatalog.zkwebservice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzxie on 2016/10/13.
 */
public class WebserviceConstant {
    public final static String Namespace = "http://com.hzxie.ZKWebservice.org/";
    public final static String Endpoint = "http://192.168.1.6/zkws/ZkDbService.asmx";

    //method
    public final static String  IsConnectedMethod = "IsConnected";
    public final static String  StateMethod = "State";
    public final static String  StateStringMethod = "StateString";
    public final static String  SelectMethod = "ExcuteSelectSql";
    public final static String  InsertMethod = "ExcuteInsertSql";
    public final static String  UpdateMethod = "ExcuteUpdateSql";
    public final static String  DeleteMethod = "ExcuteDeleteSql";

    public static List<String> GetParameters(String method)
    {
        if(method == IsConnectedMethod || method == StateMethod || method == StateStringMethod)
        {
            return new ArrayList<String>(0);
        }
        else
        {
            ArrayList<String> params = new ArrayList<String>(1);
            params.add("sql");
            return params;
        }
    }

}
