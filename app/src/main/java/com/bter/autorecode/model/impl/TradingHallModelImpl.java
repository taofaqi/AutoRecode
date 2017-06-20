package com.bter.autorecode.model.impl;

import com.bter.autorecode.entity.TradingHall;
import com.bter.autorecode.model.TradingHallModel;
import com.bter.autorecode.retrofitapi.RetrofitService;
import com.bter.autorecode.utils.AppConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by taofaqi on 2017/6/20.
 */

public class TradingHallModelImpl implements TradingHallModel {
    @Override
    public void loadTradingHallDats(int t, Callback<TradingHall> callback) {
        RetrofitService.getRetrofit().create(TradingService.class).getTradingHallDatas(t).equals(callback);
    }

    public interface TradingService {
        @GET(AppConstants.RequsetPath.TRADING_HALL_URL)
        Call<TradingHall> getTradingHallDatas(@Query("id") int t);
    }
}
