package com.jingna.workshopapp.app;

import android.app.Activity;
import android.app.Application;

import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.BankCodeTimeCount;
import com.jingna.workshopapp.util.FTPTimeCount;
import com.jingna.workshopapp.util.ForgotTimeCount;
import com.jingna.workshopapp.util.SMSCodeTimeCount;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedList;
import java.util.List;

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
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL);
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
