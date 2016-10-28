package com.example.hzxie.itaszkcatalog.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hzxie.itaszkcatalog.R;


public class FindPasswordActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        Button btn_findpassword = (Button)findViewById(R.id.refind_btnRegister);
        btn_findpassword.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(FindPasswordActivity.this,"Getting!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
