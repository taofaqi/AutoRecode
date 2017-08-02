package com.bter.radarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RadarView radarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radarView = ((RadarView) findViewById(R.id.radarView));
    }

    public void start(View view) {
        radarView.startScan();
    }

    public void stop(View view) {
        radarView.stopScan();
    }
}
