package com.qppd.rizalelearning.Libs.Dialogz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.qppd.rizalelearning.R;

public class Loading {

    Activity activity;
    AlertDialog dialog;

    public Loading(Activity activity) {
        this.activity = activity;
    }

    @SuppressLint("InflateParams")
    public void start() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading, null));
        builder.setCancelable(false);

        dialog = builder.create();

        dialog.show();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}

