package com.ywl5320.rxjavaretrofit.httpservice.subscribers;

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
                subscriberOnListener.onError("网络中断，请检查您的网络状态");
            } else if (e instanceof ConnectException) {
                subscriberOnListener.onError("网络中断，请检查您的网络状态");
            } else {
                subscriberOnListener.onError(e.getMessage());
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
