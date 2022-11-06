package com.dt.loralocate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGetLocation;
    private Button btnManualInput;
    private Button btnDevInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetLocation=findViewById(R.id.main_btn_get_location);
        btnGetLocation.setOnClickListener(this);

        btnManualInput=findViewById(R.id.main_btn_manual_input);
        btnManualInput.setOnClickListener(this);

        btnDevInfo=findViewById(R.id.main_btn_dev_info);
        btnDevInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_btn_get_location:
                startActivity(new Intent(MainActivity.this,GetLocationActivity.class));
                break;
            case R.id.main_btn_manual_input:
                startActivity(new Intent(MainActivity.this,ManualInputActivity.class));
                break;
            case R.id.main_btn_dev_info:
                startActivity(new Intent(MainActivity.this,DevInfoActivity.class));
                break;
        }
    }
}