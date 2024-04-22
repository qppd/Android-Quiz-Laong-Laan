package com.qppd.rizalelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;
import com.qppd.rizalelearning.Libs.SharedPreferencez.SharedPreferencesClass;

public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private UserFunctions userFunctions = new UserFunctions(this);
    private SharedPreferencesClass sharedPreferencesClass;

    private Switch swtMusic;
    private Switch swtButton;

    private boolean music;
    private boolean button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        userFunctions.setActionbar(getSupportActionBar(), 1, "Mga Setting", 0);
        sharedPreferencesClass = new SharedPreferencesClass(this);

        swtMusic = findViewById(R.id.swtMusic);
        swtMusic.setOnCheckedChangeListener(this);

        swtButton = findViewById(R.id.swtButton);
        swtButton.setOnCheckedChangeListener(this);

        getSettings();
    }

    private void getSettings() {

        music = sharedPreferencesClass.getBoolean("music", false);
        button = sharedPreferencesClass.getBoolean("button", false);

        if (music) {
            swtMusic.setChecked(true);
        } else {
            swtMusic.setChecked(false);
        }

        if (button) {
            swtButton.setChecked(true);
        } else {
            swtButton.setChecked(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.swtMusic:
                if(swtMusic.isChecked()){
                    sharedPreferencesClass.putBoolean("music", true);
                }else{
                    sharedPreferencesClass.putBoolean("music", false);
                }

                break;
            case R.id.swtButton:
                if(swtButton.isChecked()){
                    sharedPreferencesClass.putBoolean("button", true);
                }else{
                    sharedPreferencesClass.putBoolean("button", false);
                }
                break;
        }
    }
}