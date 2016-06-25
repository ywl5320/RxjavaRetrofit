package com.ywl5320.rxjavaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ywl5320.rxjavaretrofit.dialog.LoadDialog;
import com.ywl5320.rxjavaretrofit.httpservice.beans.UserBean;
import com.ywl5320.rxjavaretrofit.httpservice.beans.WeatherBean;
import com.ywl5320.rxjavaretrofit.httpservice.serviceapi.UserApi;
import com.ywl5320.rxjavaretrofit.httpservice.subscribers.HttpSubscriber;
import com.ywl5320.rxjavaretrofit.httpservice.subscribers.SubscriberOnListener;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    public LoadDialog loadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLoadDialog("正在获取天气数据");

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
                }));
            }
        });

    }

    public void showLoadDialog(String msg)
    {
        if(loadDialog == null)
        {
            loadDialog = new LoadDialog(this);
        }
        loadDialog.show();
        loadDialog.setLoadMsg(msg);
    }

    public void hideLoadDialog()
    {
        if(loadDialog != null)
            loadDialog.dismiss();
    }
}
