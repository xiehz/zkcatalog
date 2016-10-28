package com.example.hzxie.itaszkcatalog.zkwebservice;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by hzxie on 2016/10/13.
 */
public class WebserviceSoaper {

    public static Object CallWebService(String namespace,String endpoint, String method,String... strings)
    {
        List<String > params = WebserviceConstant.GetParameters(method);

        SoapObject soap = CreateSoapObject(namespace, method,params,strings);
        String  action = CreateSoapAction(namespace,method);

        return CallResult(endpoint,soap,action);
    }

    public static String  CallWebServiceUsedJSon(String namespace, String endpoint, String method,String jsonstr)throws JSONException
    {
        SoapObject soapObject = new SoapObject(namespace, method);
        soapObject.addProperty("json",jsonstr);//参数名称和值
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE transit = new HttpTransportSE(endpoint);

        String action = namespace + method;
        try
        {
            transit.call(action, envelope);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }

        //获取返回JSON格式的数据
        SoapObject object = (SoapObject)envelope.bodyIn;
        String jsonResult = object.getProperty(0).toString();
        JSONObject result = new JSONObject(jsonResult);
        return result.getString("result");
    }
    public static Object CallWebServiceUsedJSon(String namespace,String endpoint, String method,JSONObject values)throws JSONException
    {
        SoapObject soapObject = new SoapObject(namespace, method);
        JSONObject json = new JSONObject();
        json.put("name","milk");
        json.put("age","30");
        soapObject.addProperty("json",json.toString());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE transit = new HttpTransportSE(endpoint);

        String action = namespace + method;
        try
        {
            transit.call(action, envelope);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }

        //获取返回的数据
        SoapObject object = (SoapObject)envelope.bodyIn;
        return object.getProperty(0);

    }
    private static String CreateSoapAction(String namespace, String method)
    {
        return namespace + method;
    }
    private static SoapObject CreateSoapObject(String namespace,String method, List<String > params, String... values)
    {
        SoapObject soap = new SoapObject(namespace, method);
        if(params != null && values != null)
        {
            assert(params.size() == values.length);
            for (int i = 0; i < params.size(); ++i)
            {
                soap.addProperty(params.get(i), values[i]);
            }
        }
        return new SoapObject(namespace,method);
    }
    private static Object CallResult(String endpoint,SoapObject soap, String action)
    {
        //生成调用Webservice服务的Soap信息， 并指定soap版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

        envelope.bodyOut = soap;
        //设置是否是调用.Net开发的webservice
        envelope.dotNet = true;

        envelope.setOutputSoapObject(soap);

        HttpTransportSE transportSE = new HttpTransportSE(endpoint);

        try
        {
            transportSE.call(action, envelope);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }

        //获取返回的数据
        SoapObject object = (SoapObject)envelope.bodyIn;
        return object.getProperty(0);
    }
}
