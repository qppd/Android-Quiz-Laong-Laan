package com.qppd.rizalelearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qppd.rizalelearning.Globals.Chapter;
import com.qppd.rizalelearning.Globals.Mode;
import com.qppd.rizalelearning.Globals.Quiz;
import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;
import com.qppd.rizalelearning.Libs.IntentManager.IntentManagerClass;
import com.qppd.rizalelearning.Libs.SharedPreferencez.SharedPreferencesClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    private UserFunctions userFunctions = new UserFunctions(this);
    private SharedPreferencesClass sharedPreferencesClass;

    private MediaPlayer mediaPlayer;

    private LinearLayout quizLayout;

    private TextView correctCounter;
    private int correct_counter = 0;

    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userFunctions.setActionbar(getSupportActionBar(), 1, "Pagsusulit", 0);
        sharedPreferencesClass = new SharedPreferencesClass(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_sound);

        typeface = ResourcesCompat.getFont(this, R.font.timesnewroman);

        correctCounter = findViewById(R.id.correct_counter);
        correctCounter.setVisibility(View.INVISIBLE);
        quizLayout = findViewById(R.id.quizLayout);

        loadQuiz();

        ImageButton btnSubmit = new ImageButton(this);
        //btnSubmit.setText("IPASA");
        btnSubmit.setBackground(getDrawable(R.drawable.buttonipasa));
        //btnSubmit.setTextColor(Color.WHITE);
        //btnSubmit.setBackgroundColor(getResources().getColor(R.color.colorMain));
        //btnSubmit.setTextSize(18);
        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(600,
                150);
        buttonLayoutParams.setMargins(0, 100, 0, 100);
        buttonLayoutParams.gravity = Gravity.CENTER;

        btnSubmit.setLayoutParams(buttonLayoutParams);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferencesClass.getBoolean("button", false)){
                    mediaPlayer.start();
                }
                Quiz.setScore(correct_counter);
                finish();
                IntentManagerClass.intentsify(QuizActivity.this, ResultActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        });
        quizLayout.addView(btnSubmit);

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

    private void loadQuiz() {
        if (Mode.getMode().equals("easy") && Chapter.getChapter().equals("chapter1")) {
            //userFunctions.showMessage("Chapter 1! Easy mode!");

            setChapter1EasyQuiz();

        } else if (Mode.getMode().equals("hard") && Chapter.getChapter().equals("chapter1")) {
            //userFunctions.showMessage("Chapter 1! Hard mode!");

            setChapter1HardQuiz();
        } else if (Mode.getMode().equals("easy") && Chapter.getChapter().equals("chapter2")) {
            //userFunctions.showMessage("Chapter 2! Easy mode!");

            setChapter2EasyQuiz();

        } else if (Mode.getMode().equals("hard") && Chapter.getChapter().equals("chapter2")) {
            //userFunctions.showMessage("Chapter 2! Hard mode!");

            setChapter2HardQuiz();
        } else if (Mode.getMode().equals("easy") && Chapter.getChapter().equals("chapter3")) {
            //userFunctions.showMessage("Chapter 3! Easy mode!");

            setChapter3EasyQuiz();

        } else if (Mode.getMode().equals("hard") && Chapter.getChapter().equals("chapter3")) {
            //userFunctions.showMessage("Chapter 3! Hard mode!");

            setChapter3HardQuiz();
        } else if (Mode.getMode().equals("easy") && Chapter.getChapter().equals("chapter4")) {
            //userFunctions.showMessage("Chapter 4! Easy mode!");

            setChapter4EasyQuiz();

        } else if (Mode.getMode().equals("hard") && Chapter.getChapter().equals("chapter4")) {
            //userFunctions.showMessage("Chapter 4! Hard mode!");

            setChapter4HardQuiz();
        }
    }

    private void setChapter1HardQuiz() {

        String[] questionss = {"Siya ang ama ni Jose na pinagmanahan niya ng kanyang mga katangian",
                "Siya naman ang ina ni Jose na nagtataglay ng mataas na pinag-aralan at interes sa panitikan?",
                "Si Jose ay pang-ilan sa magkakapatid?",
                "Kailan nagsimulang makilala ang bayan ng Calamba bilang katunggali ng Binan sa produksiyon ng bigas sa lalawigan?",
                "Ang buong pangalan ni Jose ay?",
                "Sinong kapatid ni Jose ang sumama sa kanya papuntang Biñan Laguna?",
                "Araw ng kapanganakan ni Jose"
        };

        String[] answerss = {"Don Francisco Mercado",
                "Donya Teodora Alonso",
                "Pito",
                "1860",
                "Jose Protacio Rizal Mercado y Alonso Realonda",
                "Paciano",
                "Hunyo 19, 1861"
        };

        correct_counter = 0;

        String[][] shuffledArrays = shuffleArrays(questionss, answerss);

        for (int i = 0; i < shuffledArrays[0].length; i++) {
            int index = i + 1;
            sharedPreferencesClass.putString("c1-hq" + index, shuffledArrays[0][i]);
            sharedPreferencesClass.putString("c1-ha" + index, shuffledArrays[1][i]);
        }

        String answers[] = new String[5];

        for (int x = 1; x <= 5; x++) {
            answers[x - 1] = sharedPreferencesClass.getString("c1-ha" + x, "");


            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setText(x + ". " + sharedPreferencesClass.getString("c1-hq" + x, ""));
            textView.setTypeface(typeface);
            LinearLayout.LayoutParams questionLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );

            questionLayoutParams.setMargins(40, 10, 40, 50);

            quizLayout.addView(textView);

            TextView txtAnswer = new TextView(this);
            txtAnswer.setText(answers[x - 1]);
            txtAnswer.setTextColor(Color.BLACK);
            txtAnswer.setTextSize(18);

            TextView txtStatus = new TextView(this);
            txtStatus.setText(answers[x - 1]);
            txtStatus.setTextColor(Color.BLACK);
            txtStatus.setTextSize(18);
            txtStatus.setText("WRONG");


            EditText editText = new EditText(this);
            editText.setHint("Enter your answer here...");
            //editText.setId(x + 1000);
            editText.setTextSize(20);
            LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            editTextParams.setMargins(40, 10, 40, 10);
            editText.setLayoutParams(editTextParams);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if (txtAnswer.getText().toString().trim().toLowerCase().equals("pito") && charSequence.toString().trim().toLowerCase().equals("7")) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("WRONG") && charSequence.toString().trim().toLowerCase().equals(txtAnswer.getText().toString().trim().toLowerCase())) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("CORRECT")) {
                        txtStatus.setText("WRONG");
                        correct_counter--;
                        correctCounter.setText(String.valueOf(correct_counter));
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            quizLayout.addView(editText);
            quizLayout.addView(txtAnswer);
            quizLayout.addView(txtStatus);

            txtAnswer.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);
        }

        //Quiz.setAnswers(answers);
    }

    private void setChapter1EasyQuiz() {

        String[] questionss = {"Sino ang nagmamay-ari ng hacienda na pinapamahala ng ama ni Jose?",
                "Sino ang nagmamay-ari ng hacienda na pinapamahala ng ama ni Jose?",
                "Ilan ang mga anak ng mag-asawang Donya Teodora Alfonso at Don Francisco Mercado?",
                "Kailan si Jose ipinanganak?",
                "Kasabay ng anong pagdiriwang ginanap ang pagbinyag kay Jose?",
                "Siya ang unang naging pormal na guro ni Jose?",
                "Anong nangyari kay Leon noong siyam na gulang pa lamang si Jose?",
                "Saan pinadala si Jose ng kanyang ama para ipagpatuloy ang kanyang pag-aaral?",
                "Sino ang sumama kay Jose papuntang binan?",
                "Kaano-ano ni Jose ang taong nagpatira sa kaniya sa binan?",
                "Ano ang pangalan ng ama ni Jose?",
                "Ano ang pangalan ng ina ni Jose?",
                "Pang ilan si Jose sa magkakapatid?",
                "Kailan nakilala ang bayan ng Calamba bilang katunggali ng Biñan?",
                "Tunay na pangalan ni Dr. Jose Rizal ay?"
        };

        String[] choicess = {"Hacienda sa Calamba:Hacienda sa Binan:Hacienda sa Laguna:Hacienda sa Rizal",
                "Grupo ng mga kapariang Dominikano:Grupo ng mga Indio:Grupo ng mga Mestizo:Grupo ng mga Kastila",
                "Labing-dalawa:Labing-pito:Labing-apat:Labing-isa",
                "Hunyo 29,1861:Hunyo 19,1861:Hulyo 29,1861:Hulyo 19,1861",
                "Araw ng kapistahan ni San Jose:Araw ng patay:Araw ng libing ng kanyang lola:Araw ng pagkabuhay",
                "Padre Jose Bech:G.Justiniano Aquino Cruz:Leon Monroy:Manuel Xeres",
                "Nag-aral:Namatay:Nag-asawa:Nangibang-bayan",
                "Rizal:Calamba:Binan:Laguna",
                "Soledad:Paciano:Maria:Josefa",
                "Kaibigan:Ninong:Tiyahin:Kapatid",
                "Don Juan Mercado:Don Teodoro Mercado:Don Gregorio Mercado:Don Francisco Mercado",
                "Donya Eleonora Mercado:Donya Teodora Mercado:Donya Francisca Mercado:Donya Juana Mercado",
                "8:6:7:9",
                "1860:1870:1880:1890",
                "Jose Gregorio Rizal Mercado:Juan Rizal Mercado y Alonso Realonda:Jose Protacio Rizal Mercado y Alonso Realonda:Juan Protacio Rizal Mercado y Alonso Realonda"
        };

        String[] answerss = {"Hacienda sa Calamba",
                "Grupo ng mga kapariang Dominikano",
                "Labing-isa",
                "Hunyo 19,1861",
                "Araw ng kapistahan ni San Jose",
                "Leon Monroy",
                "Namatay",
                "Binan",
                "Paciano",
                "Tiyahin",
                "Don Francisco Mercado",
                "Donya Teodora Mercado",
                "7",
                "1860",
                "Jose Protacio Rizal Mercado y Alonso Realonda"
        };

        correct_counter = 0;

        String[][] shuffledArrays = shuffleArrays(questionss, answerss, choicess);

        for (int i = 0; i < shuffledArrays[0].length; i++) {
            int index = i + 1;
            sharedPreferencesClass.putString("c1-eq" + index, shuffledArrays[0][i]);
            sharedPreferencesClass.putString("c1-ea" + index, shuffledArrays[1][i]);
            sharedPreferencesClass.putString("c1-ec" + index, shuffledArrays[2][i]);

        }

        String answers[] = new String[15];

        for (int x = 1; x <= 10; x++) {

            answers[x - 1] = sharedPreferencesClass.getString("c1-ea" + x, "");

            LinearLayout questionLayout = new LinearLayout(this);
            questionLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            layoutParams.setMargins(40, 0, 40, 50);
            questionLayout.setLayoutParams(layoutParams);

            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setText(x + ". " + sharedPreferencesClass.getString("c1-eq" + x, ""));
            textView.setTypeface(typeface);
            questionLayout.addView(textView);

            String choices[] = sharedPreferencesClass.getString("c1-ec" + x, "").split(":");

            LinearLayout choicesLayout1 = new LinearLayout(this);
            choicesLayout1.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams choicesLayout1Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            choicesLayout1Params.setMargins(20, 10, 20, 70);
            choicesLayout1.setLayoutParams(choicesLayout1Params);

            RadioGroup radioGroup = new RadioGroup(this);

            LinearLayout.LayoutParams radioGroupParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            radioGroup.setLayoutParams(radioGroupParams);

            TextView txtAnswer = new TextView(this);
            txtAnswer.setText(answers[x - 1]);
            txtAnswer.setTextColor(Color.BLACK);
            txtAnswer.setTextSize(18);

            TextView txtStatus = new TextView(this);
            txtStatus.setText(answers[x - 1]);
            txtStatus.setTextColor(Color.BLACK);
            txtStatus.setTextSize(18);
            txtStatus.setText("WRONG");

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    String textValue = checkedRadioButton.getText().toString();

                    if (txtStatus.getText().equals("WRONG") && textValue.trim().toLowerCase().equals(txtAnswer.getText().toString().trim().toLowerCase())) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("CORRECT")) {
                        txtStatus.setText("WRONG");
                        correct_counter--;
                        correctCounter.setText(String.valueOf(correct_counter));
                    }

                }
            });

            RadioButton[] rb = new RadioButton[4];

            for (int y = 0; y < choices.length; y++) {
                rb[y] = new RadioButton(this);
                rb[y].setText(choices[y]);
                rb[y].setTextSize(20);
                LinearLayout.LayoutParams radioButtonParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, // Use WRAP_CONTENT for width
                        LinearLayout.LayoutParams.WRAP_CONTENT  // Use WRAP_CONTENT for height
                );
                rb[y].setLayoutParams(radioButtonParams);
                radioGroup.addView(rb[y]);
            }

            choicesLayout1.addView(radioGroup);
            choicesLayout1.addView(txtAnswer);
            choicesLayout1.addView(txtStatus);
            questionLayout.addView(choicesLayout1);
            quizLayout.addView(questionLayout);

            txtAnswer.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);

        }

    }

    private void setChapter2HardQuiz() {

        String[] questionss = {"Sa anong paaralan nagsimulang gamitin ni Jose gamitin ang apelyidong Rizal?",
                "Saan si Jose kumuha ng pagsusulit para sa sekondaryang edukasyon?",
                "Ano ang apelyidong ginamit ni Jose sa Ateneo?",
                "Ano ang koneksyon ni Padre Manuel Xeres Burgos kay Padre Jose Burgos ng GOMBURZA?",
                "Saang parte ng Maynila si Jose nanirahan habang nag-aaral?",
                "Samahan na inaniban ni Jose na bukas sa mga relihiyoso at binubuo ng matiya-tiyagang mag-aaral",
                "Ilang taon si Jose ng samahan ni Paciano sa maynila?",
                "Kaano-ano ni Tandang Juancho ang pangalawang guro ni Jose"
        };

        String[] answerss = {"Ateneo de Municipal",
                "Colegio San Juan de Letran",
                "Rizal",
                "Kapatid",
                "Santa Cruz",
                "Congregacion de Maria",
                "11",
                "Biyenan",
        };

        correct_counter = 0;

        String[][] shuffledArrays = shuffleArrays(questionss, answerss);

        for (int i = 0; i < shuffledArrays[0].length; i++) {
            int index = i + 1;
            sharedPreferencesClass.putString("c2-hq" + index, shuffledArrays[0][i]);
            sharedPreferencesClass.putString("c2-ha" + index, shuffledArrays[1][i]);
        }

        String answers[] = new String[5];
        for (int x = 1; x <= 5; x++) {
            answers[x - 1] = sharedPreferencesClass.getString("c2-ha" + x, "");

            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setText(x + ". " + sharedPreferencesClass.getString("c2-hq" + x, ""));

            LinearLayout.LayoutParams questionLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );

            questionLayoutParams.setMargins(40, 10, 40, 50);
            textView.setLayoutParams(questionLayoutParams);
            quizLayout.addView(textView);

            TextView txtAnswer = new TextView(this);
            txtAnswer.setText(answers[x - 1]);
            txtAnswer.setTextColor(Color.BLACK);
            txtAnswer.setTextSize(18);

            TextView txtStatus = new TextView(this);
            txtStatus.setText(answers[x - 1]);
            txtStatus.setTextColor(Color.BLACK);
            txtStatus.setTextSize(18);
            txtStatus.setText("WRONG");

            EditText editText = new EditText(this);
            editText.setHint("Enter your answer here...");
            //editText.setId(x + 1000);
            editText.setTextSize(20);
            LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            editTextParams.setMargins(40, 10, 40, 10);
            editText.setLayoutParams(editTextParams);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    if (txtStatus.getText().equals("WRONG") && charSequence.toString().trim().toLowerCase().equals(txtAnswer.getText().toString().trim().toLowerCase())) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("CORRECT")) {
                        txtStatus.setText("WRONG");
                        correct_counter--;
                        correctCounter.setText(String.valueOf(correct_counter));
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            quizLayout.addView(editText);
            quizLayout.addView(txtAnswer);
            quizLayout.addView(txtStatus);

            txtAnswer.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);

        }
    }

    private void setChapter2EasyQuiz() {

        String[] questionss = {"Siya ang naging pangalawang guro ni Jose nakilala sa pagiging mahigpit sa pagdidisiplina.",
                "Sino ang nagturo ng pagguhit at pagpinta kay Jose?",
                "___ang tawag sa mga estudyanteng nakatira sa loob ng Ateneo habang ___ ang tawag naman sa estudyang nakatira sa labas ng Ateneo.",
                "Kaano-ano ni Tandang Juancho ang pangalawang guro ni Jose?",
                "Ilang taon si Jose ng samahan ni Paciano sa maynila?",
                "Saan tuluyang nag-aral ng sekondarya si Jose?",
                "Sino ang kaibigan ni Paciano na tumulong kay Jose para makapasok sa ateneo?",
                "Isang samahan na inaniban ni Jose na bukas sa mga relihiyoso at binubuo ng matiya-tiyagang mag-aaral.",
                "Isang halimbawa ng aklat na binabasa ni Jose na nagpalalim ng kanyang kaalaman.",
                "Sino ang pumayag na bilhan si Jose ng mga aklat na El historia Universal ni Cesar Cantu?",
                "Anong paaralan nagsimula gamitin ni Jose ang kanyang apelyidong Rizal?",
                "Saan sekondaryang paaralan nag sulit si Jose?",
                "Magkaano-ano si Padre Xeres Burgos at Padre Jose Burgos?"};

        String[] choicess = {"Padre Jose Bech:G. Justiniano Aquino Cruz:Manuel Xeres:Leon Monroy",
                "Tandang Tasyo:Tandang Telmo:Tandang Juancho:Tandang Simon",
                "Interno, Interno:Externo, Externo:Externo, Interno:Interno, Externo",
                "Kapatid:Tiyohin:Biyenan:Kaibigan",
                "11:14:9:7",
                "Colegio San Juan de Letran:Unibersidad ng Santo Tomas:Ateneo de Municipal:Unibersidad ng Pilipinas",
                "Leon Monroy:Padre Manuel Xeres Burgos:Tandang Juancho:G. Justiniano Aquino Cruz",
                "Ateneo de Municipal:Interno:Congregacion de Maria:Mestizo",
                "Bibliya:Conte de Montre Cristo:Historia:Tula",
                "Ang kanyang Ama:Paciano:Tandang Juancho:Kapatid",
                "Pamantasan ng Malayong Silangan:Ateneo de Municipal:Pamantasang Pambansa:Ang Pamantasang De La Salle",
                "Colegio San Juan de Letran:Ateneo de Municipal:Pamantasang Pambansa:Ang Pamantasang De La Salle",
                "Kaibigan:Bayaw:Pinsan:Kapatid"};

        String[] answerss = {"G. Justiniano Aquino Cruz",
                "Tandang Juancho",
                "Interno, Externo",
                "Biyenan",
                "11",
                "Ateneo de Municipal",
                "Padre Manuel Xeres Burgos",
                "Congregacion de Maria",
                "Conte de Montre Cristo",
                "Ang kanyang Ama",
                "Ateneo de Municipal",
                "Pamantasang Pambansa",
                "Ang Pamantasang De La Salle",
        };

        correct_counter = 0;

        String[][] shuffledArrays = shuffleArrays(questionss, answerss, choicess);

        for (int i = 0; i < shuffledArrays[0].length; i++) {
            int index = i + 1;
            sharedPreferencesClass.putString("c2-eq" + index, shuffledArrays[0][i]);
            sharedPreferencesClass.putString("c2-ea" + index, shuffledArrays[1][i]);
            sharedPreferencesClass.putString("c2-ec" + index, shuffledArrays[2][i]);

        }


        String answers[] = new String[10];
        for (int x = 1; x <= 10; x++) {
            answers[x - 1] = sharedPreferencesClass.getString("c2-ea" + x, "");

            LinearLayout questionLayout = new LinearLayout(this);
            questionLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            layoutParams.setMargins(40, 0, 40, 50);
            questionLayout.setLayoutParams(layoutParams);

            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setText(x + ". " + sharedPreferencesClass.getString("c2-eq" + x, ""));

            questionLayout.addView(textView);

            String choices[] = sharedPreferencesClass.getString("c2-ec" + x, "").split(":");

            LinearLayout choicesLayout1 = new LinearLayout(this);
            choicesLayout1.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams choicesLayout1Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            choicesLayout1Params.setMargins(20, 10, 20, 70);
            choicesLayout1.setLayoutParams(choicesLayout1Params);

            TextView txtAnswer = new TextView(this);
            txtAnswer.setText(answers[x - 1]);
            txtAnswer.setTextColor(Color.BLACK);
            txtAnswer.setTextSize(18);

            TextView txtStatus = new TextView(this);
            txtStatus.setText(answers[x - 1]);
            txtStatus.setTextColor(Color.BLACK);
            txtStatus.setTextSize(18);
            txtStatus.setText("WRONG");

            RadioGroup radioGroup = new RadioGroup(this);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    String textValue = checkedRadioButton.getText().toString();
                    if (txtStatus.getText().equals("WRONG") && textValue.trim().toLowerCase().equals(txtAnswer.getText().toString().trim().toLowerCase())) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("CORRECT")) {
                        txtStatus.setText("WRONG");
                        correct_counter--;
                        correctCounter.setText(String.valueOf(correct_counter));
                    }

                }
            });

            RadioButton[] rb = new RadioButton[4];

            for (int y = 0; y < choices.length; y++) {

                rb[y] = new RadioButton(this);
                rb[y].setText(choices[y]);
                rb[y].setTextSize(20);
                LinearLayout.LayoutParams radioButtonParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                rb[y].setLayoutParams(radioButtonParams);
                radioGroup.addView(rb[y]);

            }

            choicesLayout1.addView(radioGroup);
            choicesLayout1.addView(txtAnswer);
            choicesLayout1.addView(txtStatus);
            questionLayout.addView(choicesLayout1);
            quizLayout.addView(questionLayout);

            txtAnswer.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);

        }

    }

    private void setChapter3HardQuiz() {

        String[] questionss = {"Si Padre Villarica ang nagging guro ni Jose sa asignaturang Pilosopiya at A____?",
                "Ito ang nag-iisang unibersidad sa pilipinas na pinamamahalaan ng ordeng Dominikano.",
                "Ano itong kursong sa una pa lang ay hilig na talaga ni Jose?",
                "Siya mismo ang nagpayo kay Jose na talikuran na ang pagsulat ng panitikan?",
                "Sino ang sumama kay Jose sa Unibersidad ng Santo Tomas para magpalista?",
                "Dalawang kursong pinag-sabay ni Jose sa Kolehiyo",
                "Nagpatala si Jose sa kursong ito dahil sa kaniyang inang malabo ang mata",
                "Ilang taon si Jose noong makapagtapos ng kursong Bachiller en Ares sa Ateneo?"
        };

        String[] answerss = {"Agham",
                "Unibersidad ng Santo Tomas",
                "Pilosopiya at Literatura",
                "Padre Villarica",
                "Paciano",
                "Pilosopiya at Literatura",
                "Medisina",
                "16",
        };

        correct_counter = 0;

        String[][] shuffledArrays = shuffleArrays(questionss, answerss);

        for (int i = 0; i < shuffledArrays[0].length; i++) {
            int index = i + 1;
            sharedPreferencesClass.putString("c3-hq" + index, shuffledArrays[0][i]);
            sharedPreferencesClass.putString("c3-ha" + index, shuffledArrays[1][i]);
        }

        String answers[] = new String[5];
        for (int x = 1; x <= 5; x++) {
            answers[x - 1] = sharedPreferencesClass.getString("c3-ha" + x, "");

            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setText(x + ". " + sharedPreferencesClass.getString("c3-hq" + x, ""));

            LinearLayout.LayoutParams questionLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );

            questionLayoutParams.setMargins(40, 10, 40, 50);
            textView.setLayoutParams(questionLayoutParams);
            quizLayout.addView(textView);

            TextView txtAnswer = new TextView(this);
            txtAnswer.setText(answers[x - 1]);
            txtAnswer.setTextColor(Color.BLACK);
            txtAnswer.setTextSize(18);

            TextView txtStatus = new TextView(this);
            txtStatus.setText(answers[x - 1]);
            txtStatus.setTextColor(Color.BLACK);
            txtStatus.setTextSize(18);
            txtStatus.setText("WRONG");

            EditText editText = new EditText(this);
            editText.setHint("Enter your answer here...");
            //editText.setId(x + 1000);
            editText.setTextSize(20);
            LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            editTextParams.setMargins(40, 10, 40, 10);
            editText.setLayoutParams(editTextParams);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    if (txtStatus.getText().equals("WRONG") && charSequence.toString().trim().toLowerCase().equals(txtAnswer.getText().toString().trim().toLowerCase())) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("CORRECT")) {
                        txtStatus.setText("WRONG");
                        correct_counter--;
                        correctCounter.setText(String.valueOf(correct_counter));
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            quizLayout.addView(editText);
            quizLayout.addView(txtAnswer);
            quizLayout.addView(txtStatus);

            txtAnswer.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);
        }
    }


    private void setChapter3EasyQuiz() {

        String[] questionss = {"Sino ang gurong nag-impluwensya kay Jose sa pagsulat ng panitikan?",
                "Tulang kaniyang sinulat noong siya ay nag-aaral sa Ateneo na may translasyon na “Sa Pamamagitan ng Karunungan Nakapagtatamo ang Bayan ng Liwanag”?",
                "Ilang taon si Jose noong makapagtapos ng kursong Bachiller en Ares sa Ateneo?",
                "Anong kurso ang ipinalista ni Jose alinsunod sa kagustuhan ng kaniyang ama?",
                "Nagpatala si Jose sa kursong ito dahil sa kaniyang inang malabo ang mata.",
                "Taong 1611 ang unibersidad na ito ay itinutring na pangunahing institusyon ng kaalamang Europeo sa Silangan.",
                "Sino itong tutol na magpatuloy sa mataas na pag-aaral si Jose?",
                "Sino ang nagdesisyon na magpatuloy sa mataas na pag-aaral si Jose?",
                "Ilan ang medalyang natanggap ni Jose noong makapagtapos sa Ateneo?",
                "Maliban sa kursong medisina, sinabay niya din ang kursong?",
                "Ano ang kursong hilig talaga ni Jose?",
                "Nag-iisang unibersidad sa pilipinas na pinamamahalaan ng ordeng Dominikano.",
                "Nagpayo kay Jose na talikuran na ang pagsulat ng panitikan"};

        String[] choicess = {"Padre Jose Bech:Padre Francisco Paula Sanchez:Padre Jose Villaclara:Padre Damaso",
                "Mga Gunita ng Isang Mag-aaral:Por la Educacion Recibe Lustre La Patria:Sa aking mga  Kababata:Noli Me Tangere",
                "16:19:21:15",
                "Pilosopiya:Medisina:Agham:Metapisika",
                "Pilosopiya:Medisina:Agham:Metapisika",
                "Colegio San Juan de Letran:Colegio de Santa Isabel:Ateneo de Municipal:Unibersidad ng Santo Tomas",
                "Paciano:Ama:Ina:Soledad",
                "Paciano:Ama:Ina:Soledad",
                "5:1:8:3",
                "Pilosopiya at Literatura:Agham:Abogasya:Metapisika",
                "Pilosopiya at Literatura:Agham:Abogasya: Arkitektura",
                "Ateneo de Municipal:Unibersidad ng Santo Tomas:Ang Pamantasang De La Salle:Pamantasang Pambansa",
                "Padre Damaso:Padre Burgos:Padre Villarica:Padre Estrella"};


        String[] answerss = {"Padre Francisco Paula Sanchez",
                "Por la Educacion Recibe Lustre La Patria",
                "16",
                "Metapisika",
                "Medisina",
                "Unibersidad ng Santo Tomas",
                "Ina",
                "Ama",
                "5",
                "Pilosopiya at Literatura",
                "Pilosopiya at Literatura",
                "Unibersidad ng Santo Tomas",
                "Padre Villarica",
        };

        correct_counter = 0;

        String[][] shuffledArrays = shuffleArrays(questionss, answerss, choicess);

        for (int i = 0; i < shuffledArrays[0].length; i++) {
            int index = i + 1;
            sharedPreferencesClass.putString("c3-eq" + index, shuffledArrays[0][i]);
            sharedPreferencesClass.putString("c3-ea" + index, shuffledArrays[1][i]);
            sharedPreferencesClass.putString("c3-ec" + index, shuffledArrays[2][i]);

        }

        String answers[] = new String[10];
        for (int x = 1; x <= 10; x++) {
            answers[x - 1] = sharedPreferencesClass.getString("c3-ea" + x, "");

            LinearLayout questionLayout = new LinearLayout(this);
            questionLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            layoutParams.setMargins(40, 0, 40, 50);
            questionLayout.setLayoutParams(layoutParams);

            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setText(x + ". " + sharedPreferencesClass.getString("c3-eq" + x, ""));

            questionLayout.addView(textView);

            String choices[] = sharedPreferencesClass.getString("c3-ec" + x, "").split(":");

            LinearLayout choicesLayout1 = new LinearLayout(this);
            choicesLayout1.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams choicesLayout1Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            choicesLayout1Params.setMargins(20, 10, 20, 70);
            choicesLayout1.setLayoutParams(choicesLayout1Params);

            TextView txtAnswer = new TextView(this);
            txtAnswer.setText(answers[x - 1]);
            txtAnswer.setTextColor(Color.BLACK);
            txtAnswer.setTextSize(18);

            TextView txtStatus = new TextView(this);
            txtStatus.setText(answers[x - 1]);
            txtStatus.setTextColor(Color.BLACK);
            txtStatus.setTextSize(18);
            txtStatus.setText("WRONG");

            RadioGroup radioGroup = new RadioGroup(this);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    String textValue = checkedRadioButton.getText().toString();
                    if (txtStatus.getText().equals("WRONG") && textValue.trim().toLowerCase().equals(txtAnswer.getText().toString().trim().toLowerCase())) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("CORRECT")) {
                        txtStatus.setText("WRONG");
                        correct_counter--;
                        correctCounter.setText(String.valueOf(correct_counter));
                    }

                }
            });

            RadioButton[] rb = new RadioButton[4];

            for (int y = 0; y < choices.length; y++) {

                rb[y] = new RadioButton(this);
                rb[y].setText(choices[y]);
                rb[y].setTextSize(20);
                LinearLayout.LayoutParams radioButtonParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                rb[y].setLayoutParams(radioButtonParams);
                radioGroup.addView(rb[y]);

            }

            choicesLayout1.addView(radioGroup);
            choicesLayout1.addView(txtAnswer);
            choicesLayout1.addView(txtStatus);
            questionLayout.addView(choicesLayout1);
            quizLayout.addView(questionLayout);

            txtAnswer.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);

        }

    }

    private void setChapter4HardQuiz() {

        String[] questionss = {"Sino ang “Unang Pag-ibig“ ni Jose?",
                "Sino ang “Unang Tunay na Pag-ibig“ ni Jose?",
                "Sino ang nakaukit sa singsing na napanalunan ni Jose at kinikilalang “Dakilang Anak ng Panitikan“?",
                "Sino ang nakaimpluwensya kay Paciano?",
                "Anong patimpalak na isang indio ang tumalo sa mga Espanyol sa larangang gamit ang sariling wika?",
                "Sino ang nag-iisang lalaki na kapatid ni Jose?",
                "Anong taon sumali si Jose sa Liceo Artistico-Literitario?",
                "Sino ang nagmamay-ari ng bahay na nilipatan ni Jose sa loob ng Intramuros"};

        String[] answerss = {"Segunda Katigbak",
                "Leonor Rivera",
                "Miguel de Cervantes",
                "Padre Jose Burgos",
                "Liceo Artistico-Literitario",
                "Paciano",
                "1879",
                "Antonio Rivera",

        };

        correct_counter = 0;

        String[][] shuffledArrays = shuffleArrays(questionss, answerss);

        for (int i = 0; i < shuffledArrays[0].length; i++) {
            int index = i + 1;
            sharedPreferencesClass.putString("c4-hq" + index, shuffledArrays[0][i]);
            sharedPreferencesClass.putString("c4-ha" + index, shuffledArrays[1][i]);
        }

        String answers[] = new String[5];
        for (int x = 1; x <= 5; x++) {
            answers[x - 1] = sharedPreferencesClass.getString("c4-ha" + x, "");

            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setText(x + ". " + sharedPreferencesClass.getString("c4-hq" + x, ""));

            LinearLayout.LayoutParams questionLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );

            questionLayoutParams.setMargins(40, 10, 40, 50);
            textView.setLayoutParams(questionLayoutParams);
            quizLayout.addView(textView);

            TextView txtAnswer = new TextView(this);
            txtAnswer.setText(answers[x - 1]);
            txtAnswer.setTextColor(Color.BLACK);
            txtAnswer.setTextSize(18);

            TextView txtStatus = new TextView(this);
            txtStatus.setText(answers[x - 1]);
            txtStatus.setTextColor(Color.BLACK);
            txtStatus.setTextSize(18);
            txtStatus.setText("WRONG");

            EditText editText = new EditText(this);
            editText.setHint("Enter your answer here...");
            //editText.setId(x + 1000);
            editText.setTextSize(20);
            LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            editTextParams.setMargins(40, 10, 40, 10);
            editText.setLayoutParams(editTextParams);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    if (txtStatus.getText().equals("WRONG") && charSequence.toString().trim().toLowerCase().equals(txtAnswer.getText().toString().trim().toLowerCase())) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("CORRECT")) {
                        txtStatus.setText("WRONG");
                        correct_counter--;
                        correctCounter.setText(String.valueOf(correct_counter));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            quizLayout.addView(editText);
            quizLayout.addView(txtAnswer);
            quizLayout.addView(txtStatus);

            txtAnswer.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);

        }
    }


    private void setChapter4EasyQuiz() {

        String[] questionss = {"Sino ang nagmamay-ari ng bahay na nilipatan ni Jose sa loob ng Intramuros",
                "Kaano-ano ni Jose ang nagmamay-ari ng bahay na nilipatan niya?",
                "Sino ang babaeng nakilala ni Jose sa tirahan ni Antonio?",
                "Ano itong paligsahang pangliteraryo na nilahukan ni Jose noong 1879?",
                "Ang tulang nagpanalo kay Jose  at nagkamit ng unang gantimpala na tumatalakay sa kahalagahan ng mga nakapag-aral na kabataan para sa kanilang bayan",
                "Ito ay isang komedya na nagpapakita ng buhay ng mga Filipino bago ito sinakop ng  mga kastila?",
                "Sino ang nag-iisang kapatid na lalaki ni Jose na mas matanda sa kaniya ng sampung taon?",
                "Anong taong sumali si Jose sa Liceo Artistico-Literitario?",
                "Kailan unang itinanghal ang Junto al Pasig?",
                "Ano ang relasyon ni Jose at Leonor?", "Sinong babae ang Unang Pag-ibig ni Jose?",
                "Sino ang nakaukit sa singsing na napanalunan ni Jose at kinikilalang \"Dakilang Anak ng Panitikan\"",

        };

        String[] choicess = {"Segunda Katigbak:Antonio Rivera:Padre Jose Villaclara:Padre Damaso",
                "Tiyuhin:Kapatid:Pinsan:Kapitbahay",
                "Leonor Rivera:Segunda Katigbak:Maria:Soledad",
                "Liceo Artistico-Literario:La Liga Filipina:La Filipino de Artistico:Literario de competacion",
                "Mga Gunita ng Isang Mag-aaral:Junto al Pasig:A La Juventud Filipina:Noli Me Tangere",
                "Mga Gunita ng Isang Mag-aaral:Junto al Pasig:A La Juventud Filipina:Noli Me Tangere",
                "Paciano:Antonio:Maria:Soledad",
                "1888:1866:1857:1879",
                "Disyembre 1890:Nobyembre 1890:Disyembre 1880:Nobyembre 1880",
                "Pinsan:Tiyahin:Kapatid:Ninang",
                "Segunda Katigbak:Leonor Valenzuela:Gertrude Beckett:Leonor Rivera",
                "Miguel de Cervantes:Padre Jose Burgos:Leonor Rivera:Leonor Valenzuela"};

        String[] answerss = {"Antonio Rivera",
                "Tiyuhin",
                "Leonor Rivera",
                "Liceo Artistico-Literario",
                "A La Juventud Filipina",
                "Junto al Pasig",
                "Paciano",
                "1879",
                "Disyembre 1880",
                "Pinsan",
                "Segunda Katigbak",
                "Miguel de Cervantes",
        };

        correct_counter = 0;

        String[][] shuffledArrays = shuffleArrays(questionss, answerss, choicess);

        for (int i = 0; i < shuffledArrays[0].length; i++) {
            int index = i + 1;
            sharedPreferencesClass.putString("c4-eq" + index, shuffledArrays[0][i]);
            sharedPreferencesClass.putString("c4-ea" + index, shuffledArrays[1][i]);
            sharedPreferencesClass.putString("c4-ec" + index, shuffledArrays[2][i]);

        }

        String answers[] = new String[10];
        for (int x = 1; x <= 10; x++) {
            answers[x - 1] = sharedPreferencesClass.getString("c4-ea" + x, "");

            LinearLayout questionLayout = new LinearLayout(this);
            questionLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            layoutParams.setMargins(40, 0, 40, 50);
            questionLayout.setLayoutParams(layoutParams);

            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setText(x + ". " + sharedPreferencesClass.getString("c4-eq" + x, ""));

            questionLayout.addView(textView);

            String choices[] = sharedPreferencesClass.getString("c4-ec" + x, "").split(":");

            LinearLayout choicesLayout1 = new LinearLayout(this);
            choicesLayout1.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams choicesLayout1Params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            choicesLayout1Params.setMargins(20, 10, 20, 70);
            choicesLayout1.setLayoutParams(choicesLayout1Params);

            TextView txtAnswer = new TextView(this);
            txtAnswer.setText(answers[x - 1]);
            txtAnswer.setTextColor(Color.BLACK);
            txtAnswer.setTextSize(18);

            TextView txtStatus = new TextView(this);
            txtStatus.setText(answers[x - 1]);
            txtStatus.setTextColor(Color.BLACK);
            txtStatus.setTextSize(18);
            txtStatus.setText("WRONG");

            RadioGroup radioGroup = new RadioGroup(this);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    String textValue = checkedRadioButton.getText().toString();
                    if (txtStatus.getText().equals("WRONG") && textValue.trim().toLowerCase().equals(txtAnswer.getText().toString().trim().toLowerCase())) {
                        txtStatus.setText("CORRECT");
                        correct_counter++;
                        correctCounter.setText(String.valueOf(correct_counter));
                    } else if (txtStatus.getText().equals("CORRECT")) {
                        txtStatus.setText("WRONG");
                        correct_counter--;
                        correctCounter.setText(String.valueOf(correct_counter));
                    }

                }
            });

            RadioButton[] rb = new RadioButton[4];

            for (int y = 0; y < choices.length; y++) {

                rb[y] = new RadioButton(this);
                rb[y].setText(choices[y]);
                rb[y].setTextSize(20);
                LinearLayout.LayoutParams radioButtonParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                rb[y].setLayoutParams(radioButtonParams);
                radioGroup.addView(rb[y]);

            }

            choicesLayout1.addView(radioGroup);
            choicesLayout1.addView(txtAnswer);
            choicesLayout1.addView(txtStatus);
            questionLayout.addView(choicesLayout1);
            quizLayout.addView(questionLayout);

            txtAnswer.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);

        }
    }

    public static String[][] shuffleArrays(String[] questions, String[] answers, String[] choices) {
        Random rnd = new Random();
        for (int i = questions.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Swap questions
            String tempQuestion = questions[index];
            questions[index] = questions[i];
            questions[i] = tempQuestion;
            // Swap answers
            String tempAnswer = answers[index];
            answers[index] = answers[i];
            answers[i] = tempAnswer;
            // Swap choices
            String tempChoice = choices[index];
            choices[index] = choices[i];
            choices[i] = tempChoice;
        }
        // Returning all three shuffled arrays as a 2D array
        return new String[][]{questions, answers, choices};
    }

    public static String[][] shuffleArrays(String[] questions, String[] answers) {
        Random rnd = new Random();
        for (int i = questions.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Swap questions
            String tempQuestion = questions[index];
            questions[index] = questions[i];
            questions[i] = tempQuestion;
            // Swap answers
            String tempAnswer = answers[index];
            answers[index] = answers[i];
            answers[i] = tempAnswer;
        }
        // Returning all three shuffled arrays as a 2D array
        return new String[][]{questions, answers};
    }
}