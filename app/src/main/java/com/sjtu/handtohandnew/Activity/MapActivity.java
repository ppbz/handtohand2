package com.sjtu.handtohandnew.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.sjtu.handtohandnew.Base.BaseActivity;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.R;

public class MapActivity extends BaseActivity implements BaseInterface, BaiduMap.OnMapStatusChangeListener, OnGetGeoCoderResultListener {

    private BaiduMap mBaiduMap;
    private MapView mMapView;
    private Context mContext;
    private LocationClient mlocationClient;

    /**
     * 当前经纬度
     */
    private LatLng mLoactionLatLng;

    /**
     * 显示的地图
     */
    protected MapView bmapView;
    /**
     * 附近地点列表
     */
    private ListView lv_location_position;

    /* *//**
     * 列表数据
     *//*
    private List<PoiInfo> datas;*/
    /**
     * 地理编码
     */
    private GeoCoder mSearch;
    /**
     * 定位
     */
    private LocationClient mLocClient;
    private MyLocationListener02 myLocationListener = new MyLocationListener02();
    // MapView 中央对于的屏幕坐标
    private android.graphics.Point mCenterPoint = null;

    /**
     * 是否第一次定位
     */
    private boolean isFirstLoc = true;
    /**
     * 进度条
     */
    private ProgressBar pb_location_load_bar;
    /**
     * 获取的位置
     */
    private String mLocationValue;
    /**
     * 按钮：回到原地
     */
    private ImageView img_location_back_origin;
    /**
     * 请求码
     */
    private final static int REQUEST_CODE = 0x123;
    private boolean isTouch = true;
    private LatLng target;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_test);
        mMapView = findViewById(R.id.bMapView01);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        Log.d("wqeqd","-----------------------------------------");

        InitView();
        InitData();
        InitViewOper();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {


        super.onDestroy();
        mMapView.onDestroy();

    }

    @Override
    public void InitView() {
        mContext = this;
        // 设置为普通矢量图地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mMapView.setPadding(10, 0, 0, 10);
        mMapView.showZoomControls(false);

        /*// 设置缩放比例(500米)
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
        mBaiduMap.setOnMapTouchListener(touchListener);
*/
        // 初始化当前 MapView 中心屏幕坐标
        mCenterPoint = mBaiduMap.getMapStatus().targetScreen;
        target = mBaiduMap.getMapStatus().target;

        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);

        // 地图状态监听
        mBaiduMap.setOnMapStatusChangeListener(this);
        // 定位初始化
        mLocClient = new LocationClient(this);
        Log.d("sadas","BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        mLocClient.registerLocationListener(myLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(5000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        // 可定位
        mBaiduMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void InitData() {

    }

    @Override
    public void InitViewOper() {

    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {

    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }

    public class MyLocationListener02 implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            Log.d("aa","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            Double mLatitude = location.getLatitude();
            Double mLongitude = location.getLongitude();

            LatLng currentLatLng = new LatLng(mLatitude, mLongitude);
            mLoactionLatLng = new LatLng(mLatitude, mLongitude);

            // 是否第一次定位
            if (isFirstLoc) {
                isFirstLoc = false;
                // 实现动画跳转
                MapStatusUpdate u = MapStatusUpdateFactory
                        .newLatLng(currentLatLng);
                mBaiduMap.animateMapStatus(u);

                mSearch.reverseGeoCode((new ReverseGeoCodeOption())
                        .location(currentLatLng));
                return;
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
