package com.bter.autorecode.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bter.autorecode.R;
import com.bter.autorecode.entity.TradingHall;
import com.bter.autorecode.presenter.TradingHallPresenter;
import com.bter.autorecode.presenter.impl.TradingHallPresenterImpl;
import com.bter.autorecode.view.TradingHallView;

public class MainActivity extends AppCompatActivity implements TradingHallView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TradingHallPresenter tradingHallPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatas();
            }
        });
    }

    private void initDatas() {
        tradingHallPresenter = new TradingHallPresenterImpl(this);
        tradingHallPresenter.loadTradingHallDatas(209226202);
    }

    @Override
    public void showTradingHall(TradingHall tradingHall) {
        Log.e("MainActivity", tradingHall.toString());
    }

    @Override
    public void showErrorMsg(String msg) {
        Log.e("MainActivity", msg);
    }
}
