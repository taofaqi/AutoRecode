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
//        这里是interface不是class，所以我们是无法直接调用该方法，我们需要用Retrofit创建一个BlogService的代理对象。
        RetrofitService.getRetrofit(AppConstants.BASE_URL).create(TradingService.class).getTradingHallDatas(t).enqueue(callback);
    }

    public interface TradingService {
        @GET(AppConstants.RequsetPath.TRADING_HALL_URL)
        Call<TradingHall> getTradingHallDatas(@Query("id") int t);

//        @GET(AppConstants.RequsetPath.TRADING_HALL_URL?{id})
//        Call<TradingHall> getTradingHallDatas(@Path("id") int id);
    }
}
