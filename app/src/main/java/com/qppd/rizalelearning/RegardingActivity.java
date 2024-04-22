package com.qppd.rizalelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.qppd.rizalelearning.Libs.Functionz.UserFunctions;

public class RegardingActivity extends AppCompatActivity {

    private UserFunctions userFunctions = new UserFunctions(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regarding);

        userFunctions.setActionbar(getSupportActionBar(), 1, "Patungkol", 0);

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