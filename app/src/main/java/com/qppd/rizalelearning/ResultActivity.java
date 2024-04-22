package com.qppd.rizalelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.qppd.rizalelearning.Globals.Chapter;
import com.qppd.rizalelearning.Globals.Mode;
import com.qppd.rizalelearning.Globals.Quiz;
import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;
import com.qppd.rizalelearning.Libs.IntentManager.IntentManagerClass;
import com.qppd.rizalelearning.Libs.SharedPreferencez.SharedPreferencesClass;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private UserFunctions userFunctions = new UserFunctions(this);
    private SharedPreferencesClass sharedPreferencesClass;

    private MediaPlayer mediaPlayer;

    private TextView quizStatus;
    private TextView quizScore;

    private ImageButton btnNext;
    private ImageButton btnExit;
    private ImageButton btnReQuiz;

    int current_chapter;
    String currentChapter;

    int question_count = 0;
    int correct_answer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        userFunctions.setActionbar(getSupportActionBar(), 1, "Resulta ng Pagsusulit", 0);
        sharedPreferencesClass = new SharedPreferencesClass(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_sound);

        quizStatus = findViewById(R.id.quizStatus);
        quizScore = findViewById(R.id.quizScore);
        btnNext = findViewById(R.id.btnNext);
        btnReQuiz = findViewById(R.id.btnReQuiz);
        btnExit = findViewById(R.id.btnExit);


        correct_answer = Quiz.getScore();

        //userFunctions.showMessage(String.valueOf(correct_answer));

        if(Mode.getMode().equals("easy")){
            //userFunctions.showMessage(Mode.getMode());
            question_count = 10;
            if(correct_answer >= 7){
                quizStatus.setText("NAKAPASA");
                quizScore.setText(correct_answer + "/" + question_count);
                btnNext.setVisibility(View.VISIBLE);
                btnReQuiz.setVisibility(View.INVISIBLE);

            }else if(correct_answer < 7){
                quizStatus.setText("NABIGO");
                quizScore.setText(correct_answer + "/" + question_count);
                btnNext.setVisibility(View.INVISIBLE);
                btnReQuiz.setVisibility(View.VISIBLE);
            }
        }
        else if(Mode.getMode().equals("hard")){
            //userFunctions.showMessage(Mode.getMode());
            question_count = 5;
            if(correct_answer >= 3){
                quizStatus.setText("NAKAPASA");
                quizScore.setText(correct_answer + "/" + question_count);
                btnNext.setVisibility(View.VISIBLE);
                btnReQuiz.setVisibility(View.INVISIBLE);


            }else if(correct_answer < 3){
                quizStatus.setText("NABIGO");
                quizScore.setText(correct_answer + "/" + question_count);
                btnNext.setVisibility(View.INVISIBLE);
                btnReQuiz.setVisibility(View.VISIBLE);

            }
        }

        current_chapter = Integer.parseInt(Chapter.getChapter().replace("chapter", ""));

        btnReQuiz.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                current_chapter  += 1;
                currentChapter = "chapter" + current_chapter;

                Chapter.setChapter(currentChapter);
                sharedPreferencesClass.putInt("current_open_chapter", current_chapter);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                if(sharedPreferencesClass.getBoolean("button", false)){
                    mediaPlayer.start();
                }
                IntentManagerClass.intentsify(ResultActivity.this, StoryActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP);
                current_chapter  += 1;
                currentChapter = "chapter" + current_chapter;

                //userFunctions.showMessage(currentChapter);
                Chapter.setChapter(currentChapter);
                sharedPreferencesClass.putInt("current_open_chapter", current_chapter);
                finish();
                break;
            case R.id.btnReQuiz:
                if(sharedPreferencesClass.getBoolean("button", false)){
                    mediaPlayer.start();
                }

                currentChapter = "chapter" + current_chapter;

                //userFunctions.showMessage(currentChapter);
                Chapter.setChapter(currentChapter);
                sharedPreferencesClass.putInt("current_open_chapter", current_chapter);

                IntentManagerClass.intentsify(ResultActivity.this, QuizActivity.class);
                finish();

                break;
            case R.id.btnExit:
                if(sharedPreferencesClass.getBoolean("button", false)){
                    mediaPlayer.start();
                }
                IntentManagerClass.intentsify(ResultActivity.this, HomeActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                break;
        }
    }
}