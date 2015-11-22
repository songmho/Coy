package com.asc.coy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.parse.ParseUser;

/**
 * Created by songmho on 2015-10-31.
 */
public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_TIME=1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(ParseUser.getCurrentUser()==null)
                    startActivity(new Intent(SplashActivity.this,Login_Activity.class));
                else
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },SPLASH_TIME);

    }
}
