package com.qppd.rizalelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qppd.rizalelearning.Globals.Chapter;
import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;
import com.qppd.rizalelearning.Libs.IntentManager.IntentManagerClass;
import com.qppd.rizalelearning.Libs.SharedPreferencez.SharedPreferencesClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RoadmapActivity extends AppCompatActivity {

    private UserFunctions userFunctions = new UserFunctions(this);
    private SharedPreferencesClass sharedPreferencesClass;

    private MediaPlayer mediaPlayer;

    private LinearLayout roadmapLayout;

    private ArrayList<TextView> textViewArrayList = new ArrayList<>();

    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadmap);

        userFunctions.setActionbar(getSupportActionBar(), 1, "Istorya", 0);
        sharedPreferencesClass = new SharedPreferencesClass(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_sound);

        roadmapLayout = findViewById(R.id.roadmapLayout);

        showChapters(4);
        //handler.post(updateClockRunnable);

    }

    private void showChapters(int chaptersCount) {
        for (int x = 0; x < chaptersCount; x++) {
            createButton(x);
        }
    }

    private void createButton(int chapter) {

        int current_chapter = sharedPreferencesClass.getInt("current_open_chapter", 1);
        //userFunctions.showMessage("CURRENT_OPEN_CHAPER:" + current_chapter);

        TextView textView = new TextView(this);
        textViewArrayList.add(textView);
        chapter += 1;

        textView.setTextSize(18);
        textView.setTextColor(Color.WHITE);
        textView.setId(chapter);

        if (current_chapter < chapter) {
            textView.setEnabled(false);
            Drawable icon = getResources().getDrawable(R.drawable.ic_baseline_lock_24);
            textView.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);

        }else{
            Drawable icon = getResources().getDrawable(R.drawable.ic_baseline_lock_open_24);
            textView.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        }


        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        buttonLayoutParams = setButtonLayout(chapter, buttonLayoutParams);

        int screenHeight = getScreenHeight(this) / 4;
        //userFunctions.showMessage(screenHeight + "");
        textView.setHeight(screenHeight);
        setPadding(textView, chapter);
        //textView.setBackgroundColor(Color.BLACK);
        textView.setLayoutParams(buttonLayoutParams);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferencesClass.getBoolean("button", false)){
                    mediaPlayer.start();
                }
                Chapter.setChapter("chapter" + view.getId());
                //int current_chapter = view.getId();
                //sharedPreferencesClass.putInt("current_open_chapter", current_chapter + 1);
                IntentManagerClass.intentsify(RoadmapActivity.this, StoryActivity.class);
            }
        });

        roadmapLayout.addView(textView);
    }

    private LinearLayout.LayoutParams setButtonLayout(int chapter, LinearLayout.LayoutParams buttonLayoutParams) {
        int left = 0, top = 0, right = 0, bottom = 0;
        if (chapter == 1) {
            buttonLayoutParams.gravity = Gravity.CENTER;
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        } else if (chapter == 2) {
            buttonLayoutParams.gravity = Gravity.CENTER;
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        } else if (chapter == 3) {
            buttonLayoutParams.gravity = Gravity.CENTER;
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        } else if (chapter == 4) {
            buttonLayoutParams.gravity = Gravity.CENTER;
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }


        buttonLayoutParams.setMargins(left, top, right, bottom);

        return buttonLayoutParams;
    }

    private void setPadding(TextView textView, int chapter) {
        int left = 0, top = 0, right = 0, bottom = 0;
        if (chapter == 1) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        } else if (chapter == 2) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        } else if (chapter == 3) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        } else if (chapter == 4) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }
        textView.setPadding(left, top, right, bottom);
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
            return metrics.heightPixels;
        }
        return 0; // Default value or handle the error as needed
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
    protected void onResume() {
        super.onResume();

        roadmapLayout.removeAllViews();
        showChapters(4);


        //userFunctions.showMessage("CONTINUING");
    }
}