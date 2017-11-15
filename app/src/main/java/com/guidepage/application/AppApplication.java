package com.guidepage.application;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Description: AppApplication
 * Author: zsk
 * Date: 2017/11/14  16:21
 * Email: zsk559521@163.com
 */

public class AppApplication extends Application {
    private static SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private static AppApplication instance;
    private static int isLogin = 0;//判断是否已登录
    private static boolean isFirstApp;

    public int getIsLogin() {
        if (sp == null) {
            sp = getApplicationContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        }
        return sp.getInt("isLogin", 0);
    }

    public void setIsLogin(int isLogin) {
        if (sp == null) {
            sp = getApplicationContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        }
        if (edit == null) {
            edit = sp.edit();
        }
        edit.putInt("isLogin", isLogin);
        edit.commit();
        AppApplication.isLogin = isLogin;
    }
    public boolean isIsFirstApp() {
        if (sp == null) {
            sp = getApplicationContext().getSharedPreferences("userInfo", MODE_PRIVATE);
        }
        isFirstApp = sp.getBoolean("First", true);
        return isFirstApp;
    }
    public void setFirstApp(boolean isFirstApp) {
        if (sp == null) {
            sp = getApplicationContext().getSharedPreferences("userInfo",MODE_PRIVATE);
        }
        if(edit==null){
            edit = sp.edit();
        }
        edit.putBoolean("First", false);
        edit.commit();
        AppApplication.isFirstApp = isFirstApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static AppApplication getInstance() {
        return instance;
    }
}
