package com.ywl5320.rxjavaretrofit.httpservice.serviceapi;


import com.ywl5320.rxjavaretrofit.httpservice.beans.UserBean;
import com.ywl5320.rxjavaretrofit.httpservice.beans.WeatherBean;
import com.ywl5320.rxjavaretrofit.httpservice.service.BaseApi;
import com.ywl5320.rxjavaretrofit.httpservice.service.HttpMethod;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ywl on 2016/5/19.
 */
public class UserApi extends BaseApi {

    public static UserApi userApi;
    public UserService userService;
    public UserApi()
    {
        userService = HttpMethod.getInstance().createApi(UserService.class);
    }

    public static UserApi getInstance()
    {
        if(userApi == null)
        {
            userApi = new UserApi();
        }
        return userApi;
    }
    /*-------------------------------------获取的方法-------------------------------------*/

    public void getWeather(String loaction, String output, String ak, Subscriber<WeatherBean> subscriber)
    {
        Observable observable = userService.getWeather(loaction, output, ak)
                .map(new HttpResultFunc<WeatherBean>());

        toSubscribe(observable, subscriber);
    }

}
