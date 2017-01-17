package com.rm.easy.ro.roastorder.iface;

/**
 * Created by Easy.D on 2016/11/6.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
