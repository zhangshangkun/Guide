package com.guidepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.guidepage.application.AppApplication;
/**
 * Description: LoginActivity
 * Author: luocen
 * Date: 2017/11/14  16:21
 * Email: zsk559521@163.com
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    //登录
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initView();
    }
    private void initView() {
        Login=findViewById(R.id.login);
        Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                //登录判断 登录成功
                AppApplication.getInstance().setIsLogin(LauncherActivity.RESULT_MAIN);//不用重复登录
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
                break;
                default:
                    break;
        }
    }
}
