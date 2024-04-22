package com.qppd.rizalelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.qppd.rizalelearning.Globals.Chapter;
import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;
import com.qppd.rizalelearning.Libs.IntentManager.IntentManagerClass;
import com.qppd.rizalelearning.Libs.SharedPreferencez.SharedPreferencesClass;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener {

    private UserFunctions userFunctions = new UserFunctions(this);
    private SharedPreferencesClass sharedPreferencesClass;

    private MediaPlayer mediaPlayer;
    private MediaPlayer btnPlayer;

    private ScrollView scrollView;

    private ImageView imageView;

    private ImageButton btnQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        userFunctions.setActionbar(getSupportActionBar(), 1, "Istorya", 0);
        sharedPreferencesClass = new SharedPreferencesClass(this);

        scrollView = findViewById(R.id.scrollView);

        imageView = findViewById(R.id.imageView);

        btnQuiz = findViewById(R.id.btnQuiz);
        btnQuiz.setOnClickListener(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.musicbg);
        mediaPlayer.setLooping(true);
        btnPlayer = MediaPlayer.create(this, R.raw.button_sound);


        if(sharedPreferencesClass.getBoolean("music", false)){
            mediaPlayer.start();
        }

        //imageView.setImageResource(R.drawable.chapter2);

        if (Chapter.getChapter().equals("chapter1")) {
            //sharedPreferencesClass.putInt("current_image", R.drawable.chapter1);
            imageView.setImageResource(R.drawable.chapter1);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.chapter1, getApplicationContext().getTheme()));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.chapter1));
            }
        } else if (Chapter.getChapter().equals("chapter2")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.chapter2, getApplicationContext().getTheme()));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.chapter2));
            }
        } else if (Chapter.getChapter().equals("chapter3")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.chapter3, getApplicationContext().getTheme()));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.chapter3));
            }
        } else if (Chapter.getChapter().equals("chapter4")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.chapter4, getApplicationContext().getTheme()));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.chapter4));
            }
        }

        scrollView.smoothScrollTo(0, 0);

//        new CountDownTimer(10000, 20) {
//
//            public void onTick(long millisUntilFinished) {
//                scrollView.scrollTo(0, (int) (10000 - millisUntilFinished)); // from zero to 2000
//            }
//
//            public void onFinish() {
//            }
//
//        }.start();


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
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btnNext:
//                int currentImageResource = sharedPreferencesClass.getInt("current_image", 0);
//                int nextImageResource = getNextImageResource(currentImageResource);
//                sharedPreferencesClass.putInt("current_image", nextImageResource);
//
//                imageView.setImageResource(nextImageResource);
//                scrollView.smoothScrollTo(0, 0);
//                break;
            case R.id.btnQuiz:
                if(sharedPreferencesClass.getBoolean("button", false)){
                    btnPlayer.start();
                }
                finish();
                IntentManagerClass.intentsify(StoryActivity.this, ModeActivity.class);
                break;
        }
    }


    private int getNextImageResource(int currentImageResource) {
        int[] drawableResources = {R.drawable.chapter1, R.drawable.chapter2};
        int currentIndex = 0;

        for (int i = 0; i < drawableResources.length; i++) {
            if (drawableResources[i] == currentImageResource) {
                currentIndex = i;
                break;
            }
        }

        int nextIndex = (currentIndex + 1) % drawableResources.length;
        return drawableResources[nextIndex];
    }

    @Override
    protected void onDestroy() {
        if(sharedPreferencesClass.getBoolean("music", false)){
            mediaPlayer.stop();
        }

        super.onDestroy();
    }
}