package com.ywl5320.rxjavaretrofit.httpservice.subscribers;

import com.ywl5320.rxjavaretrofit.httpservice.service.ExceptionApi;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by ywl on 2016/5/19.
 */
public class HttpSubscriber<T> extends Subscriber<T>{

    private SubscriberOnListener subscriberOnListener;

    public HttpSubscriber(SubscriberOnListener subscriberOnListener)
    {
        this.subscriberOnListener = subscriberOnListener;

    }



    public void onConnected() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    /**
     * 访问网络开始前（可以处理缓存）
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
        if(subscriberOnListener != null)
        {
            //subscriberOnListener.onError("完成", 1);
        }
    }

    @Override
    public void onError(Throwable e) {
        if(subscriberOnListener != null)
        {
            if (e instanceof SocketTimeoutException) {
                subscriberOnListener.onError(-1001, "网络超时，请检查您的网络状态");
            } else if (e instanceof ConnectException) {
                subscriberOnListener.onError(-1002, "网络链接中断，请检查您的网络状态");
            } else if(e instanceof ExceptionApi){
                subscriberOnListener.onError(((ExceptionApi)e).getCode(), ((ExceptionApi)e).getMsg());
            }
            else
            {
                subscriberOnListener.onError(-1003, "未知错误:" + e.getMessage());
            }
        }
    }

    @Override
    public void onNext(T t) {
        if(subscriberOnListener != null)
        {
            subscriberOnListener.onSucceed(t);
        }
    }
}
