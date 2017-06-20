package com.bter.autorecode.view;

import com.bter.autorecode.entity.TradingHall;

/**
 * Created by taofaqi on 2017/6/20.
 */

public interface TradingHallView {
    void showTradingHall(TradingHall tradingHall);

    void showErrorMsg(String msg);
}
