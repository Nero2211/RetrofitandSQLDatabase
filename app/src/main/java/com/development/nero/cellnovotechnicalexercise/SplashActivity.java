package com.development.nero.cellnovotechnicalexercise;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewDebug;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends AwesomeSplash {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }*/

    @Override
    public void initSplash(ConfigSplash configSplash) {
       ActionBar actionBar = getSupportActionBar();
       actionBar.hide();

       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               WindowManager.LayoutParams.FLAG_FULLSCREEN);

       configSplash.setBackgroundColor(R.color.aboutUs);
       configSplash.setAnimCircularRevealDuration(1000);
       configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
       configSplash.setRevealFlagX(Flags.REVEAL_TOP);

       configSplash.setLogoSplash(R.drawable.cnlogo);
       configSplash.setAnimLogoSplashDuration(1000);
       configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn);

       configSplash.setTitleSplash("Products Exercise");
       configSplash.setTitleTextSize(30f);
       configSplash.setAnimTitleDuration(1000);
    }

    @Override
    public void animationsFinished() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
