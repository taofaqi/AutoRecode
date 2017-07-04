package com.bter.autorecode.presenter.impl;

import com.bter.autorecode.entity.ZhiHuUser;
import com.bter.autorecode.model.ZhiHuModel;
import com.bter.autorecode.model.impl.ZhiHuModelImpl;
import com.bter.autorecode.presenter.ZhiHuPresenter;
import com.bter.autorecode.view.ZhiHuView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by taofaqi on 2017/7/4.
 */

public class ZhiHuPresenterImpl implements ZhiHuPresenter {
    ZhiHuModel zhiHuModelImpl;
    ZhiHuView zhiHuView;

    public ZhiHuPresenterImpl(ZhiHuView zhiHuView) {
        zhiHuModelImpl = new ZhiHuModelImpl();
        this.zhiHuView = zhiHuView;
    }

    @Override
    public void loadZhiHuUserDatas(String userName) {
        zhiHuModelImpl.loadZhiHuDatas(userName, new Callback<ZhiHuUser>() {
            @Override
            public void onResponse(Call<ZhiHuUser> call, Response<ZhiHuUser> response) {
                zhiHuView.loadZhiHuUserSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ZhiHuUser> call, Throwable t) {
                zhiHuView.loadZhiHuUserError();
            }
        });
    }
}
