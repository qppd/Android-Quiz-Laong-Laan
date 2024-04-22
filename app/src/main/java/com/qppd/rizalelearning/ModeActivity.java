package com.qppd.rizalelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.qppd.rizalelearning.Globals.Mode;
import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;
import com.qppd.rizalelearning.Libs.IntentManager.IntentManagerClass;
import com.qppd.rizalelearning.Libs.SharedPreferencez.SharedPreferencesClass;

public class ModeActivity extends AppCompatActivity implements View.OnClickListener {

    private UserFunctions userFunctions = new UserFunctions(this);
    private SharedPreferencesClass sharedPreferencesClass;
    private MediaPlayer mediaPlayer;

    private ImageButton btnEasy, btnHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        userFunctions.noActionBar(getSupportActionBar());
        sharedPreferencesClass = new SharedPreferencesClass(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_sound);

        btnEasy = findViewById(R.id.btnEasy);
        btnHard = findViewById(R.id.btnHard);

        btnEasy.setOnClickListener(this);
        btnHard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEasy:
                Mode.setMode("easy");
                playSound();
                finish();
                IntentManagerClass.intentsify(ModeActivity.this, QuizActivity.class);
                break;
            case R.id.btnHard:
                Mode.setMode("hard");
                playSound();
                finish();
                IntentManagerClass.intentsify(ModeActivity.this, QuizActivity.class);
                break;
        }
    }

    private void playSound() {
        if(sharedPreferencesClass.getBoolean("button", false)){
            mediaPlayer.start();
        }
    }
}