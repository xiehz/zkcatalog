package com.example.hzxie.itaszkcatalog;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.hzxie.itaszkcatalog.zkcatalog.ZkCatalogBasicinfoActivity;

public class CatalogMapView extends Activity {

    private SlidingPaneLayout mSlidingPanelLayout;
    private MenuFragment menuFragment;
    private ContentFragment contentFragment;

    private  SearchPresentation mSearcher;

    public SlidingPaneLayout getSlidingPaneLayout() {
        return mSlidingPanelLayout;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);// 不显示程序的标题栏
        setContentView(R.layout.activity_catalog_map_view1);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.layout_common_head);

        String[] message = this.getIntent().getExtras().getStringArray("USER_INFO");
        assert message.length ==2;
        menuFragment = new MenuFragment();
        menuFragment.SetUserName(message[0]);
        contentFragment = new ContentFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.slidingpane_menu, menuFragment);
        transaction.replace(R.id.slidingpane_content, contentFragment);

        transaction.commit();
        mSlidingPanelLayout = (SlidingPaneLayout)findViewById(R.id.slidingpanellayout);
        mSlidingPanelLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });

        mSearcher = new SearchPresentation(this);
        mSearcher.init();
        initUser();
    }

    private void initUser()
    {
        ImageView user = (ImageView)findViewById(R.id.iv_head_left);
        user.setOnClickListener( new ImageView.OnClickListener(){
            @Override
            public void onClick(View view) {
                final SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout)
                        findViewById(R.id.slidingpanellayout);
                if (slidingPaneLayout.isOpen()) {
                    slidingPaneLayout.closePane();
                } else {
                    slidingPaneLayout.openPane();
                }

            }
    });
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
