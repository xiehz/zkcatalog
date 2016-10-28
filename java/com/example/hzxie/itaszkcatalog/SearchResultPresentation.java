package com.example.hzxie.itaszkcatalog;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.model.Marker;

/**
 * Created by hzxie on 2016/9/18.
 */
public class SearchResultPresentation {

    private View mainview;
    private RelativeLayout mPoiDetail;
    private TextView  mPoiAddress;

    public SearchResultPresentation(View mapView)
    {
        mainview = mapView;
    }
    public void init()
    {
        if(mainview == null) return ;
        mPoiDetail = (RelativeLayout)mainview.findViewById(R.id.resultview);
        mPoiAddress = (TextView)mainview.findViewById(R.id.poi_address);
    }
    public void update(Marker marker)
    {
        mPoiAddress.setText(marker.getPosition().toString());
    }
    public  void showResult(Marker marker)
    {
        if(mPoiDetail.getVisibility()!= View.VISIBLE)
            mPoiDetail.setVisibility(View.VISIBLE);

        this.update(marker);
    }
    public void hideResult()
    {
        if(mPoiDetail!= null)
            mPoiDetail.setVisibility(View.GONE);
    }
}
