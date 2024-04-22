package com.qppd.rizalelearning;

import android.content.Intent;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;
import com.qppd.rizalelearning.Libs.SharedPreferencez.SharedPreferencesClass;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import java.util.Objects;

public class MainActivity extends AwesomeSplash {

    private UserFunctions userFunctions = new UserFunctions(this);
    private SharedPreferencesClass sharedPreferencesClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        sharedPreferencesClass = new SharedPreferencesClass(this);
    }

    @Override
    public void initSplash(ConfigSplash configSplash) {
        configSplash.setBackgroundColor(R.color.colorMain);
        configSplash.setAnimCircularRevealDuration(1000);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);
        configSplash.setTitleSplash("Laong-laan");
        configSplash.setTitleTextColor(R.color.white);

        configSplash.setLogoSplash(R.drawable.applogo); //or any other drawable
        configSplash.setTitleTextSize(50);
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.BounceInRight);
        configSplash.setAnimTitleDuration(2000);
    }

    @Override
    public void animationsFinished() {

        boolean isFirstTimeRunning = true;
        isFirstTimeRunning = sharedPreferencesClass.getBoolean("isFirstTime", true);

        if(isFirstTimeRunning){
            sharedPreferencesClass.putBoolean("isFirstTime", false);
            sharedPreferencesClass.putInt("current_open_chapter", 1);
            //userFunctions.showMessage("this is the first time this app is ran");
        }else{
            //userFunctions.showMessage("this is the second time this app is ran");
        }

        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();

    }
}