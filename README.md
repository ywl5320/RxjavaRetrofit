# RxjavaRetrofit
### Rxjava + Retrofit网络访问<br/>
#### 此Demo是根据 <a href="http://gank.io/post/56e80c2c677659311bed9841">RxJava 与 Retrofit 结合的最佳实践</a> 进行的封装，已在项目中使用。

实例图片：<br/>
![image](https://github.com/wanliyang1990/RxjavaRetrofit/blob/master/imgs/weather.gif)<br/>

#### 使用方法：

    /**
        这里是访问的百度天气预报接口
        返回数据可以根据自己的需要更改 HttpResult<T> 类里面的参数 (如：int code; T data; String msg）
        
    */
    UserApi.getInstance().getWeather("成都", "json", "5NImrCXDE8hR05Yc49Bgs5QG", new HttpSubscriber<WeatherBean>(new SubscriberOnListener<WeatherBean>() {
        @Override
        public void onSucceed(WeatherBean data) {
            hideLoadDialog();//这个可以在回调里面添加到complete里面，关闭对话框只写到这个里面就可以了
        }
    
        @Override
        public void onError(String msg) {
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            hideLoadDialog();
        }
    }, MainActivity.this));
    

# create by ywl5320