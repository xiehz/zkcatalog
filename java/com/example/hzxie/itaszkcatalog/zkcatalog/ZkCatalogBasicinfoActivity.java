package com.example.hzxie.itaszkcatalog.zkcatalog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.hzxie.itaszkcatalog.R;

import java.util.ArrayList;

public class ZkCatalogBasicinfoActivity extends Activity {

    ListView mListview;
    public  final static String LOCATION_MESSAGE = "com.example.hzxie.itascatalog.locationmessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.hzxie.itaszkcatalog.R.layout.activity_zk_catalog_basicinfo);

        String[] messages = this.getIntent().getStringArrayExtra(LOCATION_MESSAGE);

        ArrayList<ZkCatalogBasicInfoAdapter.ViewInfo> viewInfos = new
                ArrayList<ZkCatalogBasicInfoAdapter.ViewInfo>();
        ZkCatalogBasicInfoAdapter adapter = new ZkCatalogBasicInfoAdapter(this,viewInfos);

        ZkCatalogBasicInfoAdapter.ViewInfo viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "经度";
        viewInfo.entriesOrhints = new String[]{messages[0]};
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "纬度";
        viewInfo.entriesOrhints = new String[]{messages[1]};
        viewInfos.add(viewInfo);


        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "编号";
        viewInfo.entriesOrhints = new String[]{""};
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "类型";
        viewInfo.entriesOrhints = this.getResources().getStringArray(R.array.zk_type);
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "孔口坐标X";
        viewInfo.entriesOrhints = new String[]{""};
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "孔口坐标Y";
        viewInfo.entriesOrhints = new String[]{""};
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "高度";
        viewInfo.entriesOrhints = new String[]{""};
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "孔深";
        viewInfo.entriesOrhints = new String[]{""};
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "方位";
        viewInfo.entriesOrhints = new String[]{""};
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "角度";
        viewInfo.entriesOrhints = new String[]{""};
        viewInfos.add(viewInfo);

        viewInfo = adapter.new ViewInfo();
        viewInfo.id = R.mipmap.point6;
        viewInfo.label = "覆盖层厚度";
        viewInfo.entriesOrhints = new String[]{""};
        viewInfos.add(viewInfo);

        mListview = (ListView)findViewById(R.id.zk_catalog_listview);
        mListview.setAdapter(adapter);

        Button okbtn = (Button)findViewById(R.id.btn_catalog_ok);
        okbtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                ListAdapter listAdapter = mListview.getAdapter();
                for(int i = 0; i < listAdapter.getCount(); ++i)
                {
                    View view1 = listAdapter.getView(i,null,null);

                }

            }
        });
    }
}
