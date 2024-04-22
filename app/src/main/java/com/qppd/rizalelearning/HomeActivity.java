package com.qppd.rizalelearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;
import com.qppd.rizalelearning.Libs.SharedPreferencez.SharedPreferencesClass;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private UserFunctions userFunctions = new UserFunctions(this);
    private SharedPreferencesClass sharedPreferencesClass;
    private ImageView imgSetting;

    private AlertDialog.Builder exit_dialog_builder;
    private AlertDialog exit_dialog;

    private ImageButton istorya;
    private ImageButton patungkol;
    private ImageButton tribya;

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userFunctions.setActionbar(getSupportActionBar(), 0, "" , 0);
        sharedPreferencesClass = new SharedPreferencesClass(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.button_sound);

        imgSetting = findViewById(R.id.icon_setting);
        imgSetting.setOnClickListener(this);
        istorya = findViewById(R.id.istorya);
        patungkol = findViewById(R.id.patungkol);
        tribya = findViewById(R.id.tribya);

        istorya.setOnClickListener(this);
        patungkol.setOnClickListener(this);
        tribya.setOnClickListener(this);

        exit_dialog_builder = new AlertDialog.Builder(this);
        exit_dialog_builder.setCancelable(true);
        exit_dialog_builder.setTitle("Kumpirmasyon");
        exit_dialog_builder.setMessage("Sigurado ka bang gusto mong lumabas?");

        exit_dialog_builder.setPositiveButton("Kumpirmahin", (dialog, which) -> attemptExit());
        exit_dialog_builder.setNegativeButton("Kanselahin", (dialog, which) -> exit_dialog.dismiss());
        exit_dialog = exit_dialog_builder.create();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.icon_setting:
                playSound();
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.istorya:
                playSound();
                startActivity(new Intent(this, RoadmapActivity.class));
                break;
            case R.id.patungkol:
                playSound();
                startActivity(new Intent(this, RegardingActivity.class));
                break;
            case R.id.tribya:
                playSound();
                startActivity(new Intent(this, TriviaActivity.class));
                break;

        }
    }

    private void playSound() {
        if(sharedPreferencesClass.getBoolean("button", false)){
            mediaPlayer.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit:
                exit_dialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void attemptExit(){

    }

}