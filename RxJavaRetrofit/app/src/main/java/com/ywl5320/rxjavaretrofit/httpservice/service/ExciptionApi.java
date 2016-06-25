package com.ywl5320.rxjavaretrofit.httpservice.service;


import com.ywl5320.rxjavaretrofit.httpservice.subscribers.ResponStatusCode;

/**
 * Created by ywl on 2016/5/19.
 */
public class ExciptionApi extends RuntimeException{

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;

    public ExciptionApi(int resultCode, String msg) {
        this(getApiExceptionMessage(resultCode, msg));
    }

    public ExciptionApi(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code, String msg){
        String message = ResponStatusCode.getCode(code, msg);
        return message;
    }

}
