package com.qppd.rizalelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    private UserFunctions userFunctions = new UserFunctions(this);

    private ListView trivias;

    private String trivia_key;
    private ArrayList<Trivia> trivia_list;

    private TriviaList trivia_adapter;
    private Trivia trivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        userFunctions.setActionbar(getSupportActionBar(), 1, "Tribya", 0);

        trivias = findViewById(R.id.listViewTrivias);

        trivia_list = new ArrayList<>();

        for (int i = 1; i <= 312; i++) {
            String triviaName = "Trivia " + i;
            String triviaStringResourceName = "trivia" + i;

            // Assuming R.string.trivia63, R.string.trivia64, ..., R.string.trivia151 exist in your resources
            int resourceId = getResources().getIdentifier(triviaStringResourceName, "string", getPackageName());
            String triviaContent = getString(resourceId);

            Trivia trivia = new Trivia(triviaName, getString(R.string.tab) + "" + triviaContent);
            trivia_list.add(trivia);
        }



        loadTrivias();

    }

    void loadTrivias() {

        trivia_adapter = new TriviaList(this, trivia_list);
        trivias.setAdapter(trivia_adapter);

        trivias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                user = user_list.get(position);
//                user_key = user_keys.get(position);
//                selected_email = user.getEmail();
                return false;
            }
        });

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

}