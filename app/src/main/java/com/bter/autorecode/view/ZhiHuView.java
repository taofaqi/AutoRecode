package com.bter.autorecode.view;

import com.bter.autorecode.entity.ZhiHuUser;

/**
 * Created by taofaqi on 2017/7/4.
 */

public interface ZhiHuView {
    void loadZhiHuUserSuccess(ZhiHuUser zhiHuUser);
    void loadZhiHuUserError();
}
