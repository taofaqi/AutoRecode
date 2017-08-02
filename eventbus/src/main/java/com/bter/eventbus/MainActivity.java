package com.bter.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_txt)
    TextView mainTxt;
    @BindView(R.id.main_btn)
    Button mainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainTxt.setText("EventDemo");
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*
        * Activity及Fragment一般在对应生命周期函数内采用对应方法，
        * View中推荐在onAttachedToWindow()和onDetachedFromWindow()对应使用注册及注销方法(具体实现结合业务实现调整)。
        * */
        EventBus.getDefault().unregister(this);
    }

    /*
      1. 方法必须加上{org.greenrobot.eventbus.Subscribe}注解
         方法必须为公共方法(public),无返回值（void）
         只有一个参数(发送的事件)
      2.
        ThreadMode提供四种线程模式：POSTING,MAIN,BACKGROUND,ASYNC，用以支持发送事件和处理接收事件线程独立。
        POSTING：在发送线程上处理接收事件，以保证最小开销。使用过程中应避免在主线程发送事件以造成线程阻塞。
        MAIN：在Android主线程(UI线程)处理接收事件。由于在主线程处理事件，所以在使用过程中应避免执行耗时操作以造成线程阻塞。
        BACKGROUND：后台线程上处理接收事件，若在非主线程发送事件，则直接在当前线程处理接收。EventBus采用唯一的后台线程，确保所有发送事件按顺序交付。
        ASYNC：单独线程上处理接收事件，与后台线程及主线程独立。EventBus通过线程池有效的限制线程数量，并高效复用已执行完毕异步任务。

    */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        mainTxt.setText(messageEvent.getMessage() + 2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoEvent(MessageEvent messageEvent) {
        mainTxt.setText(messageEvent.getMessage() + 1);
    }
}
