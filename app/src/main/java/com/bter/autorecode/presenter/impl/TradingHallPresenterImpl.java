package com.bter.autorecode.presenter.impl;

import com.bter.autorecode.entity.TradingHall;
import com.bter.autorecode.model.TradingHallModel;
import com.bter.autorecode.model.impl.TradingHallModelImpl;
import com.bter.autorecode.presenter.TradingHallPresenter;
import com.bter.autorecode.view.TradingHallView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by taofaqi on 2017/6/20.
 */

public class TradingHallPresenterImpl implements TradingHallPresenter {
    TradingHallView tradingHallView;
    TradingHallModel tradingHallModel;

    public TradingHallPresenterImpl(TradingHallView tradingHallView) {
        tradingHallModel = new TradingHallModelImpl();
        this.tradingHallView = tradingHallView;
    }

//    @Override
//    public void loadTradingHallDatas(int t) {
//        tradingHallModel.loadTradingHallDats(t, new Callback<TradingHall>() {
//            @Override
//            public void onResponse(Call<TradingHall> call, Response<TradingHall> response) {
//                TradingHall tradingHall = response.body();
//                tradingHallView.showTradingHall(tradingHall);
//            }
//
//            @Override
//            public void onFailure(Call<TradingHall> call, Throwable t) {
//                tradingHallView.showErrorMsg("网络链接错误");
//            }
//        });
//    }

    @Override
    public void loadTradingHallDatas(int t) {
        tradingHallModel.loadTradingHallDats(t, new Observer<TradingHall>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TradingHall tradingHall) {
                if (null != tradingHall)
                    tradingHallView.showTradingHall(tradingHall);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                tradingHallView.showErrorMsg("网络链接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
