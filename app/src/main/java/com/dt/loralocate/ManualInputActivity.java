package com.dt.loralocate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import com.dt.loralocate.Utils.BSLocation;
import com.dt.loralocate.Utils.CoordinateConversion;
import com.dt.loralocate.Utils.RSSI;

public class ManualInputActivity extends AppCompatActivity implements View.OnClickListener {
    private MapView mapView=null;
    private BaiduMap baiduMap=null;

    private Button btn_submit;

    private EditText distanceView1;
    private EditText distanceView2;
    private EditText distanceView3;

    private RSSI rssi;

    private CoordinateConversion conversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);

        //Initialise the BaiduMap SDK
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();
        BaiduMap baiduMap=mapView.getMap();
        LatLng GEO_DLUT = new LatLng(39.092355,121.824447);

        MapStatusUpdate statusUpdate= MapStatusUpdateFactory.newLatLng(GEO_DLUT);
        baiduMap.setMapStatus(statusUpdate);
        MapStatus.Builder builder=new MapStatus.Builder();
        builder.zoom(19);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        //Bind Items in activity
        btn_submit=findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        distanceView1=findViewById(R.id.edit_text_1);
        distanceView2=findViewById(R.id.edit_text_2);
        distanceView3=findViewById(R.id.edit_text_3);

        //Init the Coordinate
        conversion=new CoordinateConversion(39.092355,121.824447);

        //Input the location of base stations to the RSSI algorithm
        rssi=new RSSI(conversion.LLtoMeter(BSLocation.getLocation1()),
                conversion.LLtoMeter(BSLocation.getLocation2()),
                conversion.LLtoMeter(BSLocation.getLocation3()));

        //将三个基站和坐标原点设置在地图上
//        addPinToMap(0,0);
//        addPinToMap(-74.500600, 39.363003);
//        addPinToMap(33.914452, 34.248037);
//        addPinToMap(-13.565781, -39.251809);

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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_submit:
                try {
                    double d1,d2,d3;
                    d1=Double.parseDouble(distanceView1.getText().toString());
                    d2=Double.parseDouble(distanceView2.getText().toString());
                    d3=Double.parseDouble(distanceView3.getText().toString());
                    rssi.setRadius(d1,d2,d3);

                    double[] location=rssi.get_circle_intersection();
                    this.addPinToMap(location[0],location[1]);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void addPinToMap(double x,double y){
            double[] latAndLng=conversion.MeterToLL(x,y);
            Log.d("MYLOCATION",String.format("%f, %f",x,y));
            LatLng point = new LatLng(latAndLng[0],latAndLng[1]);
            BitmapDescriptor bitmap= BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_mark_a);
            OverlayOptions options=new MarkerOptions().position(point).icon(bitmap);
            baiduMap.addOverlay(options);
    }
}