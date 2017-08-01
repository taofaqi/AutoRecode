package com.bter.eventbus;

/**
 * Created by taofaqi on 2017/7/5.
 *
 * EventBus3.0最大的优化就是通过EventBusAnnotationProcessor在编译时生成索引，提升索引速度。
 */

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
