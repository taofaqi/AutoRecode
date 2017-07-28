package com.bter.autorecode.model;

import com.bter.autorecode.entity.TradingHall;

import io.reactivex.Observer;
import retrofit2.Callback;

/**
 * Created by taofaqi on 2017/6/20.
 */

public interface TradingHallModel {
    //    void loadTradingHallDats(int t, Callback<TradingHall> callback);
    void loadTradingHallDats(int t, Observer<TradingHall> observer);
}
