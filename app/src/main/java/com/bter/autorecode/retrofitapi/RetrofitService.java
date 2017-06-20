package com.bter.autorecode.retrofitapi;

import com.bter.autorecode.utils.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by taofaqi on 2017/6/19.
 * <p>
 * http://www.jianshu.com/p/308f3c54abdd
 */

public class RetrofitService {

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)  //Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
