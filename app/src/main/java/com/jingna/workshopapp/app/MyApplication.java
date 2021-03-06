package com.jingna.workshopapp.app;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.BankCodeTimeCount;
import com.jingna.workshopapp.util.FTPTimeCount;
import com.jingna.workshopapp.util.ForgotTimeCount;
import com.jingna.workshopapp.util.SMSCodeTimeCount;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2019/2/13.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> mList = new LinkedList<Activity>();
    // 修改密码获取验证码倒计时
    public static SMSCodeTimeCount smsCodeTimeCount;
    public static FTPTimeCount ftptimecount;
    public static ForgotTimeCount forgotTimeCount;
    public static BankCodeTimeCount bankCodeTimeCount;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        ZXingLibrary.initDisplayOpinion(this);
        smsCodeTimeCount = new SMSCodeTimeCount(60000, 1000);
        ftptimecount = new FTPTimeCount(60000, 1000);
        forgotTimeCount = new ForgotTimeCount(60000, 1000);
        bankCodeTimeCount = new BankCodeTimeCount(60000, 1000);
    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}
