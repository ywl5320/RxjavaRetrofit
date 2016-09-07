package com.ywl5320.rxjavaretrofit.httpservice.subscribers;

import com.ywl5320.rxjavaretrofit.httpservice.service.ExceptionApi;
import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by ywl on 2016/5/19.
 */
public class HttpSubscriber<T> extends Subscriber<T>{

    private SubscriberOnListener subscriberOnListener;
	private Context context;

    public HttpSubscriber(SubscriberOnListener subscriberOnListener, Context context)
    {
        this.subscriberOnListener = subscriberOnListener;
		this.context = context;

    }



    public void onUnsubscribe() {
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
        if(subscriberOnListener != null && context != null)
        {
            //subscriberOnListener.onError("完成", 1);
        }
		else
		{
			onUnsubscribe();
		}
    }

    @Override
    public void onError(Throwable e) {
        if(subscriberOnListener != null && context != null)
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
		else
		{
			onUnsubscribe();
		}
    }

    @Override
    public void onNext(T t) {
        if(subscriberOnListener != null && context != null)
        {
            subscriberOnListener.onSucceed(t);
        }
		else
		{
			onUnsubscribe();
		}
    }
}
