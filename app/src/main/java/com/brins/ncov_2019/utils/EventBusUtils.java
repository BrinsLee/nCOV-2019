package com.brins.ncov_2019.utils;


import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {
    /**
     * 注册
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }


    /**
     * 解绑
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送事件
     */
    public static void sendEnvent(EventMessage eventMessage) {
        EventBus.getDefault().post(eventMessage);
    }

    /**
     * 发送粘性事件
     */
    public static void sendStickyEnvent(EventMessage eventMessage) {
        EventBus.getDefault().postSticky(eventMessage);
    }
}
