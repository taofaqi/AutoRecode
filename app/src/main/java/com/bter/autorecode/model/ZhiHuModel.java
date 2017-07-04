package com.bter.autorecode.model;

import com.bter.autorecode.entity.ZhiHuUser;

import retrofit2.Callback;

/**
 * Created by taofaqi on 2017/7/4.
 */

public interface ZhiHuModel {
    void loadZhiHuDatas(String userName, Callback<ZhiHuUser> callback);
}
