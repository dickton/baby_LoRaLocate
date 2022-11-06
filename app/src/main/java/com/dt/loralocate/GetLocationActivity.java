package com.dt.loralocate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

public class GetLocationActivity extends AppCompatActivity {

    private MapView mapView=null;
    private BaiduMap baiduMap=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();
        LatLng GEO_DLUT = new LatLng(39.090874,121.822957);
        MapStatusUpdate statusUpdate= MapStatusUpdateFactory.newLatLng(GEO_DLUT);
        baiduMap.setMapStatus(statusUpdate);
        MapStatus.Builder builder=new MapStatus.Builder();
        builder.zoom(18);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        generateSamplePoint();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    private void generateSamplePoint(){
        LatLng point = new LatLng(39.092891,121.82452);
        BitmapDescriptor bitmap= BitmapDescriptorFactory
                .fromResource(R.drawable.icon_mark_a);
        OverlayOptions options=new MarkerOptions().position(point).icon(bitmap);
        baiduMap.addOverlay(options);
    }
}