package com.bter.autorecode.model.impl;

import com.bter.autorecode.entity.ZhiHuUser;
import com.bter.autorecode.model.ZhiHuModel;
import com.bter.autorecode.retrofitapi.RetrofitService;
import com.bter.autorecode.utils.AppConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by taofaqi on 2017/7/4.
 */

public class ZhiHuModelImpl implements ZhiHuModel {
    @Override
    public void loadZhiHuDatas(String userName, Callback<ZhiHuUser> callback) {
        RetrofitService.getRetrofit(AppConstants.BASE_URL_ZHIHU).create(ZhiHuService.class).getZhiHuDatas(userName).enqueue(callback);
    }

    private interface ZhiHuService {
        @GET(AppConstants.RequsetPath.ZHI_HU_USER_URL)
        Call<ZhiHuUser> getZhiHuDatas(@Path("user") String user);
    }
}
