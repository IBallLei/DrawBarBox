package com.example.werewol.laganxiang.thirdparty;

import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.werewol.laganxiang.R;
import com.example.werewol.laganxiang.ui.BaiduMapActivity;

/**
 * Created by zhanglei
 * on 2017/10/12.
 */
public class BaiduMapManager {

    public static LocationClient mLocationClient = null;

    public static BDAbstractLocationListener myListener = new MyLocationListener();
    private static BitmapDescriptor bitmap;

    public static void init(Context context) {

        SDKInitializer.initialize(context);

        mLocationClient = new LocationClient(context);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        //构建Marker图标
        bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.location_icon);

        initLocation();
    }

    private static void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

//        int span = 1000;
//        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    public static void start() {
        if (mLocationClient != null) {
            mLocationClient.restart();
        }
    }

    public static LatLng myPoint;
    public static double myLatitude;
    public static double myLongitude;
    public static LatLng laganxiangPoint;
    public static double laganxiangLatitude;
    public static double laganxiangLongitude;

    public static void setMaker(MapView mapView, int pointType, double latitude, double longitude) {
        BaiduMap baiduMap = mapView.getMap();
        switch (pointType) {
            case BaiduMapActivity.POINT_TYPE_ME:
                myPoint = new LatLng(latitude, longitude);
                myLatitude = latitude;
                myLongitude = longitude;
                showPointInMap(baiduMap, myPoint);
                moveToPoint(baiduMap, myPoint);
                break;
            case BaiduMapActivity.POINT_TYPE_LAGANXIANG:
                laganxiangPoint = new LatLng(latitude, longitude);
                laganxiangLatitude = latitude;
                laganxiangLongitude = longitude;
                showPointInMap(baiduMap, myPoint);
                showPointInMap(baiduMap, laganxiangPoint);
                break;
            default:

                break;
        }

    }

    private static void showPointInMap(BaiduMap baiduMap, LatLng point) {
        //定义Maker坐标点
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .zIndex(9)  //设置marker所在层级
                .draggable(true)  //设置手势拖拽
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        baiduMap.clear();
        baiduMap.addOverlay(option);
    }

    private static void moveToPoint(BaiduMap baiduMap, LatLng point) {
        //移动到我的位置
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        baiduMap.animateMapStatus(mMapStatusUpdate);
    }
}
