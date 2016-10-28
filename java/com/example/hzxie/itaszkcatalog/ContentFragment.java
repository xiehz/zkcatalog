package com.example.hzxie.itaszkcatalog;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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



public class ContentFragment extends Fragment implements
        AMapLocationListener,AMap.OnMarkerClickListener, AMap.OnMapClickListener ,
        AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter,AMap.OnMapLongClickListener{

    private View mView;
    private AMap aMap;
    private MapView mapView;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    private Marker mMarker;
    private Marker mLocationMarker;

    private Button mBtnLocation;

    private SearchResultPresentation mResult;

    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_content, container, false);

        mapView = (MapView)mView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        init();
        return mView;
    }

    /**
     * 初始化
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }


        mResult = new SearchResultPresentation(mView);
        mResult.init();

        mBtnLocation = (Button)mView.findViewById(R.id.btn_location);
        mBtnLocation.setBackgroundResource(R.mipmap.point6);
        assert(mBtnLocation != null);
        mBtnLocation.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                //点击定位按钮，重新定位
                activeLocation();
            }
        });

        Button btn_catalog = (Button)mView.findViewById(R.id.zk_catalog);
        btn_catalog.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                onStartZKCatalog(view);
            }
        });
        //初始定位
        this.activeLocation();
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.getUiSettings().setScaleControlsEnabled(true);
        aMap.setOnMarkerClickListener(this);
        aMap.setOnMapClickListener(this);
        aMap.setOnMapLongClickListener(this);

        UiSettings settings = aMap.getUiSettings();
        //地图logo显示在左下方
        settings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);

        //是否显示比例尺
        settings.setScaleControlsEnabled(true);

        //是否显示默认缩放
        settings.setZoomControlsEnabled(true);
        //是否显示指南针
        settings.setCompassEnabled(false);
        //默认的定位按钮是否显示
        settings.setMyLocationButtonEnabled(false);

        //设置地图是否可以手势滑动
        settings.setScrollGesturesEnabled(true);
        //设置地图是否可以倾斜
        settings.setTiltGesturesEnabled(true);
        //设置地图是否可以缩放
        settings.setZoomGesturesEnabled(true);
        //设置是否可以旋转
        settings.setRotateGesturesEnabled(true);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
    }

    public void onLocationChanged(AMapLocation aMapLocation)
    {
        if(aMapLocation != null && aMapLocation.getErrorCode() == 0)
        {
            LatLng ll = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            //更新位置
            float level = aMap.getCameraPosition().zoom;
            if(this.mLocationMarker == null)
            {
                this.mLocationMarker = aMap.addMarker(new MarkerOptions()
                        .anchor(0.5f, 0.5f)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker))
                        .position(ll));
                level = 15;
            }
            this.mLocationMarker.setPosition(ll);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll,level));

        }
        else
        {
            Toast.makeText(getActivity(),"定位失败："+ aMapLocation.getErrorInfo(),Toast.LENGTH_SHORT).show();
        }
    }
    public void activeLocation()
    {
        if(mlocationClient == null)
        {
            mlocationClient = new AMapLocationClient(getActivity());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //单次定位
            mLocationOption.setOnceLocation(true);
            //获取最近3s内精度最高的一次定位结果
            mLocationOption.setOnceLocationLatest(true);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
        }
        mlocationClient.startLocation();
    }

    public void deactivate() {
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        this.mMarker = marker;
        mResult.showResult(marker);
        return false;
    }

    public AMap GetAMap()
    {
        return this.aMap;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(mResult != null)
            mResult.hideResult();
        mMarker = null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    public void onStartZKCatalog(View view)
    {
        Intent intent = new Intent(getActivity(), ZkCatalogActivity.class);

        String[] messages = new String[]{
                String.valueOf(mMarker.getPosition().longitude),
                String.valueOf(mMarker.getPosition().latitude)
        };
        intent.putExtra(ZkCatalogActivity.LOCATION_MESSAGE,messages);
        this.startActivity(intent);
//        Intent intent1 = new Intent(this, ZkCatalogBasicinfoActivity.class);
//        intent1.putExtra(ZkCatalogActivity.LOCATION_MESSAGE,messages);
//        this.startActivity(intent1);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        this.mResult.hideResult();
        this.mMarker =  aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(latLng));
        this.mResult.showResult(mMarker);
    }
}
