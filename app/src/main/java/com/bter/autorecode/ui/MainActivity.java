package com.bter.autorecode.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bter.autorecode.R;
import com.bter.autorecode.entity.TradingHall;
import com.bter.autorecode.entity.ZhiHuUser;
import com.bter.autorecode.presenter.TradingHallPresenter;
import com.bter.autorecode.presenter.ZhiHuPresenter;
import com.bter.autorecode.presenter.impl.TradingHallPresenterImpl;
import com.bter.autorecode.presenter.impl.ZhiHuPresenterImpl;
import com.bter.autorecode.view.TradingHallView;
import com.bter.autorecode.view.ZhiHuView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements TradingHallView, ZhiHuView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TradingHallPresenter tradingHallPresenter;
    private ZhiHuPresenter zhiHuPresenter;

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
        zhiHuPresenter = new ZhiHuPresenterImpl(this);
        zhiHuPresenter.loadZhiHuUserDatas("qinchao");
    }

    @Override
    public void showTradingHall(TradingHall tradingHall) {
        Log.e("MainActivity", tradingHall.toString());
    }

    @Override
    public void showErrorMsg(String msg) {
        Log.e("MainActivity", msg);
    }

    @Override
    public void loadZhiHuUserSuccess(ZhiHuUser zhiHuUser) {
        Log.e("MainActivity", zhiHuUser.toString());
    }

    @Override
    public void loadZhiHuUserError() {

    }
}
