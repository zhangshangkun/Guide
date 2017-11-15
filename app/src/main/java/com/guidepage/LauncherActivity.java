package com.guidepage;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.guidepage.application.AppApplication;

/**
 * Description: LauncherActivity
 * Author: luocen
 * Date: 2017/11/14  16:21
 * Email: zsk559521@163.com
 */
public class LauncherActivity extends Activity {
    public final static int RESULT_GUID = 0;
    public final static int RESULT_LOGIN = 1;
    public final static int RESULT_MAIN = 2;
    private static final long SPLASH_DELAY_MILLIS = 500;
    private static final long WELCOME_DELAY_MILLIS = 1000;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Intent intent = null;
            switch (msg.what) {
                case RESULT_GUID: //0
                    intent = new Intent(LauncherActivity.this, GuideActivity.class);
                    break;
                case RESULT_LOGIN:  //1
                    intent = new Intent(LauncherActivity.this, LoginActivity.class);
                    break;
                case RESULT_MAIN:  //2
                    intent = new Intent(LauncherActivity.this, MainActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果是第一次启动，则先进入功能引导页
        if (AppApplication.getInstance().isIsFirstApp()) {
            Log.i("TAG","判断=="+AppApplication.getInstance().isIsFirstApp());
            Intent intent = new Intent(LauncherActivity.this, GuideActivity.class);
            startActivity(intent);
            AppApplication.getInstance().setFirstApp(false);
            finish();
        }else{
            Log.i("TAG","app==="+AppApplication.getInstance().isIsFirstApp());
            final View view = View.inflate(this, R.layout.start, null);
            setContentView(view);
            getData();
            new StartUpTask().execute();
        }
    }
    private void getData() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
            }
        }, 300);
    }
    private final class StartUpTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... params) {
            int isLogin = AppApplication.getInstance().getIsLogin();
            return isLogin == 0 ? RESULT_GUID : isLogin == 1 ? RESULT_LOGIN
                    : RESULT_MAIN;
        }
        @Override
        protected void onPostExecute(Integer result) {
            switch (result) {
                case RESULT_GUID:
                    mHandler.sendEmptyMessageDelayed(RESULT_GUID, WELCOME_DELAY_MILLIS);
                    break;
                case RESULT_LOGIN:
                    mHandler.sendEmptyMessageDelayed(RESULT_LOGIN, SPLASH_DELAY_MILLIS);
                    break;
                case RESULT_MAIN:
                    mHandler.sendEmptyMessageDelayed(RESULT_MAIN,
                            SPLASH_DELAY_MILLIS);
                    break;
                default:
                    break;
            }
            super.onPostExecute(result);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
