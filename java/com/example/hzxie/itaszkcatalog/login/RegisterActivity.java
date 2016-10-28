package com.example.hzxie.itaszkcatalog.login;

import android.app.Activity;
import android.content.pm.ProviderInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hzxie.itaszkcatalog.R;
import com.example.hzxie.itaszkcatalog.zkdatabase.ZkDbContract;
import com.example.hzxie.itaszkcatalog.zkwebservice.WebserviceConstant;
import com.example.hzxie.itaszkcatalog.zkwebservice.WebserviceSoaper;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_register = (Button)findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view){

                JSONObject pks = null;
                try {
                    pks = package2JSon();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                finally {

                }
                new RegisterTask().execute(pks.toString());
            }
        });
    }

    private JSONObject package2JSon()throws JSONException
    {
        EditText name = (EditText)findViewById(R.id.registerName);
        EditText email = (EditText)findViewById(R.id.registerEmail);
        EditText password = (EditText)findViewById(R.id.registerPassword);


        JSONObject jsonObject = new JSONObject();

        jsonObject.put(ZkDbContract.ZkCfgUser.USERNAME_COLUMN, name.getText());
        jsonObject.put(ZkDbContract.ZkCfgUser.PASSWORDS_COLUMN, password.getText());
        jsonObject.put(ZkDbContract.ZkCfgUser.DISPLAYNAME_COLUMN, name.getText());
        jsonObject.put(ZkDbContract.ZkCfgUser.COMMENTTEXT_COLUMN, name.getText());
        jsonObject.put(ZkDbContract.ZkCfgUser.DISABLED_COLUMN, 1);
        jsonObject.put(ZkDbContract.ZkCfgUser.EMAIL_COLUMN, email.getText());

        return jsonObject;
    }
    public class RegisterTask extends AsyncTask<String , Void,String  >
    {

        @Override
        protected String doInBackground(String...strings) {
            try
            {
                return WebserviceSoaper.CallWebServiceUsedJSon(WebserviceConstant.Namespace, WebserviceConstant.Endpoint,
                        "Register",strings[0]);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            finally {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String  re) {
            if(re == null) return;
            super.onPostExecute(re);
            //内部类使用到外部类的实例时
            Toast.makeText(RegisterActivity.this,re,Toast.LENGTH_SHORT).show();
        }
    }
}
