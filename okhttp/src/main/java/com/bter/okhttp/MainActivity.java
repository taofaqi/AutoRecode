package com.bter.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView mImg;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImg = ((ImageView) findViewById(R.id.main_img));
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MainActivity", "文件开始下载");
                request = new Request.Builder().url("http://photocdn.sohu.com/20150424/mp12227252_1429864903423_2_th_fv23.jpeg").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        InputStream inputStream = response.body().byteStream();
                        byte[] imgByte = new byte[0];
                        try {
                            imgByte = response.body().bytes();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        final byte[] finalImgByte = imgByte;

                        /**
                         * 需要在子线程中更新UI
                         * */
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(finalImgByte, 0, finalImgByte.length);
                                mImg.setImageBitmap(bitmap);
                            }
                        });

                        FileOutputStream outputStream = new FileOutputStream(new File("/sdcard/logo.png"));
                        byte[] bytes = new byte[20];
                        int len = 0;
                        while ((len = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, len);
                        }
                        outputStream.flush();

                        Log.e("MainActivity", "文件下载成功");
                    }
                });
            }
        });
    }
}