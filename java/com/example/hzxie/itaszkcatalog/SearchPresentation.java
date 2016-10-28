package com.example.hzxie.itaszkcatalog;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hzxie on 2016/9/14.
 */
public class SearchPresentation implements View.OnClickListener {

    private Activity mainview;

    private String keyWord = "";
    private EditText mSearchText;

    public  SearchPresentation(Activity mapview)
    {
        mainview = mapview;
    }

    public void init() {
        if(mainview == null)return;
        TextView searchButton = (TextView)mainview.findViewById(R.id.btn_search);
        searchButton.setOnClickListener(this);

//        this.setup();
    }
    private void setup() {
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(mainview, "搜索中...", Toast.LENGTH_LONG).show();
    }
}
